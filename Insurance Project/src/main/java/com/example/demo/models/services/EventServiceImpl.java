package com.example.demo.models.services;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.repositories.EventRepository;
import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.dto.mappers.EventMapper;
import com.example.demo.models.exceptions.EventDateOutOfRangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private UserServiceImpl userService;

    /**
     * Creates a new event and associates it with the specified insurance.
     *
     * @param eventDTO DTO containing event details.
     * @param insuranceId ID of the insurance to which the event belongs.
     * @return The created EventDTO.
     * @throws EventDateOutOfRangeException if the event date is outside the insurance coverage period.
     */
    @Override
    public EventDTO createEvent(EventDTO eventDTO, Long insuranceId) {

        InsuranceEntity insuranceEntity = getInsuranceEntity(insuranceId);

        // Validate event date within the insurance coverage period
        if (!isEventDateInRange(eventDTO.getEventDate(), eventDTO.getInsuranceId())) {
            throw new EventDateOutOfRangeException();
        }

        //mapping En    
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
        //return mapped DTO
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

        return eventRepository.findByInsuranceEntityUserEntityUserId(currentUser.getUserId());
    }


    @Override
    public void editEvent(EventDTO eventDTO, Long insuranceId) {
        EventEntity fetchedEvent = getEventOrThrow(eventDTO.getEventId());

        // Validace data přímo ve service třídě
        if (!isEventDateInRange(eventDTO.getEventDate(), eventDTO.getInsuranceId())) {
            throw new EventDateOutOfRangeException();
        }

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


    private InsuranceEntity getInsuranceEntity(Long insuranceId) {
        return insuranceRepository.findByInsuranceId(insuranceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance ID: " + insuranceId));
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

    public boolean isEventDateInRange(LocalDate eventDate, Long insuranceId) {

        InsuranceEntity insuranceEntity = getInsuranceEntity(insuranceId);

        return !(eventDate.isBefore(insuranceEntity.getStartDate()) || eventDate.isAfter(insuranceEntity.getEndDate()));
    }

    // formátovací vzor může být definován jako konstantní hodnota
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Metoda pro formátování LocalDate na požadovaný řetězec
    public String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

}
