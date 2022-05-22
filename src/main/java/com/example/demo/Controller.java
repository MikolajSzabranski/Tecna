package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private final Service service;

    @Autowired
    PersonRepository personRepository;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAll() {
        return service.getAll();
    }

    @RequestMapping("/persons/id/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") long id) {
        return service.getById(id);
    }

   @PostMapping(value = "/persons/add")
    public String create(@ModelAttribute Person person, Model model) {
        model.addAttribute("add", person);
        return "persons";
    }

    @PutMapping("/persons/up")
    public ResponseEntity updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return service.updatePerson(id, person);
    }
//    @RequestMapping("/persons/up/{id}/{firstName}/{lastName}/{mail}")
//    public ResponseEntity<Person> update(@PathVariable("id") long id, @PathVariable String firstName, @PathVariable String lastName, @PathVariable String mail) { //@RequestBody Person person,
//        return service.update(id, firstName, lastName, mail);
//    }

    @RequestMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return service.delete(id);
    }

//    @PostMapping
//    public void addNewPerson(@RequestBody Person person){
//        service.addNewPerson(person);
//    }
}

