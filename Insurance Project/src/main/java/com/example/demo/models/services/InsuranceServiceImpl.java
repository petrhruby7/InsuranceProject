package com.example.demo.models.services;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.entities.UserEntity;
import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.InsuranceDTO;
import com.example.demo.models.dto.mappers.InsuranceMapper;
import com.example.demo.models.exceptions.InsuranceAmountException;
import com.example.demo.models.exceptions.InsuranceDurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private InsuranceMapper insuranceMapper;

    @Override
    public InsuranceDTO createInsurance(InsuranceDTO insuranceDTO, UserEntity userEntity){
        validateInsuranceDuration(insuranceDTO.getStartDate(),insuranceDTO.getEndDate());
        validateInsuranceAmount(insuranceDTO.getAmount());


        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity.setUserEntity(userEntity);
        insuranceEntity = insuranceRepository.saveAndFlush(insuranceEntity);
        return insuranceMapper.toDTO(insuranceEntity);
    }

    @Override
    public List<InsuranceDTO> getAll() {
        return insuranceRepository.findAll()
                .stream()
                .map(i -> insuranceMapper.toDTO(i))
                .toList();
    }

    @Override
    public InsuranceDTO getById(Long insuranceId) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceId);
        return insuranceMapper.toDTO(fetchedInsurance);
    }

    @Override
    public void editInsurance(InsuranceDTO insuranceDTO) {
        validateInsuranceDuration(insuranceDTO.getStartDate(),insuranceDTO.getEndDate());
        validateInsuranceAmount(insuranceDTO.getAmount());

        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceDTO.getInsuranceId());
        insuranceMapper.updateInsuranceEntity(insuranceDTO,fetchedInsurance);
        insuranceRepository.saveAndFlush(fetchedInsurance);
    }

    @Override
    public void removeInsurance(Long insuranceId) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceId);
        insuranceRepository.delete(fetchedInsurance);
    }

    //metoda pro získání Insurance dle unikátního Id
    private InsuranceEntity getInsuranceOrThrow(Long insuranceId){
        return insuranceRepository
                .findById(insuranceId)
                .orElseThrow();
    }

    //kontrola: pojištění trvá alespoň 1 měsíc a není delší než 5 let
    private void validateInsuranceDuration(LocalDate startDate, LocalDate endDate){
        Period insurancePeriod = Period.between(startDate,endDate);
        if((insurancePeriod.getYears()*12 + insurancePeriod.getMonths() < 6)||(insurancePeriod.getYears()>5)){
            throw new InsuranceDurationException();
        }
    }

    //kontrola: pojistná částka není menší než 10 000 a nepřesahuje 10 000 000
    private void validateInsuranceAmount(int amount){
        if((amount < 10000)||(amount > 10000000)){
            throw new InsuranceAmountException();
        }
    }
}
