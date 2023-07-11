package com.example.exception;

public class FileNotExists extends RuntimeException{
    public FileNotExists(String message){
        super(message);
    }
}
