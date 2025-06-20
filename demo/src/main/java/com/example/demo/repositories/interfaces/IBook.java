package com.example.demo.repositories.interfaces;

import com.example.demo.exceptions.ElementListNotFoundException;
import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.exceptions.SqlQueryFailedException;
import com.example.demo.models.Book;

import java.util.List;

public interface IBook{
    Book findById(long id) throws ElementNotFoundException;
    Book findByName(String name) throws ElementNotFoundException;
    Boolean save(Book book) throws SqlQueryFailedException;
    Boolean saveBookAndStudent(long bookId,long studentId) throws ElementNotFoundException,SqlQueryFailedException;
    Boolean deleteBook(Long id) throws SqlQueryFailedException,ElementNotFoundException;
    List<Book> getAllBook() throws ElementListNotFoundException;
}