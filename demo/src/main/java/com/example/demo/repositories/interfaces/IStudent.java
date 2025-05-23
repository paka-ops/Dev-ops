package com.example.demo.repositories.interfaces;

import com.example.demo.models.Faculty;
import com.example.demo.models.Student;

import java.util.List;

public interface IStudent {
    Student findById(long id);
    Student findByName(String name);
    List<Student> findAll();
    Boolean save(Student student);
    Boolean delete(long id);



}
