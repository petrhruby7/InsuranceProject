package com.example.demo.models.services;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.models.dto.EventDTO;

import java.util.List;

public interface EventService {

    //Method for creating an insurance event
    EventDTO createEvent(EventDTO eventDTO, InsuranceEntity insuranceEntity);

    //Method for finding all existing events
    List<EventDTO> getAll();

    //Method for finding an insurance events
    EventDTO getById(Long eventId);

    //Method for finding all events by its insurance ID
    List<EventDTO> getEventByInsuranceId(Long insuranceId);

    //Method for finding all events by its user
    List<EventEntity> getEventsForCurrentUser();

    //Method for editing of existing event
    void editEvent(EventDTO eventDTO, InsuranceEntity insuranceEntity);

    //Method for deleting od existing event
    void removeEvent(Long eventId);
}
