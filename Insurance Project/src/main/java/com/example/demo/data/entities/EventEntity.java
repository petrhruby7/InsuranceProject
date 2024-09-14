package com.example.demo.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    private InsuranceEntity insuranceEntity;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private String eventDescription;

    //Region: getters and setters

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public InsuranceEntity getInsuranceEntity() {
        return insuranceEntity;
    }

    public void setInsuranceEntity(InsuranceEntity insuranceEntity) {
        this.insuranceEntity = insuranceEntity;
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

    //added getter - find an insurance Id
    public Long getInsuranceId() {
        if (getInsuranceEntity() == null) {
            return null;
        }
        return this.insuranceEntity.getInsuranceId();
    }

    // End of region: getters and setters
}
