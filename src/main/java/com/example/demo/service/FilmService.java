package com.example.demo.service;

import com.example.demo.domain.Film;

import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<Film> findAllFilm() {
        return filmRepository.findAll();
    }

    public List<Film> findFilmsByGenre(Long genreId){
        return filmRepository.findAllByGenreId(genreId);
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElseGet(Film::new);
    }

    public Film saveFilm(String id, String title, String genreStr, String personStr,
                         int created, int rating, MultipartFile cover) throws IOException {
        Film film = new Film();
        if (id != null && !id.isEmpty()) {
            film.setId(Long.parseLong(id));
        }

        String[] strGenre = genreStr.split(",");
        List<Long> genreIds = new ArrayList<>();
        for (String genreIdStr : strGenre) {
            genreIds.add(Long.parseLong(genreIdStr));
        }

        List<Genre> genreList = new ArrayList<>();
        for (Long genreId : genreIds) {
            Genre genre = genreRepository.findById(genreId).get();
            //   log.debug("Найден жанр по id = {} : {}", genreId, genre);
            genreList.add(genre);
        }
        // Persons
        String[] strPerson = personStr.split(",");
        List<Long> personIds = new ArrayList<>();
        for (String personIdSrt : strPerson) {
            personIds.add(Long.parseLong(personIdSrt));
        }
        List<Person> personList = new ArrayList<>();
        for (Long personId : personIds) {
            Person person = personRepository.findById(personId).get();
            personList.add(person);
        }


        film.setTitle(title);
        film.setGenres(genreList);
        film.setPersons(personList);
        film.setCreated(created);
        film.setRating(rating);


        film.setImg(cover.getBytes());
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    public Optional<Film> getFile(Long fileId) {
        return filmRepository.findById(fileId);
    }

    public List<Film> getFiles() {
        return filmRepository.findAll();
    }

}
