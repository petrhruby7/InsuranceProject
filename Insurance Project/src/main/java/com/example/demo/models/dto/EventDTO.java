package com.example.demo.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class EventDTO {

    //pole pro užovatele
    private Long eventId;
    private Long insuranceId;

    @NotNull(message = "Event date is required")
    @PastOrPresent(message = "The event date must be today or in the past")
    private LocalDate eventDate;

    @NotBlank(message = "Event description is required")
    private String eventDescription;

    //region: gettery a settery

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

    //konec regionu: gettery a settery
}
