package com.example.demo.repositories.implementations;

import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Faculty;
import com.example.demo.models.Student;
import com.example.demo.repositories.interfaces.IFacutly;
import com.example.demo.repositories.interfaces.IStudent;
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
@Repository
public class StudentRepository implements IStudent {
    private final JdbcTemplate jdbc;

    private final IFacutly facultyRepository;
    @Autowired
    public StudentRepository(JdbcTemplate jdbc,IFacutly facultyRepository){
        this.facultyRepository = facultyRepository;
        this.jdbc = jdbc;
    }

    private Student studentRowMapper(ResultSet rs,int numRow) throws SQLException {
        return new Student(
                rs.getLong("id"),
                rs.getString("personName"),
                rs.getString("secondName"),
                rs.getString("email"),
                rs.getString("telephone"),
                rs.getString("passwords"),
                rs.getDate("createdAt")
        );
    }
    private Long saveStudentAndReturnKey(Student student) throws SQLException{
        student.setCreatedAt(new Timestamp(new Date().getTime()));
        String sql = "insert into Person(name,secondName,email,passwords,createdAt) values(?,?,?,?,?)";
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP);
        pscFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
                 Arrays.asList(
                        student.getName(),
                        student.getSecondName(),
                        student.getEmail(),
                        student.getPassword(),
                        student.getCreatedAt()
                )
        );
        KeyHolder key = new GeneratedKeyHolder();

        int result = jdbc.update(psc,key);
        if(result!=0){
            return key.getKey().longValue();
        }else throw new SQLException("failled");


    }
    private boolean saveStudentWithFaculty(long studentId, String facultyId)  {
        try{
            if(!facultyRepository.findOne(facultyId).equals(new Faculty())){
                String sql = " insert into Student_Faculty(studentId,facultyId) values(?,?)";
                jdbc.update(sql,studentId,facultyId);
                return true;
            }else return false;

        }catch (ElementNotFoundException e){
            e.getMessage();
            return false;
        }

    }

    @Override
    public Student findById(long id) {
        String sql = "Select name,secondName,email,telephone from person where id  = ?";

        return null;
    }

    @Override
    public Student findByName(String name) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Boolean save(Student student) throws SQLException {
        long studentId = saveStudentAndReturnKey(student);
        String facId = student.getFaculty().getId();
        try {

            if (!facultyRepository.findOne(facId).equals(new Faculty())) {
                boolean result = saveStudentWithFaculty(studentId, facId);
                if (result) {
                    return true;
                } else return false;
            } else {
                return false;
            }
        } catch (ElementNotFoundException e) {
            e.getMessage();
            return false;

        }

    }

    @Override
    public Boolean delete(long id) {

        return null;
    }

}
