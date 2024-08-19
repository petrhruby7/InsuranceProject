package com.example.demo.models.dto.mappers;

import com.example.demo.data.entities.ProfileEntity;
import com.example.demo.models.dto.ProfileDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-18T22:17:44+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileEntity toEntity(ProfileDTO profileDTO) {
        if ( profileDTO == null ) {
            return null;
        }

        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setPhoneNumber( profileDTO.getPhoneNumber() );
        profileEntity.setCity( profileDTO.getCity() );
        profileEntity.setZipCode( profileDTO.getZipCode() );
        profileEntity.setCountry( profileDTO.getCountry() );

        return profileEntity;
    }

    @Override
    public ProfileDTO toDTO(ProfileEntity profileEntity) {
        if ( profileEntity == null ) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setPhoneNumber( profileEntity.getPhoneNumber() );
        profileDTO.setCountry( profileEntity.getCountry() );
        profileDTO.setZipCode( profileEntity.getZipCode() );
        profileDTO.setCity( profileEntity.getCity() );

        return profileDTO;
    }
}
