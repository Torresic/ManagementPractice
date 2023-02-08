package com.example.management.service;

import com.example.management.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    User save(User user);

    List<User> findByName(String name);
    List<User> findAll();

    List<User> findByBirthDate(LocalDate date);

    Optional<User> findById(Long id);

    public List<User> deleteById(Long id);
}
