package com.example.management.model;

import jakarta.persistence.*;
import org.wildfly.common.annotation.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "Usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "birthDate")
    @NotNull
    private LocalDate birthDate;

    public User() {

    }


}
