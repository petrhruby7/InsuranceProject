package com.example.demo.models.dto.mappers;

import com.example.demo.data.entities.ProfileEntity;
import com.example.demo.models.dto.ProfileDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ProfileMapper {
    ProfileEntity toDTO (ProfileDTO profileDTO);
    ProfileDTO toEntity (ProfileEntity profileEntity);
}
