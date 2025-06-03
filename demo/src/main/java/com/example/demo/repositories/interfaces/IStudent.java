package com.example.demo.repositories.interfaces;

import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Faculty;
import com.example.demo.models.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudent {
    Student findById(long id) throws ElementNotFoundException;
    Student findByName(String name) throws ElementNotFoundException;
    List<Student> findAll() throws ElementNotFoundException;
    Boolean save(Student student) throws SQLException;
    Boolean delete(long id) throws ElementNotFoundException;



}
