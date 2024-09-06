package com.example.demo.data.repositories;

import com.example.demo.data.entities.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {


    //vyhledá entitu dle jejího unikátního id
    Optional<InsuranceEntity> findByInsuranceId(Long insuranceId);
    //vyhledá všechny pojištění podle id
    List<InsuranceEntity> findByUserEntityUserId(Long userId);
}
