package com.example.demo.models.services;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.entities.UserEntity;
import com.example.demo.models.dto.InsuranceDTO;

import java.util.List;

public interface InsuranceService {

    //Method for creating an insurance
    InsuranceDTO createInsurance(InsuranceDTO insurance, UserEntity userEntity);

    //Method for finding all insurances
    List<InsuranceDTO> getAll();

    //Method for finding insurance by its ID
    InsuranceDTO getById(Long insuranceId);

    //Method for finding all insurances by user's ID
    List<InsuranceEntity> getInsurancesForCurrentUser();

    //Method for editing of existing insurance
    void editInsurance(InsuranceDTO insurance);

    //Method for deleting of existing insurance
    void removeInsurance(Long insuranceId);
}
