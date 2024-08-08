package com.example.demo.data.repositories;

import com.example.demo.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);
}
