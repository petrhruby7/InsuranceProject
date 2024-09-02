package com.example.demo.models.services;

import com.example.demo.data.entities.UserEntity;
import com.example.demo.models.dto.InsuranceDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface InsuranceService {

    InsuranceDTO createInsurance(InsuranceDTO insurance, UserEntity userEntity);

    List<InsuranceDTO> getAll();

    //void updateInsurance(InsuranceDTO insurance);

    //void deleteInsurance(InsuranceDTO insurance);
}
