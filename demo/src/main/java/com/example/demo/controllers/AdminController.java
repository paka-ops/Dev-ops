package com.example.demo.controllers;


import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Admin;
import com.example.demo.repositories.interfaces.IAdmin;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {
    private IAdmin adminRepository;
    @Autowired
    public AdminController(IAdmin adminRepository){
        this.adminRepository = adminRepository;
    }
    @ModelAttribute(name = "admin")
    public Admin admin(){
        return new Admin();
    }
    @GetMapping("admin")
    public String  adminHomePage(Model model) throws ElementNotFoundException {
        List<Admin> admins = adminRepository.findAll();
        model.addAttribute("admins", admins);
        return "adminPage";
    }
    @DeleteMapping("admin/{id}")
    public String deleteAdmin(@PathVariable("id") long id) throws ElementNotFoundException {
        adminRepository.delete(id);
        return "redirect:admin";
    }
    @GetMapping("admin/{id}")
    public String findAdmin(@PathVariable("id") long id){
        try {
            adminRepository.findById(id);
            return "adminPage";
        }catch (ElementNotFoundException e){
            e.getMessage();
        }
        return "redirect: /admin";
    }

}
