package com.example.demo.data.repositories;

import com.example.demo.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <UserEntity, Long> {

    //vyhledávání entity dle User name
    Optional<UserEntity> findByUserName(String userName);
    //vyhledávání entity dle emailu
    Optional<UserEntity> findByEmail(String email);

}
