package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    private PersonRepository personRepository;

    public Service(PersonRepository personRepository) {
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

    public void create(Person person){
        Optional<Person> findPersonByEmail=personRepository.findPersonByEmail(person.getEmail());
        if (findPersonByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        personRepository.save(person);
    }


    public ResponseEntity updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person currentPerson = personRepository.findById(id).orElseThrow(RuntimeException::new);
        currentPerson.setFirst_name(person.getFirst_name());
        currentPerson.setLast_name(person.getLast_name());
        currentPerson.setEmail(person.getEmail());
        currentPerson = personRepository.save(person);

        return ResponseEntity.ok(currentPerson);
    }

//    public ResponseEntity<Person> update(@PathVariable("id") long id, @PathVariable String firstName, @PathVariable String lastName, @PathVariable String mail) { //@RequestBody Person person,
//        Optional<Person> tutorialData = personRepository.findById(id);
//        if (tutorialData.isPresent()) {
//            Person _person = tutorialData.get();
//            _person.setFirst_name(firstName);
//            return new ResponseEntity<>(personRepository.save(_person), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    public ResponseEntity delete(@PathVariable Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*public void addNewPerson(Person person){
        Optional<Person> findPersonByEmail=personRepository.findPersonByEmail(person.getEmail());
        if (findPersonByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        personRepository.save(person);
    }*/
}
