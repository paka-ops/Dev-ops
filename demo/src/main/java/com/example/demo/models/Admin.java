package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Person{
    private Role role;
    private Boolean actif;
    private static enum Role{
        SuperAdmin,
        Manager
    }
}
