
package com.example.demo.controllers;


import com.example.demo.exceptions.ElementListNotFoundException;
import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.exceptions.SqlQueryFailedException;
import com.example.demo.models.Admin;
import com.example.demo.models.Student;
import com.example.demo.repositories.interfaces.IAdmin;
import com.example.demo.repositories.interfaces.IBook;
import com.example.demo.repositories.interfaces.IStudent;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("admin")
@Controller
public class AdminController {
    private IAdmin adminRepository;
    private IStudent studentRepository;
    private IBook bookRepository;
    @Autowired
    public AdminController(IAdmin adminRepository,IStudent studentRepository,IBook bookRepository){
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
    }
    @ModelAttribute(name = "admin")
    public Admin admin(){
        return new Admin();
    }
    @GetMapping("admin")
    public String  adminHomePage(Model model) throws ElementNotFoundException {
        try {
            List<Admin> admins = adminRepository.findAll();
            model.addAttribute("admins", admins);

        }catch (ElementListNotFoundException e){
            model.addAttribute("message",e.getMessage());
        }
        return "adminPage";
    }
    @DeleteMapping("delete/{id}")
    public String deleteAdmin(@PathVariable("id") long id) throws ElementNotFoundException {
        adminRepository.delete(id);
        return "redirect:/admin";
    }
    @GetMapping("admin/{id}")
    public String findAdmin(@PathVariable("id") long id){
        try {
            adminRepository.findById(id);
            return "adminPage";
        }catch (ElementNotFoundException e){
            e.getMessage();
        }
        return "redirect:admin";
    }
    @GetMapping("/students")
    public String getAllStudents(Model model){
        try {
            List<Student> students = new ArrayList<>();
            students =   studentRepository.findAll();
            model.addAttribute("students",students);
            return "studentPage";
        }catch (ElementListNotFoundException e){
            model.addAttribute("message",e.getMessage());
            return "studentPage";
        }
    }
    @DeleteMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id,Model model){
        try {

            studentRepository.delete(id);
        }catch (ElementNotFoundException e){
            e.getMessage();
        }catch (SqlQueryFailedException a){
            a.getMessage();
        }

        return "redirect:admin/admin";
    }
    @DeleteMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id,Model model){
        try{
            bookRepository.deleteBook(id);
            return "redirect:http://localhost:8080/book/books";
        }catch (ElementNotFoundException e){
            model.addAttribute("message",e.getMessage());
            return "redirect:http://localhost:8080/book/books";
        }catch (SqlQueryFailedException a){
            model.addAttribute("message",a.getMessage());
            return "redirect:http://localhost:8080/book/books";
        }
    }



}
