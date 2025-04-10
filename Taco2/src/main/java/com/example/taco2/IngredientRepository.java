package com.example.taco2;

import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface IngredientRepository {
    public Ingredient findOneIngredient(String id) throws IngredientNotFoundException;
    public List<Ingredient> findAll() throws IngredientNotFoundException;
    public Ingredient saveIngredient(Ingredient ingredient) throws IngredientNotFoundException;
}
