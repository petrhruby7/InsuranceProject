package com.example.demo.models.services;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.repositories.EventRepository;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.dto.mappers.EventMapper;
import com.example.demo.models.exceptions.EventDateOutOfRangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private UserServiceImpl userService;

    /**
     * Creates a new event, associates it with the given insurance entity, and saves it to the database.
     *
     * @param eventDTO        Event data transfer object containing event details.
     * @param insuranceEntity The insurance entity associated with the event.
     * @return EventDTO object after the event is saved in the database.
     */
    @Override
    public EventDTO createEvent(EventDTO eventDTO, InsuranceEntity insuranceEntity) {

        checkEventDateInRange(eventDTO.getEventDate(), insuranceEntity.getStartDate(), insuranceEntity.getEndDate());

        EventEntity eventEntity = eventMapper.toEntity(eventDTO);
        eventEntity.setInsuranceEntity(insuranceEntity);

        eventEntity = eventRepository.saveAndFlush(eventEntity);
        return eventMapper.toDTO(eventEntity);
    }

    /**
     * Retrieves all events from the database and converts them to DTOs.
     *
     * @return List of EventDTO objects.
     */
    @Override
    public List<EventDTO> getAll() {
        // Find all event entities and map them to DTOs.
        return eventRepository.findAll().stream().map(i -> eventMapper.toDTO(i)).toList();
    }

    /**
     * Retrieves an event by its ID.
     *
     * @param eventId ID of the event to be retrieved.
     * @return EventDTO object corresponding to the event.
     */
    @Override
    public EventDTO getById(Long eventId) {
        EventEntity fetchedEvent = getEventOrThrow(eventId);
        return eventMapper.toDTO(fetchedEvent);
    }

    /**
     * Retrieves all events associated with a given insurance ID.
     *
     * @param insuranceId ID of the insurance entity.
     * @return List of EventDTO objects related to the insurance.
     */
    @Override
    public List<EventDTO> getEventByInsuranceId(Long insuranceId) {
        // Find all events related to a specific insurance and map them to DTOs.
        return eventRepository.findByInsuranceEntityInsuranceId(insuranceId).stream().map(i -> eventMapper.toDTO(i)).toList();
    }

    /**
     * Retrieves events associated with the currently logged-in user.
     *
     * @return List of EventEntity objects associated with the current user.
     */
    @Override
    public List<EventEntity> getEventsForCurrentUser() {
        UserDTO currentUser = userService.getCurrentUser();
        List<EventEntity> events = eventRepository.findByInsuranceEntityUserEntityUserId(currentUser.getUserId());

        return eventRepository.findByInsuranceEntityUserEntityUserId(currentUser.getUserId());
    }

    /**
     * Updates an existing event with new data and checks if the date is valid.
     *
     * @param eventDTO        Updated event data transfer object.
     * @param insuranceEntity The insurance entity associated with the event.
     */
    @Override
    public void editEvent(EventDTO eventDTO, InsuranceEntity insuranceEntity) {
        checkEventDateInRange(eventDTO.getEventDate(), insuranceEntity.getStartDate(), insuranceEntity.getEndDate());

        EventEntity fetchedEvent = getEventOrThrow(eventDTO.getEventId());
        eventMapper.updateEventEntity(eventDTO, fetchedEvent);
        eventRepository.saveAndFlush(fetchedEvent);
    }

    /**
     * Removes an event by its ID.
     *
     * @param eventId ID of the event to be removed.
     */
    @Override
    public void removeEvent(Long eventId) {
        EventEntity fetchedEvent = getEventOrThrow(eventId);
        eventRepository.delete(fetchedEvent);
    }

    /**
     * Helper method to fetch an event by its ID or throw an exception if it does not exist.
     *
     * @param eventId ID of the event.
     * @return The corresponding EventEntity.
     */
    private EventEntity getEventOrThrow(Long eventId) {
        // Try to find the event by ID, or throw an exception if not found.
        return eventRepository.findByEventId(eventId).orElseThrow();
    }

    /**
     * Validates that an event's date falls within the insurance's start and end date range.
     *
     * @param eventDate          Date of the event.
     * @param insuranceStartDate Start date of the insurance.
     * @param insuranceEndDate   End date of the insurance.
     */
    public void checkEventDateInRange(LocalDate eventDate, LocalDate insuranceStartDate, LocalDate insuranceEndDate) {
        if (eventDate.isBefore(insuranceStartDate) || eventDate.isAfter(insuranceEndDate)) {
            throw new EventDateOutOfRangeException();
        }
    }
}
