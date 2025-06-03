package com.example.demo.repositories.interfaces;

import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Admin;

import java.sql.SQLException;
import java.util.List;

public interface IAdmin {
    Admin findById(long id) throws ElementNotFoundException;
    Admin findByName(String name)throws ElementNotFoundException;
    List<Admin> findAll() throws ElementNotFoundException;
    Boolean save(Admin admin) throws SQLException;
    Boolean delete(long id) throws ElementNotFoundException;

}
