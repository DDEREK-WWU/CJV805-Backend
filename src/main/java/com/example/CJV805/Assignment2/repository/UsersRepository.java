package com.example.CJV805.Assignment2.repository;

import com.example.CJV805.Assignment2.model.Users;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
    
    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);

}
