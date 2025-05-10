package com.example.taco2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
 @Data @NoArgsConstructor
public class Ingredient {
    @Id
    private String ingredientId;
    private String name;
    private Type type;

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Ingredient(String ingredientId, String name, Type type) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.type = type;
    }

    public static  enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE

    }

}
