package com.example.demo.controller;

import com.example.demo.domain.Film;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.domain.TypeOfPerson;
import com.example.demo.service.FilmService;
import com.example.demo.service.GenreService;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PersonService personService;

    @GetMapping("/films")
    public String getFilmsFiltered(@RequestParam(name = "genre", required = false) Long genreId,
                                   Model model) {
        log.debug("REST request to get films by genre : {}", genreId);
        List<Film> films;
        if (genreId != null) {
            films = filmService.findFilmsByGenre(genreId);
        } else {
            films = filmService.findAllFilm();
        }
        model.addAttribute("filmList", films);
        List<Genre> genres = genreService.findAllGenre();
        model.addAttribute("genres", genres); // вставить жанры для тимлифа индекс
        List<Person> producers = personService.findPersonsByType(TypeOfPerson.PRODUCER);
        model.addAttribute("producers", producers); // вставить персоны  для тимлифа индекс

        // Передаю в шаблон выбранный жанр, что-бы подсветить выбранный фильтр
        model.addAttribute("selectedGenre", genreId);
        return "public/films";
    }

    @PostMapping(value = "/film_table")
    public String filmTablePost(@RequestParam String id,
                                @RequestParam String title,
                                @RequestParam String genre,
                                @RequestParam String person,
                                @RequestParam int year,
                                @RequestParam int rating,
                                @RequestParam MultipartFile file,
                                Model model) throws IOException {
        filmService.saveFilm(id, title, genre, person, year, rating, file);
//        filmList.add(postFilm);
//        model.addAttribute("film_table", filmList);
        return "redirect:/films";
    }
    // Считывание информации с сайта !

    @GetMapping("/film-table/delete/{id}")
    public String deleteFilmById(@PathVariable("id") Long id) {
        filmService.deleteFilm(id);
        return "redirect:/films";
    }

    @GetMapping("/film-table/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable("id") Long id) {
        Film film = filmService.getFilmById(id);
        return ResponseEntity.ok(film);
    }

}
