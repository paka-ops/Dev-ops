package com.example.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data



public class Student extends Person{

    private Faculty faculty ;
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
    public Student(){
        this.faculty = new Faculty();
    }
}
