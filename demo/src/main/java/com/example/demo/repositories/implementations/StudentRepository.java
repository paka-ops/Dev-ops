
package com.example.demo.repositories.implementations;

import com.example.demo.exceptions.ElementListNotFoundException;
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
                rs.getString("password"),
                rs.getDate("createdAt")
        );
    }
    private Long saveStudentAndReturnKey(Student student) throws SQLException{
        student.setCreatedAt(new Timestamp(new Date().getTime()));
        String sql = "insert into Person(personName,secondName,email,passwordd,createdAt) values(?,?,?,?,?)";
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
        }else throw new SQLException("failed");


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
    public Student findById(long id) throws ElementNotFoundException {
        String sql = "select name,secondName,email,telephone from person where id  = ?";
        Student student = jdbc.queryForObject(sql,this::studentRowMapper,id);
        if(student != null){
            return student;
        }else throw new ElementNotFoundException("pas d' element trouvé");


    }

    @Override
    public Student findByName(String name) throws ElementNotFoundException {
        String sql = "select name,secondName,email,telephone from person where name  = ? " ;
        Student student =  jdbc.queryForObject(sql,this::studentRowMapper,name);
        if(student != null) {
            return student;
        }else throw new ElementNotFoundException("pas d' element ");

    }

    @Override
    public List<Student> findAll() throws ElementListNotFoundException {
        String sql = "select id,personName,secondName,email,telephone,password,createdAt from Person where roles = ?" ;
        List<Student> students = jdbc.query(sql,this::studentRowMapper,"student");
        if(students.isEmpty()) {
            throw new ElementListNotFoundException("pas d' element ");

        }else  return students;
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
    public Boolean delete(long id) throws ElementNotFoundException {
        String sql = "delete from person where id = ?";
        int result = jdbc.update(sql,id);
        if(result != 0){
            return true;
        }else throw new ElementNotFoundException("pas d' element trouvé");
    }

}
