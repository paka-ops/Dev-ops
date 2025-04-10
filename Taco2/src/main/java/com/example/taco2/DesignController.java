package com.example.taco2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class DesignController implements IngredientRepository {
    private JdbcTemplate jdbcTemplate;
    public DesignController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Ingredient findOneIngredient(String id) throws IngredientNotFoundException {
        String sql ="Select id,name,type from ingredient where id = ?";
        return jdbcTemplate.queryForObject(sql,this::RowMapper,id);

    }

    @Override
    public List<Ingredient> findAll() throws IngredientNotFoundException {
        String sql = "Select id,name,type from ingredient";
        return jdbcTemplate.query(sql,this::RowMapper);
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) throws IngredientNotFoundException {
        return null;
    }

    public String getForm(Model model){

        return "designForm";
    }
    public Ingredient RowMapper(ResultSet rs,int row) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"))
        );
    }
}
