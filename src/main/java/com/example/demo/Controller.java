package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/Yo")
    public String temp() {
        return toString();
    }

    @RequestMapping("/persons")
    public ResponseEntity<List<Person>> getAll() {
        ;
        try {
            List<Person> personList = new ArrayList<Person>();
            personRepository.findAll().forEach(personList::add);
            if (personList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/persons/id/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") long id) {
        Optional<Person> personList = personRepository.findById(id);
        if (personList.isPresent()) {
            return new ResponseEntity<>(personList.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/persons/add")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        String firstName = "";
        String lastName = "";
        String mail = "";
        try {
            person = new Person(firstName, lastName, mail);
            personRepository.save(person);
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

