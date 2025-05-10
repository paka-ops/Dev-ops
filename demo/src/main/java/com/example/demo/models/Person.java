package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {
    private Long id;
    private String name;
    private String secondName;
    private String telephone;
    private String email;
    private String password;

}
