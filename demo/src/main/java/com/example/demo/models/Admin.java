package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Admin extends Person{

    private Role role;
    private Boolean actif;



    public static enum Role{
        Admin,
        SuperAdmin,
        Manager
    }

    public Admin(long id, String name, String secondName, String telephone, String email, String password , Date date, Role role, Boolean actif) {
        super(id, name, secondName, telephone, email, password,date);
        this.role = role;
        this.actif = actif;
    }
}
