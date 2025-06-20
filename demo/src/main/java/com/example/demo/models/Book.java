package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private Long isbn;
    private String bookName;
    private String author;
    private String description;
    private Category category;
    private String language;
    private String image;
    private String copies;




    public static enum Category{
        HISTORY,
        COMPUTERSCIENCE,
        SCIENCE,
        TECHNOLOGY,
        ART,
        ROMANCE,
        CLASSIQUE,
        PHILOSOPHIE,
        ADVENTURE,
        SCIENCE_FICTION
    }
}
