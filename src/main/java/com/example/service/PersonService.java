package com.example.service;

import com.example.dto.CPerson;
import com.example.dto.NewPerson;

import java.util.List;

public interface PersonService {
    String insert(NewPerson newPerson);
    List<CPerson> getAll();
    CPerson getById(int id);
    String delete(int id);
    String update(int id, NewPerson newPerson);
    String reset();
    String login(String email,String password);
}
