package com.example.demo.controller;

import com.example.demo.domain.Anime;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.domain.TypeOfPerson;
import com.example.demo.service.AnimeService;
import com.example.demo.service.GenreService;
import com.example.demo.service.PersonService;
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
public class AnimeController {
    @Autowired
    private AnimeService animeService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PersonService personService;

    @GetMapping("/anime")
    public String pageAnime(Model model) {
        // в модельку положил список аниме
        List<Anime> animeList = animeService.findAllAnime();
        model.addAttribute("animeList", animeList);
        List<Genre> genres = genreService.findAllGenre();
        model.addAttribute("genres", genres); // вставить жанры для тимлифа индекс
        List<Person> producers = personService.findPersonsByType(TypeOfPerson.PRODUCER);
        model.addAttribute("producers", producers);
        return "public/anime";
    }
    // Переход на страницу аниме!

    @PostMapping(value = "/anime_table")
    public String animeTablePost(@RequestParam String id,
                                 @RequestParam String title,
                                 @RequestParam String genre,
                                 @RequestParam String person,
                                 @RequestParam int year,
                                 @RequestParam int rating,
                                 @RequestParam MultipartFile file) throws IOException {
        animeService.saveAnime(id, title, genre, person , year, rating, file);
        // animeList.add(postAnime);

        return "redirect:/anime";
    }
    // Считывание информации с сайта !


    @GetMapping("/anime-table/delete/{id}")
    public String deleteAnimeById(@PathVariable("id") Long id) {
        animeService.deleteAnime(id);
        return "redirect:/anime";
    }

    @GetMapping("/anime-table/{id}")
    public ResponseEntity<Anime> getAnime(@PathVariable("id") Long id) {
        Anime anime = animeService.getAnimeById(id);
        return ResponseEntity.ok(anime);
    }
}
