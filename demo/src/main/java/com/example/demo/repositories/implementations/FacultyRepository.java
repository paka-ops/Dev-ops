
package com.example.demo.repositories.implementations;

import com.example.demo.exceptions.ElementListNotFoundException;
import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.exceptions.SqlQueryFailedException;
import com.example.demo.models.Faculty;
import com.example.demo.repositories.interfaces.IFacutly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
@Repository
public class FacultyRepository implements IFacutly {

    private Faculty rowMapper(ResultSet rs,int numRow) throws SQLException {
        return new Faculty(
                rs.getString("id"),
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

        String sql = "select id ,facultyname,doyen from Faculty ";
        List<Faculty> faculties =  jdbcTemplate.query(sql,this::rowMapper);
        if(faculties.isEmpty() || faculties == null ){
            throw new ElementListNotFoundException("pas d' elements");

        }else return faculties;
    }

    @Override
    public Faculty findOne(String id) throws ElementNotFoundException {
        String sql = "select id,facultyname,doyen from Faculty where id = ?";
        Faculty result = jdbcTemplate.queryForObject(sql,this::rowMapper,id);

        if(result.equals(null)){
            throw new ElementNotFoundException("Element non trouvé ");
        }else {
            return result;
        }


    }

    @Override
    public Boolean save(Faculty faculty) throws SqlQueryFailedException {
        String sql = "insert into Faculty(id,name,doyen) values (?,?,?)";
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql, Types.LONGNVARCHAR,Types.LONGVARCHAR);
        pscFactory.setReturnGeneratedKeys(true);
        KeyHolder key = new GeneratedKeyHolder();
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
                Arrays.asList(
                    faculty.getId(),
                    faculty.getName(),
                    faculty.getDoyen()
                )
        );
        return null;
    }
}
