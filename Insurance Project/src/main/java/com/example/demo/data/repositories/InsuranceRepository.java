package com.example.demo.data.repositories;

import com.example.demo.data.entities.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {

    //find entity by its unique ID
    Optional<InsuranceEntity> findByInsuranceId(Long insuranceId);

    //find list of entities by logged user ID
    List<InsuranceEntity> findByUserEntityUserId(Long userId);
}
