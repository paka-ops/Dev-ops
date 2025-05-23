package com.example.demo.repositories.implementations;

import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Faculty;
import com.example.demo.repositories.interfaces.IFacutly;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacultyRepository implements IFacutly {
    private IFacutly facutyRepository;

    private Faculty rowMapper(ResultSet rs,int numRow) throws SQLException {
        return new Faculty(
                rs.getString("facultyName"),
                rs.getString("doyen")
        );
    }
    private JdbcTemplate jdbcTemplate;
    public FacultyRepository(IFacutly facutyRepository,JdbcTemplate jdbc){
        this.facutyRepository = facutyRepository;
        this.jdbcTemplate = jdbc;
    }
    @Override
    public List<Faculty> findAll() throws ElementNotFoundException  {
        String sql = "select name,doyen from Faculty ";
        return jdbcTemplate.query(sql,this::rowMapper);
    }

    @Override
    public Faculty findOne(String id) throws ElementNotFoundException {
        String sql = "select name,doyen from Faculty where id = ?";
        return jdbcTemplate.queryForObject(sql,this::rowMapper);
    }
}
