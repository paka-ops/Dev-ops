package com.example.demo.repositories.interfaces;

import com.example.demo.models.Book;

public interface IBook{
    Book findById(long id);
    Book findByName(String name);
    Boolean save(Book book);
    Boolean saveBookAndStudent(long bookId,long studentId);
}