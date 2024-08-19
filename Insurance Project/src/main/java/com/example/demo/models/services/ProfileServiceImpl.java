package com.example.demo.models.services;

import com.example.demo.data.entities.ProfileEntity;
import com.example.demo.data.repositories.ProfileRepository;
import com.example.demo.models.dto.ProfileDTO;
import com.example.demo.models.dto.mappers.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    public ProfileDTO create(ProfileDTO profileDTO){
        ProfileEntity profileEntity = profileMapper.toEntity(profileDTO);
        profileEntity = profileRepository.saveAndFlush(profileEntity);
        return profileMapper.toDTO(profileEntity);

    }
}
