package com.example.demo.data.repositories;

import com.example.demo.data.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    //vyhledá entitu dle jejího unikátního id
    Optional<EventEntity> findById(Long eventId);
}
