package com.example.demo.models.dto.mappers;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.models.dto.EventDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-17T17:28:27+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDTO toDTO(EventEntity eventEntity) {
        if ( eventEntity == null ) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();

        eventDTO.setEventId( eventEntity.getEventId() );
        eventDTO.setInsuranceId( eventEntity.getInsuranceId() );
        eventDTO.setEventDate( eventEntity.getEventDate() );
        eventDTO.setEventDescription( eventEntity.getEventDescription() );

        return eventDTO;
    }

    @Override
    public EventEntity toEntity(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        EventEntity eventEntity = new EventEntity();

        eventEntity.setEventId( eventDTO.getEventId() );
        eventEntity.setEventDate( eventDTO.getEventDate() );
        eventEntity.setEventDescription( eventDTO.getEventDescription() );

        return eventEntity;
    }

    @Override
    public void updateEventDTO(EventDTO eventDTO, EventDTO target) {
        if ( eventDTO == null ) {
            return;
        }

        target.setEventId( eventDTO.getEventId() );
        target.setInsuranceId( eventDTO.getInsuranceId() );
        target.setEventDate( eventDTO.getEventDate() );
        target.setEventDescription( eventDTO.getEventDescription() );
    }

    @Override
    public void updateEventEntity(EventDTO eventDTO, EventEntity target) {
        if ( eventDTO == null ) {
            return;
        }

        target.setEventId( eventDTO.getEventId() );
        target.setEventDate( eventDTO.getEventDate() );
        target.setEventDescription( eventDTO.getEventDescription() );
    }
}
