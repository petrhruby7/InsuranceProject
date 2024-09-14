package com.example.demo.models.dto.mappers;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {
    InsuranceDTO toDTO(InsuranceEntity insuranceEntity);

    InsuranceEntity toEntity(InsuranceDTO insuranceDTO);

    void updateInsuranceDTO(InsuranceDTO insuranceDTO, @MappingTarget InsuranceDTO target);

    void updateInsuranceEntity(InsuranceDTO insuranceDTO, @MappingTarget InsuranceEntity target);
}
