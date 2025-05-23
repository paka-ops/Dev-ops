package com.example.demo.repositories.implementations;

import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.models.Faculty;
import com.example.demo.models.Student;
import com.example.demo.repositories.interfaces.IFacutly;
import com.example.demo.repositories.interfaces.IStudent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StudentRepository implements IStudent {
    private JdbcTemplate jdbc;
    private IFacutly facutlyRepository;
    public StudentRepository(JdbcTemplate jdbc,IFacutly facutlyRepository){
        this.facutlyRepository = facutlyRepository;
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
    private Long saveStudentAndReturKey(Student student){
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
        jdbc.update(psc,key);
        return key.getKey().longValue();

    }
    private boolean saveStudentWithFaculty(long studentId, String facultyId){
        String sql = "insert into student_faculty(studentId,facultyId) values (?,?)";
        int result = jdbc.update(sql,studentId,facultyId);
        if(result > 0){
            return true;
        }else{
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
    public Boolean save(Student student) {
        long studentId = saveStudentAndReturKey(student);
        String facId = student.getFaculty().getId();
        try{

            if(!facutlyRepository.findOne(facId).equals(null)){
               boolean result =  saveStudentWithFaculty(studentId,facId);
               if(result){
                   return true;
               }else return false;
            }else return false;
        }catch (ElementNotFoundException e){
            e.getMessage();
            return false;
        }

    }

    @Override
    public Boolean delete(long id) {

        return null;
    }

}
