package com.example.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

@NoArgsConstructor
public abstract class Person {
    private Long id;
    private String name;
    private String secondName;
    private String telephone;
    private String email;
    private String password;
    private Date createdAt;

    public Person(Long id, String name, String secondName, String telephone, String email, String password, Date createdAt) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }
}
