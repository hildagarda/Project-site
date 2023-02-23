package com.example.demo.controller;


import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.domain.Serial;

import com.example.demo.domain.TypeOfPerson;
import com.example.demo.service.GenreService;
import com.example.demo.service.PersonService;
import com.example.demo.service.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class SerialController {
    @Autowired
    private SerialService serialService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PersonService personService;

    @GetMapping("/serials")
    public String pageSerial(Model model) {
        List<Serial> serial = serialService.findAllSerial();
        model.addAttribute("serialList", serial);
        List<Genre> genres = genreService.findAllGenre();
        model.addAttribute("genres", genres); // вставить жанры для тимлифа индекс
        List<Person> producers = personService.findPersonsByType(TypeOfPerson.PRODUCER);
        model.addAttribute("producers", producers);
        return "public/serials";
    }

    @PostMapping(value = "/serial_table")
    public String serialTablePost(@RequestParam String id,
                                  @RequestParam String title,
                                  @RequestParam String genre,
                                  @RequestParam String person,
                                  @RequestParam int year,
                                  @RequestParam int rating,
                                  @RequestParam MultipartFile file,
                                  Model model) throws IOException {
        serialService.saveSerial(id, title, genre, person, year, rating, file);
        return "redirect:/serials";
    }
    // Считывание информации с сайта !

    @GetMapping("/serial-table/delete/{id}")
    public String deleteSerialById(@PathVariable("id") Long id) {
        serialService.deleteSerial(id);
        return "redirect:/serials";
    }

    @GetMapping("/serial-table/{id}")
    public ResponseEntity<Serial> getSerial(@PathVariable("id") Long id) {
        Serial serial = serialService.getSerialById(id);
        return ResponseEntity.ok(serial);
    }


}
