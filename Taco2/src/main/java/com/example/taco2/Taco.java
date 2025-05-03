package com.example.taco2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;


public class Taco {
    private long tacoId;

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @NotBlank(message ="Veuillez saisir la valeur")
    @Size(min=3)
    private String name;
    @NotNull
    private List<String> ingredients;
    private Date createdAt;
    public Taco(long id,String name){
        this.tacoId = id;
        this.name = name;
    }
    public Taco(String name,Date createdAt){

        this.name = name;
        this.createdAt = createdAt;
    }
    public Taco(long tacoId, String name,Date createdAt, List<String> ingredients) {
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
