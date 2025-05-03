package com.example.taco2;

import java.util.List;

public interface IIngredient {
    List<Ingredient> findAll();
    Ingredient findOneIngredient(String id);
    Ingredient saveOneIngredient(Ingredient ingredient);
}
