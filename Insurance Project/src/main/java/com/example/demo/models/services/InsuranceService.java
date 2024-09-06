package com.example.demo.models.services;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.entities.UserEntity;
import com.example.demo.models.dto.InsuranceDTO;
import java.util.List;

public interface InsuranceService {

    InsuranceDTO createInsurance(InsuranceDTO insurance, UserEntity userEntity);

    List<InsuranceDTO> getAll();

    InsuranceDTO getById(Long insuranceId);

    List<InsuranceEntity> getInsurancesForCurrentUser();

    void editInsurance(InsuranceDTO insurance);

    void removeInsurance(Long insuranceId);
}
