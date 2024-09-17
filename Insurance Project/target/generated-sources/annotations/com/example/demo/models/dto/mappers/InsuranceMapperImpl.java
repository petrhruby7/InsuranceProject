package com.example.demo.models.dto.mappers;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.models.dto.InsuranceDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-17T17:28:27+0200",
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

        insuranceDTO.setInsuranceId( insuranceEntity.getInsuranceId() );
        insuranceDTO.setUserId( insuranceEntity.getUserId() );
        insuranceDTO.setInsuranceType( insuranceEntity.getInsuranceType() );
        insuranceDTO.setAmount( insuranceEntity.getAmount() );
        insuranceDTO.setInsuredItem( insuranceEntity.getInsuredItem() );
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

        insuranceEntity.setInsuranceId( insuranceDTO.getInsuranceId() );
        insuranceEntity.setInsuranceType( insuranceDTO.getInsuranceType() );
        insuranceEntity.setAmount( insuranceDTO.getAmount() );
        insuranceEntity.setInsuredItem( insuranceDTO.getInsuredItem() );
        insuranceEntity.setStartDate( insuranceDTO.getStartDate() );
        insuranceEntity.setEndDate( insuranceDTO.getEndDate() );

        return insuranceEntity;
    }

    @Override
    public void updateInsuranceDTO(InsuranceDTO insuranceDTO, InsuranceDTO target) {
        if ( insuranceDTO == null ) {
            return;
        }

        target.setInsuranceId( insuranceDTO.getInsuranceId() );
        target.setUserId( insuranceDTO.getUserId() );
        target.setInsuranceType( insuranceDTO.getInsuranceType() );
        target.setAmount( insuranceDTO.getAmount() );
        target.setInsuredItem( insuranceDTO.getInsuredItem() );
        target.setStartDate( insuranceDTO.getStartDate() );
        target.setEndDate( insuranceDTO.getEndDate() );
    }

    @Override
    public void updateInsuranceEntity(InsuranceDTO insuranceDTO, InsuranceEntity target) {
        if ( insuranceDTO == null ) {
            return;
        }

        target.setInsuranceId( insuranceDTO.getInsuranceId() );
        target.setInsuranceType( insuranceDTO.getInsuranceType() );
        target.setAmount( insuranceDTO.getAmount() );
        target.setInsuredItem( insuranceDTO.getInsuredItem() );
        target.setStartDate( insuranceDTO.getStartDate() );
        target.setEndDate( insuranceDTO.getEndDate() );
    }
}
