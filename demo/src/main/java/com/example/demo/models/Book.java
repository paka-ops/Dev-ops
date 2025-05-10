package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long isbn;
    private String bookName;
    private String Author;
    private Category category;
    private String language;

    private static enum Category{
        HISTORY,
        COMPUTERSCIENCE,
        SCIENCE,
        TECHNOLOGY,
        ART,
        ROMANCE
    }
}
