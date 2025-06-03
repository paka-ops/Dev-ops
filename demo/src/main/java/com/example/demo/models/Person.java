
package com.example.demo.models;

import jakarta.validation.constraints.Email;
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
    @NotBlank(message = "name is requierd ")
    private String name;
    @NotBlank(message = "second name is requierd")
    private String secondName;
    @NotBlank(message = "telphone is requierd")
    private String telephone;
    @Email
    @NotBlank(message = "email is requierd")
    private String email;
    @Size(min = 8 ,max = 12)
    @NotBlank(message = "passowrd is requierd")
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
