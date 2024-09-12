package com.example.demo.data.repositories;

import com.example.demo.data.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    //find entity by its unique ID
    Optional<EventEntity> findByEventId(Long eventId);

    //find list of entities by logged user ID
    List<EventEntity> findByInsuranceEntityInsuranceId(Long insuranceId);

    //find list of entities by associated insurance ID
    List<EventEntity> findByInsuranceEntityUserEntityUserId(Long userId);
}
