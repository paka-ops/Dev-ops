package com.example.demo.repositories.interfaces;

import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Admin;

import java.util.List;

public interface IAdmin {
    Admin findById(long id) throws ElementNotFoundException;
    Admin findByName(String name)throws ElementNotFoundException;
    List<Admin> findAll() throws ElementNotFoundException;
    Boolean save(Admin admin);
    Boolean delete(long id);

}
