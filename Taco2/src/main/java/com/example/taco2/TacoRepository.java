package com.example.taco2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Repository
public class TacoRepository implements ITaco{
    private JdbcTemplate jdbcTemplate;
    private Long saveTacoAndReturnId(Taco taco){
        taco.setCreatedAt(new Timestamp(new Date().getTime()));
        String sql = "insert into taco(name,createdAt) values (?,?)";
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql, Types.VARCHAR,Types.TIMESTAMP);
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
    private void saveTaco_Ingredeint(long tacoId,String  ingredientId){
        String sql = "insert into Taco_Ingredients(taco,ingredient) values (?,?)";
        jdbcTemplate.update(sql,tacoId,ingredientId);
    }
    private Taco tacoRowMapper(ResultSet rs,int numRow) throws SQLException {
        return new Taco(
                rs.getLong("id"),
                rs.getString("name")
        );
    }
    public TacoRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}
    @Override
    public List<Taco> findAll() {
        String sql ="select id,name from Taco";
        return jdbcTemplate.query(sql,this::tacoRowMapper);

    }

    @Override
    public void saveTaco(Taco taco) {
        Long tacoId= saveTacoAndReturnId(taco);
        for (String ingredientId: taco.getIngredients())
        {
            saveTaco_Ingredeint(tacoId,ingredientId);
        }

    }

    @Override
    public Taco findOne(long id) {
        String sql = "SELECT  name, createdAt FROM TACO where id = ?";

        return jdbcTemplate.queryForObject(sql,this::tacoRowMapper,id);
    }
}
