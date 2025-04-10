package com.example.taco2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
public class Taco {
    private Long id;
    private String name;
    private List<Ingredient> ingredients;

}
