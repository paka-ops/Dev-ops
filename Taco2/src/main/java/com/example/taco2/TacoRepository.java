package com.example.taco2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Repository
public class TacoRepository implements ITaco{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public TacoRepository(JdbcTemplate jdbc){
        this.jdbcTemplate = jdbc;
    }
    private Taco tacoRowMapper(ResultSet rs,int numRow) throws SQLException {
        return  new Taco(
                rs.getString("name"),
                new Date(rs.getLong("createdAt"))
        );
    }
    private Long saveTacoAndReturnId(Taco taco){
       taco.setCreatedAt(new Timestamp(new Date().getTime()));
       String sql = "insert into Taco(name,createdAt) values (?,?)";
       PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql,Types.VARCHAR,Types.TIMESTAMP);
       pscFactory.setReturnGeneratedKeys(true);
       PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
               Arrays.asList(
                       taco.getName(),
                       taco.getCreatedAt()
               )
       );
       KeyHolder key = new GeneratedKeyHolder();
       jdbcTemplate.update(psc,key);
       return key.getKey().longValue();
    }
    private void saveTacoAndIngredient(String ingredientId,Long tacoId){
        String sql = "insert into taco_Ingredients(taco,ingredient) values (?,?)";
        jdbcTemplate.update(sql,tacoId,ingredientId);

    }
    @Override
    public List<Taco> findAll() {
        String sql = "select name,createdAt from Taco";
        List<Taco> tacos = jdbcTemplate.query(sql,this::tacoRowMapper);
        return tacos;
    }

    @Override
    public void saveTaco(Taco taco) {
      Long tacoId =  saveTacoAndReturnId(taco);
        for (String ingredient:taco.getIngredients()) {
            saveTacoAndIngredient(ingredient,tacoId);

        }
    }

    @Override
    public Taco findOne(long id) {
        String sql = "select name,createdAt from taco where id = ?";
        Taco taco = jdbcTemplate.queryForObject(sql,this::tacoRowMapper,id);
        return taco;
    }

}
