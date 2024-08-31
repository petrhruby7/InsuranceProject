package com.example.demo.models.services;

import com.example.demo.models.dto.InsuranceDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface InsuranceService {

    InsuranceDTO createInsurance(InsuranceDTO insurance);

    //void updateInsurance(InsuranceDTO insurance);

    //void deleteInsurance(InsuranceDTO insurance);
}
