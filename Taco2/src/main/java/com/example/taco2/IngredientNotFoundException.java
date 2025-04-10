package com.example.taco2;

import java.sql.SQLException;

public class IngredientNotFoundException extends SQLException {
    public IngredientNotFoundException(String message){
        super(message);
    }
}
