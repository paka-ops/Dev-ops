package com.example.taco2;


import lombok.*;


@Getter @Setter @AllArgsConstructor
public class Ingredient {
    private String id;
    private String name;
    private Type type;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }


    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}

