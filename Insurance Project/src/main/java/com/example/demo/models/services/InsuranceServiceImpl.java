package com.example.demo.models.services;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.entities.UserEntity;
import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.InsuranceDTO;
import com.example.demo.models.dto.UserDTO;
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
    @Autowired
    private UserServiceImpl userService;

    /**
     * Creates a new insurance, associates it with the given user entity, and saves it to the database
     *
     * @param insuranceDTO Insurance data transfer object containing insurance details.
     * @param userEntity   The user entity associated with the insurance
     * @return InsuranceDTO object after the insurance is saved in the database.
     */
    @Override
    public InsuranceDTO createInsurance(InsuranceDTO insuranceDTO, UserEntity userEntity) {
        validateInsuranceDuration(insuranceDTO.getStartDate(), insuranceDTO.getEndDate());
        validateInsuranceAmount(insuranceDTO.getAmount());

        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity.setUserEntity(userEntity);
        insuranceEntity = insuranceRepository.saveAndFlush(insuranceEntity);
        return insuranceMapper.toDTO(insuranceEntity);
    }

    /**
     * Retrieves all insurances from the database and converts then to DTOs
     *
     * @return List of InsuranceDTO objects.
     */
    @Override
    public List<InsuranceDTO> getAll() {
        return insuranceRepository.findAll()
                .stream()
                .map(i -> insuranceMapper.toDTO(i))
                .toList();
    }

    /**
     * Retrieves an insurance by its ID.
     *
     * @param insuranceId ID of insurance to be retrieved
     * @return InsuranceDTO object corresponding to the user.
     */
    @Override
    public InsuranceDTO getById(Long insuranceId) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceId);
        return insuranceMapper.toDTO(fetchedInsurance);
    }

    /**
     * Retrieves insurances associated with the currently logged-in user.
     *
     * @return List of InsuranceEntity objects associated with the current user.
     */
    @Override
    public List<InsuranceEntity> getInsurancesForCurrentUser() {
        UserDTO currentUser = userService.getCurrentUser();
        return insuranceRepository.findByUserEntityUserId(currentUser.getUserId());
    }

    /**
     * Updates an existing insurance with new data and checks if the duration and amount are valid.
     *
     * @param insuranceDTO Updated insurance data transfer object.
     */
    @Override
    public void editInsurance(InsuranceDTO insuranceDTO) {
        validateInsuranceDuration(insuranceDTO.getStartDate(), insuranceDTO.getEndDate());
        validateInsuranceAmount(insuranceDTO.getAmount());

        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceDTO.getInsuranceId());
        insuranceMapper.updateInsuranceEntity(insuranceDTO, fetchedInsurance);
        insuranceRepository.saveAndFlush(fetchedInsurance);
    }

    /**
     * Removes an insurance by its ID
     *
     * @param insuranceId ID of the insurance to be removed
     */
    @Override
    public void removeInsurance(Long insuranceId) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceId);
        insuranceRepository.delete(fetchedInsurance);
    }

    /**
     * Helper method to fetch an insurance by its ID or throw an exception if it does not exist.
     *
     * @param insuranceId ID of the insurance
     * @return The corresponding InsuranceEntity.
     */
    public InsuranceEntity getInsuranceOrThrow(Long insuranceId) {
        return insuranceRepository
                .findByInsuranceId(insuranceId)
                .orElseThrow();
    }

    /**
     * Validates the duration of the insurance policy.
     * Ensures that the duration is between 6 months and 5 years.
     *
     * @param startDate The start date of the insurance policy.
     * @param endDate   The end date of the insurance policy.
     * @throws InsuranceDurationException if the insurance period is less than 6 months or more than 5 years.
     */
    private void validateInsuranceDuration(LocalDate startDate, LocalDate endDate) {
        Period insurancePeriod = Period.between(startDate, endDate);
        if ((insurancePeriod.getYears() * 12 + insurancePeriod.getMonths() < 6) || (insurancePeriod.getYears() > 5)) {
            throw new InsuranceDurationException();
        }
    }

    /**
     * Validates the amount of insurance coverage.
     * Ensures that the amount is within the acceptable range.
     *
     * @param amount The amount of insurance coverage.
     * @throws InsuranceAmountException if the amount is less than 10,000 or more than 10,000,000.
     */
    private void validateInsuranceAmount(int amount) {
        if ((amount < 10000) || (amount > 10000000)) {
            throw new InsuranceAmountException();
        }
    }


}
