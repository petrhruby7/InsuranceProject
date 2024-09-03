package com.example.demo.models.dto.mappers;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.models.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDTO(EventEntity eventEntity);

    EventEntity toEntity(EventDTO eventDTO);

    void updateEventDTO(EventDTO eventDTO, @MappingTarget EventDTO target);

    void updateEventEntity(EventDTO eventDTO, @MappingTarget EventEntity target);
}
