package com.example.demo.View;

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

    @GetMapping(value = "/persons/up")
    public String getFormUpdate(@ModelAttribute Person person) {
        return "persons/up/up";
    }

    @GetMapping(value = "/persons/delete")
    public String getFormDelete(@ModelAttribute Person person) {
        return "persons/delete/delete";
    }
}
