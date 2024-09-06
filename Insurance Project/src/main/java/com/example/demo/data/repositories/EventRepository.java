package com.example.demo.data.repositories;

import com.example.demo.data.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    //vyhledá entitu dle jejího unikátního id
    Optional<EventEntity> findByEventId(Long eventId);
    //vyhledá entitu dle Id pojištění ke kterému je přiřazená
    List<EventEntity> findByInsuranceEntityInsuranceId(Long insuranceId);
    //vyhledá entitu dle userId
    List<EventEntity> findByInsuranceEntityUserEntityUserId(Long userId);
}
