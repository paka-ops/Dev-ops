package com.example.demo.repositories.interfaces;

import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Faculty;

import java.util.List;

public interface IFacutly {
    List<Faculty> findAll() throws ElementNotFoundException;
    Faculty findOne(String id) throws ElementNotFoundException;
}
