package com.example.demo.data.repositories;

import com.example.demo.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <UserEntity, Long> {
}
