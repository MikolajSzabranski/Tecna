package com.example.demo.Person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseEntity<List<Person>> getAll() {
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

    public ResponseEntity<Person> getById(@PathVariable("id") long id) {
        Optional<Person> personList = personRepository.findById(id);
        if (personList.isPresent()) {
            return new ResponseEntity<>(personList.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void create(String first, String last, String email) {
        Person person = new Person(first, last, email);
        Optional<Person> findPersonByEmail = personRepository.findPersonByEmail(person.getEmail());
        if (findPersonByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        personRepository.save(person);
    }

    public ResponseEntity updatePerson(@RequestBody Person person) {
        Person currentPerson = personRepository.findById(person.getId()).orElseThrow(RuntimeException::new);
        currentPerson.setFirstName(person.getFirstName());
        currentPerson.setLastName(person.getLastName());
        currentPerson.setEmail(person.getEmail());
        currentPerson = personRepository.save(person);

        return ResponseEntity.ok(currentPerson);
    }

    public ResponseEntity delete(@RequestBody Person person) {
        Person currentPerson = personRepository.findPersonByEmail(person.getEmail()).orElseThrow(RuntimeException::new);
        personRepository.delete(currentPerson); //deleteById(id);
        return ResponseEntity.ok().build();
    }
}
