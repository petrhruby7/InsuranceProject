package com.example.demo.models.services;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.repositories.EventRepository;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventMapper eventMapper;

    @Override
    public EventDTO createEvent(EventDTO eventDTO, InsuranceEntity insuranceEntity) {
        //todo: zde vložit validační kontroly

        EventEntity eventEntity = eventMapper.toEntity(eventDTO);
        eventEntity.setInsuranceEntity(insuranceEntity);
        eventEntity = eventRepository.saveAndFlush(eventEntity);
        return eventMapper.toDTO(eventEntity);
    }

    @Override
    public List<EventDTO> getAll() {
        return eventRepository.findAll()
                .stream()
                .map(i -> eventMapper.toDTO(i))
                .toList();
    }

    @Override
    public EventDTO getById(Long eventId) {
        EventEntity fetchedEvent = getEventOrThrow(eventId);
        return eventMapper.toDTO(fetchedEvent);
    }

    @Override
    public void editEvent(EventDTO eventDTO) {
        //todo: zde vložit validační kontroly
        EventEntity fetchedEvent = getEventOrThrow(eventDTO.getEventId());
        eventMapper.updateEventEntity(eventDTO, fetchedEvent);
        eventRepository.saveAndFlush(fetchedEvent);
    }

    @Override
    public void removeEvent(Long eventId) {
        EventEntity fetchedEvent = getEventOrThrow(eventId);
        eventRepository.delete(fetchedEvent);
    }

    //metoda pro získání unikátního Id události
    private EventEntity getEventOrThrow(Long eventId) {
        return eventRepository
                .findById(eventId)
                .orElseThrow();
    }
}
