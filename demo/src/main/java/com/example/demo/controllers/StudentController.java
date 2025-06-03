package com.example.demo.controllers;

import com.example.demo.exceptions.ElementListNotFoundException;
import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Faculty;
import com.example.demo.models.Person;
import com.example.demo.models.Student;
import com.example.demo.repositories.interfaces.IFacutly;
import com.example.demo.repositories.interfaces.IStudent;
import jakarta.validation.Valid;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class StudentController {
    private IStudent studentRepository;
    private IFacutly facultyRepository;
    @Autowired
    private StudentController(IStudent student,IFacutly facultyRepository){
        this.studentRepository = student;
        this.facultyRepository = facultyRepository;
    }
    @ModelAttribute(name = "student")
    public Student getStudent(){
        return new Student();
    }
    @GetMapping("/form")
    public String getForm(Model model) {
        try{

                List<Faculty> faculties = facultyRepository.findAll();
                model.addAttribute("faculties",faculties);

        }catch (ElementListNotFoundException e) {
           System.out.println(e.getMessage());
           model.addAttribute("message",e.getMessage());
        }
        return "signUp";

    }
    @PostMapping
    public String processForm(@Valid Student student  , Errors errors) throws SQLException {
        if(errors.hasErrors()){
            return "signUp";
        }
        studentRepository.save(student);
        return null;

    }
}
