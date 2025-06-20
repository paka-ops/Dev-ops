package com.example.demo.repositories.implementations;

import com.example.demo.exceptions.ElementListNotFoundException;
import com.example.demo.exceptions.ElementNotFoundException;
import com.example.demo.exceptions.SqlQueryFailedException;
import com.example.demo.models.Book;
import com.example.demo.repositories.interfaces.IBook;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Repository
public class BookRepository implements IBook {

    private SimpleJdbcInsert bookInsert;
    private ObjectMapper objectMapper;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookRepository(JdbcTemplate jdbc){
        this.bookInsert = new SimpleJdbcInsert(jdbc).withTableName("Book");
        this.objectMapper = new ObjectMapper();
        this.jdbcTemplate = jdbc;
    }

    private Book bookRowMapper(ResultSet rs,int numRow) throws SQLException {
        return new Book(
                rs.getLong("id"),
                rs.getLong("isbn"),
                rs.getString("book_name"),
                rs.getString("Author"),
                rs.getString("description"),
                Book.Category.valueOf(rs.getString("category").toUpperCase()),
                rs.getString("book_language"),
                rs.getString("image"),
                rs.getString("copies")

        );
    }

    @Override
    public Book findById(long id) throws ElementNotFoundException {
        String sql = "select * from book where id = ?";
        Book book = jdbcTemplate.queryForObject(sql,this::bookRowMapper,id);
        if(book != null){
            return book;
        }else throw new ElementNotFoundException("le livre n' existe pas");
    }

    @Override
    public Book findByName(String name) throws ElementNotFoundException {
        String sql = "select from book where name = ?";
        Book book = jdbcTemplate.queryForObject(sql,this::bookRowMapper,name);
        if(book != null){
            return book;
        }else throw new ElementNotFoundException("le livre n' existe pas");
    }

    @Override
    public Boolean save(Book book) throws SqlQueryFailedException {
        Map<String,Object> values = objectMapper.convertValue(book,Map.class);
        int result = bookInsert.execute(values);
        if(result != 0){
            String sql =" update select";
            return true;
        }else throw new SqlQueryFailedException("l' insertion du livre a echouer");

    }

    @Override
    public Boolean saveBookAndStudent(long bookId, long studentId) throws ElementNotFoundException,SqlQueryFailedException {
        String sql = "insert into Student_Faculty values(?,?)";
        int number = jdbcTemplate.update(sql,bookId,studentId);
        if(number != 0){
            return true;
        }else throw new SqlQueryFailedException("la requette a échoué");
    }

    @Override
    public Boolean deleteBook(Long id) throws SqlQueryFailedException,ElementNotFoundException {
        String sql = "delete from book where id = ?";
        Book book = findById(id);
        if(book == null) {
            throw new ElementNotFoundException("le livre n' existe pas");
        }else{
            int result = jdbcTemplate.update(sql, id);
            if (result != 0) {
                return true;
            } else throw new SqlQueryFailedException("la requete de suppression du livre  a échoué");
        }
    }

    @Override
    public List<Book> getAllBook() throws ElementListNotFoundException {
        String sql = "select * from book";
        List<Book> books = jdbcTemplate.query(sql,this::bookRowMapper);
        if(!books.isEmpty()){
            return books;
        }else throw new ElementListNotFoundException("pas d' elements");
    }
}
