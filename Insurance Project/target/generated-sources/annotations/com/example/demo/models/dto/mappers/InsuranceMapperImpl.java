package com.example.demo.models.dto.mappers;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.models.dto.InsuranceDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T10:42:19+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class InsuranceMapperImpl implements InsuranceMapper {

    @Override
    public InsuranceDTO toDTO(InsuranceEntity insuranceEntity) {
        if ( insuranceEntity == null ) {
            return null;
        }

        InsuranceDTO insuranceDTO = new InsuranceDTO();

        insuranceDTO.setInsuranceType( insuranceEntity.getInsuranceType() );
        insuranceDTO.setAmount( insuranceEntity.getAmount() );
        insuranceDTO.setInsuranceItem( insuranceEntity.getInsuranceItem() );
        insuranceDTO.setStartDate( insuranceEntity.getStartDate() );
        insuranceDTO.setEndDate( insuranceEntity.getEndDate() );

        return insuranceDTO;
    }

    @Override
    public InsuranceEntity toEntity(InsuranceDTO insuranceDTO) {
        if ( insuranceDTO == null ) {
            return null;
        }

        InsuranceEntity insuranceEntity = new InsuranceEntity();

        insuranceEntity.setInsuranceType( insuranceDTO.getInsuranceType() );
        insuranceEntity.setAmount( insuranceDTO.getAmount() );
        insuranceEntity.setInsuranceItem( insuranceDTO.getInsuranceItem() );
        insuranceEntity.setStartDate( insuranceDTO.getStartDate() );
        insuranceEntity.setEndDate( insuranceDTO.getEndDate() );

        return insuranceEntity;
    }
}
