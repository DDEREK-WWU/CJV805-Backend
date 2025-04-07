package com.example.CJV805.Assignment2.service;
import com.example.CJV805.Assignment2.model.Users;
import com.example.CJV805.Assignment2.repository.UsersRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Users registerUsers(Users users) {

        if (usersRepository.existsByEmail(users.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(hashedPassword);
        return usersRepository.save(users);
    }

    public boolean authenticateUsers(String email, String password) {
        Optional<Users> optionalUsers = usersRepository.findByEmail(email);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            return passwordEncoder.matches(password, users.getPassword());
        }
        return false;
    }

    public Users getUserByEmail (String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }

    public Users getUserById(String id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }
}
