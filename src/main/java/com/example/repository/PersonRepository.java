package com.example.repository;

import com.example.exception.FileNotExists;
import com.example.model.Person;
import com.example.util.AssistantMethod;
import com.example.util.MainMethod;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    final String tablePath = "D:\\LEARNING\\FakeDBApp\\database\\product.txt";
    final File file = new File(tablePath);
    private final List<Integer> deletedIndexes = new ArrayList<>();

    @AssistantMethod(message = "Validate person id`s")
    public void validateID(Person person){
        List<Person> personList = getAll();
        Person lastPerson = personList.get(personList.size() - 1);

        if(deletedIndexes.size()==0 || lastPerson.getId() > deletedIndexes.get(deletedIndexes.size() - 1)) {
            person.setId(lastPerson.getId() + 1);
        }
        else {
            person.setId(getBiggest(deletedIndexes) + 1);
            deletedIndexes.clear();
        }
    }

    @AssistantMethod(message = "Get the biggest element in given list")
    public int getBiggest(List<Integer> list){
        int max = 0;
        for (Integer integer : list) {
            if (integer > max) max = integer;
        }
        return max;
    }

    @MainMethod(message = "CRUD - INSERT")
    public void insert(Person person){

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            if (file.length() == 0) {

                FileOutputStream fileOut = new FileOutputStream(tablePath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                person.setId(1);
                out.writeObject(person);
                out.close();
                fileOut.close();

            } else {

                FileInputStream fileIn = new FileInputStream(tablePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Object obj = in.readObject();
                in.close();
                fileIn.close();
                validateID(person);

                if (obj instanceof Person currentData) {

                    List<Person> list = new ArrayList<>();
                    list.add(currentData);
                    list.add(person);

                    FileOutputStream fileOut = new FileOutputStream(tablePath);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(list);
                    out.close();
                    fileOut.close();

                }
                else {

                    List<Person> personList = (List<Person>) obj;
                    personList.add(person);
                    FileOutputStream fileOut = new FileOutputStream(tablePath);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(personList);
                    out.close();
                    fileOut.close();

                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @MainMethod(message = "CRUD - GET_ALL")
    public List<Person> getAll(){

        List<Person> list = new ArrayList<>();
        if (file.length() == 0){
            return list;
        }
        try{
            FileInputStream fileIn = new FileInputStream(tablePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object obj = in.readObject();
            in.close();
            fileIn.close();

            if (obj instanceof Person currentData) {
                list.add(currentData);
                return list;
            }
            else {
                return (List<Person>) obj;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    @MainMethod(message = "CRUD - GET_BY_ID")
    public Person findById(int id){
        if(!file.exists())throw new FileNotExists("Add person for creating file");
        List<Person> list = getAll();
        Person person = new Person();
        for(Person p:list){
            if(p.getId()==id) person = p;
        }
        return person;
    }

    @MainMethod(message = "CRUD - DELETE")
    public void delete(Person person){
        deletedIndexes.add(person.getId());

        if(!file.exists())throw new FileNotExists("Add person for creating file");

        List<Person> list = getAll();
        list.remove(person);

        try{
            FileOutputStream fileOut = new FileOutputStream(tablePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @MainMethod(message = "CRUD - UPDATE")
    public void update(Person person){
        if(!file.exists())throw new FileNotExists("Add person for creating file");

        List<Person> list = getAll();
        for(Person p:list){
            if (p.getId() == person.getId()){
                p.setName(person.getName());
                p.setInsertDate(person.getInsertDate());
            }
        }

        try{
            FileOutputStream fileOut = new FileOutputStream(tablePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void clearFile(){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Person findByEmail(String email){
        List<Person> list = getAll();
        Person person = new Person();
        for(Person p: list){
            if(p.getEmail().equals(email))person =  p;
        }
        return person;
    }

}