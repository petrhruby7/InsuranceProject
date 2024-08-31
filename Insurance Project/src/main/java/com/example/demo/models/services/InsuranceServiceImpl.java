package com.example.demo.models.services;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.InsuranceDTO;
import com.example.demo.models.dto.mappers.InsuranceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private InsuranceMapper insuranceMapper;

    @Override
    public InsuranceDTO createInsurance(InsuranceDTO insuranceDTO){
        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity = insuranceRepository.saveAndFlush(insuranceEntity);
        return insuranceMapper.toDTO(insuranceEntity);

    }
}
