package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    private String id;
    private String name;
    private String doyen;

    public Faculty(String facultyName, String doyen) {
        this.name = facultyName;
        this.doyen = doyen;
    }

}
