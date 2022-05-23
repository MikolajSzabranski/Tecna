package com.example.demo.Person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAll() {
        return personService.getAll();
    }

    @RequestMapping("/persons/id/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") long id) {
        return personService.getById(id);
    }


    @PostMapping(value = "/persons/add")
    public void create(@RequestBody Person person) {
        personService.create(person.getFirstName(), person.getLastName(), person.getEmail());
    }

    @PutMapping("/persons/up")
    public ResponseEntity updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }
//    @RequestMapping("/persons/up/{id}/{firstName}/{lastName}/{mail}")
//    public ResponseEntity<Person> update(@PathVariable("id") long id, @PathVariable String firstName, @PathVariable String lastName, @PathVariable String mail) { //@RequestBody Person person,
//        return service.update(id, firstName, lastName, mail);
//    }

//    @RequestMapping("/{id}")
//    public ResponseEntity delete(@PathVariable Long id) {
//        return personService.delete(id);
//    }

    @RequestMapping("/persons/delete")
    public ResponseEntity deletePerson(@RequestBody Person person) {
        return personService.delete(person);
    }

}

