package com.example.demo.models.services;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.models.dto.EventDTO;

import java.util.List;

public interface EventService {

    EventDTO createEvent(EventDTO eventDTO, InsuranceEntity insuranceEntity);

    List<EventDTO> getAll();

    EventDTO getById(Long eventId);

    List<EventDTO> getEventByInsuranceId(Long insuranceId);

    List<EventEntity> getEventsForCurrentUser();

    void editEvent(EventDTO eventDTO, InsuranceEntity insuranceEntity);

    void removeEvent(Long eventId);
}
