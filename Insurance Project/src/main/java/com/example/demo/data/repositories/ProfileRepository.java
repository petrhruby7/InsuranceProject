package com.example.demo.data.repositories;

import com.example.demo.data.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository <ProfileEntity, Long> {
}
