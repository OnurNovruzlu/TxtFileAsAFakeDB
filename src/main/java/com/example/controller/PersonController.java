package com.example.controller;

import com.example.dto.CPerson;
import com.example.dto.GenericResponse;
import com.example.dto.NewPerson;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService productService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody NewPerson newPerson){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insert(newPerson));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CPerson>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
    }
    @GetMapping("/get/{id}")
    public GenericResponse getById(@PathVariable("id")int id){
        return GenericResponse.getResponseForData(productService.getById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")int id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.delete(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id")int id,@RequestBody NewPerson newPerson){
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, newPerson));
    }
    @GetMapping("/reset")
    @ResponseStatus(HttpStatus.OK)
    public String reset(){
        return productService.reset();
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email,@RequestParam(required = false) String password){
        return ResponseEntity.status(HttpStatus.OK).body(productService.login(email,password));
    }

}
