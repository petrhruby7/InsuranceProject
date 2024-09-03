package com.example.demo.models.services;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.models.dto.EventDTO;

import java.util.List;

public interface EventService {

    EventDTO createEvent(EventDTO eventDTO, InsuranceEntity insuranceEntity);

    List<EventDTO> getAll();

    List<EventDTO> getEventByInsuranceId(Long insuranceId);

    EventDTO getById(Long eventId);

    void editEvent(EventDTO eventDTO);

    void removeEvent(Long eventId);
}
