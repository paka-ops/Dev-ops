package com.example.demo.exceptions;

public class SqlQueryFailedException extends Exception{
    private String messagge;
    public SqlQueryFailedException(String messagge){
        super(messagge);
    }
}
