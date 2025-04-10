package com.example.taco2;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IngredientImplement implements  IngredientRepository{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public IngredientImplement(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Ingredient findOneIngredient(String id) {

        return null;
    }

    @Override
    public List<Ingredient> findAll() {
        return null;
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {

        return null;
    }
    private String getIngredient(Ingredient ingredient){

        return ingredient.getName();
    }
    public Ingredient IngredientRowMapper(ResultSet rs,int row) throws IngredientNotFoundException,SQLException {
        if (rs != null) {
            return new Ingredient(
                    rs.getString("id"),
                    rs.getString("name"),
                    Ingredient.Type.valueOf(rs.getString("type"))
            );
        } else {
            throw new IngredientNotFoundException("erreur l' objet n' existe pas");
        }
    }

}
