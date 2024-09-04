package com.example.demo.data.repositories;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Test
    public void testFindByInsuranceEntityInsuranceId() {
        // Nastavte testová data
        InsuranceEntity insuranceEntity = new InsuranceEntity();
        insuranceEntity.setInsuranceId(1L); // Nastavte id podle potřeby
        insuranceRepository.save(insuranceEntity);

        EventEntity event1 = new EventEntity();
        event1.setInsuranceEntity(insuranceEntity);
        eventRepository.save(event1);

        EventEntity event2 = new EventEntity();
        event2.setInsuranceEntity(insuranceEntity);
        eventRepository.save(event2);

        // Testujte dotaz
        List<EventEntity> events = eventRepository.findByInsuranceEntityInsuranceId(1L);
        assertEquals(2, events.size());
    }


}