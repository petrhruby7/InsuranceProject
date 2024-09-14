package com.example.demo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EventDTO {

    //fields for users and insurances
    private Long eventId;
    private Long insuranceId;

    @NotNull(message = "Event date is required")
    @PastOrPresent(message = "The event date must be today or in the past")
    private LocalDate eventDate;

    @NotBlank(message = "Event description is required")
    @Size(min = 10, max = 250, message = "Event description must be between 10 and 250 characters")
    private String eventDescription;

    //Region: getters a setters

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    //End of region: getters a setters
}
