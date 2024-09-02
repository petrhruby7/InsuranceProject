package com.example.demo.data.repositories;

import com.example.demo.data.entities.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {


    //vyhledá entitu dle jejího unikátního id
    Optional<InsuranceEntity> findById(Long insuranceId);
}
