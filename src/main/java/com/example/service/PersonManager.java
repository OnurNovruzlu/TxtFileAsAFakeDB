package com.example.service;

import com.example.dto.CPerson;
import com.example.dto.NewPerson;
import com.example.exception.ModelNotFoundException;
import com.example.exception.ParameterNotMatchingException;
import com.example.model.Person;
import com.example.repository.PersonRepository;
import com.example.util.ModelConverter;
import com.example.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonManager implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public String insert(NewPerson newPerson){
        if(ParameterValidator.validate(newPerson.getEmail())) throw new ParameterNotMatchingException("Email does not match");
        if(newPerson.getPassword()!=null && ParameterValidator.validatePsw(newPerson.getPassword()))throw new ParameterNotMatchingException("Password must be 4 digit at least");
        List<CPerson> list = getAll();
        for (CPerson p : list){
            if(p.getEmail().equals(newPerson.getEmail()))throw new ParameterNotMatchingException("This email already exists");
        }
        personRepository.insert(ModelConverter.toPerson(newPerson));
        return "ok";
    }

    @Override
    public List<CPerson> getAll() {
        return personRepository.getAll().stream().map(ModelConverter::fromPerson).toList();
    }

    @Override
    public CPerson getById(int id) {
        Person person = personRepository.findById(id);
        if(person.getId()==0)throw new ModelNotFoundException("Person not found with this id:"+id);
        else return ModelConverter.fromPerson(person);
    }

    @Override
    public String delete(int id) {
        Person person = personRepository.findById(id);
        if(person.getId()==0)throw new ModelNotFoundException("Person not found with this id:"+id);
        else {
            personRepository.delete(person);
            return "ok";
        }
    }

    @Override
    public String update(int id, NewPerson newPerson) {
        if(ParameterValidator.validate(newPerson.getEmail())) throw new ParameterNotMatchingException("Email does not match");
        if(ParameterValidator.validatePsw(newPerson.getPassword()))throw new ParameterNotMatchingException("Password must be 4 digit at least");

        Person personByID = personRepository.findById(id);
        if(personByID.getId()==0)throw new ModelNotFoundException("Person not found with this id:"+id);
        personByID.setName(newPerson.getName());

        personRepository.update(personByID);
        return "ok";
    }

    @Override
    public String reset() {
        personRepository.clearFile();
        return "File has been cleared!";
    }

    @Override
    public String login(String email, String password) {
        Person person = personRepository.findByEmail(email);
        if(person.getId() == 0)throw new ModelNotFoundException("Person not found with this email: "+email);

        if(person.getPassword() == null){
            if(password == null) return "Welcome " + person.getName();
            else throw new ParameterNotMatchingException("Please check if you have set a password");
        }

        if(!person.getPassword().equals(password)) throw new ParameterNotMatchingException("Please enter correct password");

        return "Welcome " + person.getName();
    }

}
