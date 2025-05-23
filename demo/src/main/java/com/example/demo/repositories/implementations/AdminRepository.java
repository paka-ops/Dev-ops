package com.example.demo.repositories.implementations;

import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Admin;
import com.example.demo.repositories.interfaces.IAdmin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

@Repository
public class AdminRepository implements IAdmin {
    private JdbcTemplate jdbc;
    @Autowired
    public AdminRepository(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }
    private Admin adminRowMapper(ResultSet rs,int numRow) throws SQLException {
        return new Admin(
                rs.getLong("id"),
                rs.getString("personName"),
                rs.getString("secondName"),
                rs.getString("email"),
                rs.getString("telephone"),
                rs.getString("passwords"),
                rs.getDate("createdAt"),
                Admin.Role.valueOf(rs.getString("roles")),
                rs.getBoolean("isActif")

                );
    }
    private  Boolean saveAdmin(Admin admin){
        admin.setCreatedAt(new Date());
        Map<String,Object> values = new ObjectMapper().convertValue(admin,Map.class);
        String sql = "insert into Peron(personName,secondName,email,telephone,password ,createdAt,roles,isActif) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatementCreatorFactory pscFactory =
                new PreparedStatementCreatorFactory(sql,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.VARCHAR,
                        Types.TIMESTAMP,
                        Types.VARCHAR,
                        Types.VARCHAR
                );
        pscFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
                Arrays.asList(
                        admin.getName(),
                        admin.getSecondName(),
                        admin.getEmail(),
                        admin.getTelephone(),
                        admin.getPassword(),
                        admin.getRole(),
                        admin.getCreatedAt().getTime(),
                        admin.getActif()
                )
        );
        int affectedRow = jdbc.update(psc);
        if(affectedRow != 0){
            return true;
        }else return false;
    }

    @Override
    public Admin findById(long id) throws ElementNotFoundException {
        String sql = "select * from Person where role = 'Admin' or role = 'superAdmin' and id = ?";
        Admin admin = jdbc.queryForObject(sql,this::adminRowMapper,id);
        if(!admin.equals(null)){
            return admin;
        }else {
            throw new ElementNotFoundException("Admin " + id + " non trouvé");
        }
    }

    @Override
    public Admin findByName(String name) throws ElementNotFoundException {
        String sql = "select * from Person where role = 'admin' or role = 'superAdmin' and name  = ?";
        Admin admin = jdbc.queryForObject(sql,this::adminRowMapper,name);
        if(!admin.equals(null)){
            return admin;
        }else {
            throw new ElementNotFoundException("Admin " + name + " non trouvé ");
        }
    }

    @Override
    public List<Admin> findAll() throws ElementNotFoundException {
        String sql = "select * from Person";
        List<Admin> admins =  jdbc.query(sql,this::adminRowMapper);
        if(!admins.equals(null)){
            return admins;
        }else {
            throw new  ElementNotFoundException("pas d' admin dans la base de donnée veuillez contacter l' administrateur");
        }
    }

    @Override
    public Boolean save(Admin admin) {
        return saveAdmin(admin);
    }

    @Override
    public Boolean delete(long id) {
        String sql = "delete from Person where id = ?";
        int result = jdbc.update(sql,id);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

}
