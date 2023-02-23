package com.example.demo.service;

import com.example.demo.domain.Anime;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.repository.AnimeRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<Anime> findAllAnime() {
        return animeRepository.findAll();
    }

    public Anime getAnimeById(Long id) {
        return animeRepository.findById(id).orElseGet(Anime::new);
    }

    public Anime saveAnime(String id, String title, String genreStr,String personStr,
                           int created, int rating, MultipartFile cover) throws IOException {
        Anime anime = new Anime();
        if (id != null && !id.isEmpty()) {
            anime.setId(Long.parseLong(id));
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

        anime.setTitle(title);
        anime.setGenres(genreList);
        anime.setPersons(personList);
        anime.setCreated(created);
        anime.setRating(rating);

        anime.setImg(cover.getBytes());
        return animeRepository.save(anime);
    }

    public void deleteAnime(Long id) {
        animeRepository.deleteById(id);
    }


    public Optional<Anime> getFile(Long fileId) {
        return animeRepository.findById(fileId);
    }

    public List<Anime> getFiles() {
        return animeRepository.findAll();
    }

}
