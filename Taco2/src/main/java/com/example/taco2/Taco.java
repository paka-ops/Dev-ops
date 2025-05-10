package com.example.taco2;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity

public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tacoId;
    @NotBlank(message ="Veuillez saisir la valeur")
    @Size(min=3)
    private String name;

    @NotNull
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;
    private Date createdAt;
    public Taco(long id, String name){
        this.tacoId = id;
        this.name = name;
    }
    public Taco(String name,Date createdAt){

        this.name = name;
        this.createdAt = createdAt;
    }
    public Taco(long tacoId, String name,Date createdAt, List<Ingredient> ingredients) {
        this (name,createdAt);
        this.tacoId = tacoId;
        this.ingredients = ingredients;
    }
    public Taco(){

    }

    public long getTacoId() {
        return tacoId;
    }

    public void setTacoId(long tacoId) {
        this.tacoId = tacoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public Date getCreatedAt() {
        return this.createdAt;
    }
    @PrePersist

    public void setCreatedAt() {
        this.createdAt = new Date();
    }
}
