package com.example.demo.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Event")
public class EventEntity {
    /**
     * Id události: generuje se samo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    /**
     * navazuje na existují Entity - InsuranceEntity
     * Událostí je více na jedno pojištění
     */
    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    private InsuranceEntity insuranceEntity;
    /**
     * Datum kdy se událost stala
     * musí být vyplněno
     */
    @Column(nullable = false)
    private LocalDate eventDate;
    /**
     * popis pojistné událost
     * musí být vyplněno
     */
    @Column(nullable = false)
    private String eventDescription;

    //sekce: Gettery a Settery


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

    //getter navic - získá ID pojištění
    public Long getInsuranceId() {
        if (getInsuranceEntity() == null) {
            return null;
        }
        return this.insuranceEntity.getInsuranceId();
    }


    //konec sekce Gettery a Settery
}
