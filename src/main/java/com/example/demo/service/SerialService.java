package com.example.demo.service;

import com.example.demo.domain.Film;

import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.domain.Serial;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.SerialRepository;
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
public class SerialService {
    @Autowired
    private SerialRepository serialRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<Serial> findAllSerial() {
        return serialRepository.findAll();
    }

    public Serial getSerialById(Long id) {
        return serialRepository.findById(id).orElseGet(Serial::new);
    }

    public Serial saveSerial(String id, String title, String genreStr, String personStr,
                             int created, int rating, MultipartFile cover) throws IOException {
        Serial serial = new Serial();
        if (id != null && !id.isEmpty()) {
            serial.setId(Integer.parseInt(id));
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

        serial.setTitle(title);
        serial.setGenres(genreList);
        serial.setPersons(personList);
        serial.setCreated(created);
        serial.setRating(rating);
        serial.setImg(cover.getBytes());
        return serialRepository.save(serial);
    }

    public void deleteSerial(Long id) {
        serialRepository.deleteById(id);
    }

    public Optional<Serial> getFile(Long fileId) {
        return serialRepository.findById(fileId);
    }

    public List<Serial> getFiles() {
        return serialRepository.findAll();
    }

}
