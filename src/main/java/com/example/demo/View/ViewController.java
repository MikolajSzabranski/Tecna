package com.example.demo;

import com.example.demo.Person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ViewController {
    @GetMapping(value = "/persons/add")
    public String getFormCreate(@ModelAttribute Person person) {
        return "persons/add/add";
    }

    @GetMapping(value = "/persons/up/{id}")
    public String getFormUpdate(@ModelAttribute Person person) {
        return "persons/up/up";
    }
}
