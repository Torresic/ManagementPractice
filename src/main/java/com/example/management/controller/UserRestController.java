package com.example.management.controller;

import com.example.management.exception.UserValidationException;
import com.example.management.model.User;
import com.example.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/management/api/")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("name/{name}")
    public List<User> getUser(@PathVariable String name) {
        return userService.findByName(name);
    }

    @PostMapping("new")
    public ResponseEntity<User> create(@RequestBody User user) throws UserValidationException {
        if (user.getBirthDate() == null && user.getName() == null) {
            throw new UserValidationException("La fecha y el nombre son necesarios para crear un usuario");
        }
        if (user.getBirthDate() == null) {
            throw new UserValidationException("La fecha de nacimiento es necesaria para crear un usuario");
        }
        if (user.getName() == null) {
            throw new UserValidationException("El nombre es necesario para crear un usuario");
        }
        User newUser = userService.create(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(newUser);
    }

    @GetMapping("id/{id}")
    public Optional<User> findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable long id,@RequestBody User userDetails) {
        User updateUser = userService.findById(id).orElseThrow();

        updateUser.setName(userDetails.getName());
        updateUser.setBirthDate(userDetails.getBirthDate());

        userService.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("date/{date}")
    public List<User> getUserByDate(@PathVariable LocalDate date) {
        List<User> updated = userService.findByBirthDate(date);
        return ResponseEntity.ok(updated).getBody();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        Optional<User> updated = userService.findById(id);
        return ResponseEntity.ok(updated).getBody();
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        List<User> deleted = userService.deleteById(id);
        return ResponseEntity.ok("Usuario borrado").getBody();
    }
}