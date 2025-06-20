package com.example.demo.controllers;

import com.example.demo.exceptions.ElementListNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.repositories.interfaces.IBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {
    private IBook bookRepository;

    public BookController(IBook bookRepository){
        this.bookRepository = bookRepository;
    }
    @ModelAttribute(name = "book")
    public Book book(){
        return new Book();
    }
    @GetMapping("books")
    public String bookPage(Model model){
        try {
            List<Book> books = bookRepository.getAllBook();
            model.addAttribute("books",books);
        }catch (ElementListNotFoundException e){
            model.addAttribute("message",e.getMessage());
        }
        return "book/bookPage";
    }
}
