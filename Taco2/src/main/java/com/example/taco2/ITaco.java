package com.example.taco2;

import java.util.List;

public interface ITaco {
    List<Taco> findAll();
    void saveTaco(Taco taco);
    Taco findOne(long id);

}
