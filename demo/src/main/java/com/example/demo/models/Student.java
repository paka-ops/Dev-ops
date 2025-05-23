package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data

@NoArgsConstructor

public class Student extends Person{
    private Faculty faculty;
    private List<Book> books;

    public Student(Long id, String name, String secondName, String telephone, String email, String password, Date createdAt, Faculty faculty, List<Book> books) {
        super(id, name, secondName, telephone, email, password, createdAt);
        this.faculty = faculty;
        this.books = books;
    }
    public Student(Long id, String name, String secondName, String telephone, String email, String password, Date createdAt) {
        super(id, name, secondName, telephone, email, password, createdAt);
        this.faculty = faculty;
    }
}
