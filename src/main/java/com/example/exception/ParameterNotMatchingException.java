package com.example.exception;

public class ParameterNotMatchingException extends RuntimeException{
    public ParameterNotMatchingException(String message){
        super(message);
    }
}
