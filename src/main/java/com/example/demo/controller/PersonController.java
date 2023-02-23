package com.example.demo.controller;

import com.example.demo.domain.Person;
import com.example.demo.domain.TypeOfPerson;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/admin/person")
    public String pagePerson(Model model){
        List<Person> personList = personService.findAllPerson();
        model.addAttribute("personList", personList);
        return "admin/person";
    }

    @PostMapping(value = "/admin/person")
    public String personTablePost(@RequestParam String id,
                                 @RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam TypeOfPerson typeOfPerson,
                                  Model model) throws IOException {
        personService.savePerson(id, name, surname, typeOfPerson);
        return "redirect:/admin/person";
    }

    @GetMapping("/admin/person/delete/{id}")
    public String deletePersonById(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/admin/person";
    }

    @GetMapping("/admin/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") Long id) {
        Person person = personService.getPersonById(id);
        return ResponseEntity.ok(person);
    }

}
