package com.example.taco2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class IngredientRepository implements IIngredient{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public  IngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Ingredient> findAll() {
        String sql = "select id, name,type from Ingredient";

        return  jdbcTemplate.query(sql,this::rowMapper);
    }

    @Override
    public Ingredient findOneIngredient(String id) {
        String sql = "select * from Ingredient where id = ?";
        return jdbcTemplate.queryForObject(sql,this::rowMapper,id);

    }

    @Override
    public Ingredient saveOneIngredient(Ingredient ingredient) {
        String sql = "insert into Ingredient(id,name,type) value (?,?,?)";
        jdbcTemplate.update(sql,ingredient.getIngredientId(),ingredient.getName(),ingredient.getType());
        return findOneIngredient(ingredient.getIngredientId());
    }
    private Ingredient rowMapper(ResultSet rs,int row) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"))
        );
    }


}
