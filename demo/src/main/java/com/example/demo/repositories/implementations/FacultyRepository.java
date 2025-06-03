
package com.example.demo.repositories.implementations;

import com.example.demo.exceptions.ElementListNotFoundException;
import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Faculty;
import com.example.demo.repositories.interfaces.IFacutly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class FacultyRepository implements IFacutly {

    private Faculty rowMapper(ResultSet rs,int numRow) throws SQLException {
        return new Faculty(
                rs.getString("facultyName"),
                rs.getString("doyen")
        );
    }
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public FacultyRepository(JdbcTemplate jdbc){

        this.jdbcTemplate = jdbc;
    }
    @Override
    public List<Faculty> findAll() throws ElementListNotFoundException {

        String sql = "select facultyname,doyen from Faculty ";
        List<Faculty> faculties =  jdbcTemplate.query(sql,this::rowMapper);
        if(faculties.isEmpty() || faculties == null ){
            throw new ElementListNotFoundException("pas d' elements");

        }else return faculties;
    }

    @Override
    public Faculty findOne(String id) throws ElementNotFoundException {
        String sql = "select name,doyen from Faculty where id = ?";
        Faculty result = jdbcTemplate.queryForObject(sql,this::rowMapper);
        if(result.equals(null)){
            throw new ElementNotFoundException("Element non trouvé ");
        }else {
            return result;
        }
    }
}
