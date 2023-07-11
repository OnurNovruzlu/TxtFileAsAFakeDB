package com.example.util;

import com.example.dto.CPerson;
import com.example.dto.NewPerson;
import com.example.model.Person;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ModelConverter {
    public static Person toPerson(NewPerson newPerson){
        Person person = new Person();
        person.setName(newPerson.getName());
        person.setEmail(newPerson.getEmail());
        person.setPassword(newPerson.getPassword());
        return person;
    }
    public static CPerson fromPerson(Person person){
        CPerson cPerson = new CPerson();
        cPerson.setId(person.getId());
        cPerson.setName(person.getName());
        cPerson.setInsertDate(person.getInsertDate());
        cPerson.setEmail(person.getEmail());
        cPerson.setPassword(person.getPassword());
        return cPerson;
    }
}
