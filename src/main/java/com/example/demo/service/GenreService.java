package com.example.demo.service;

import com.example.demo.domain.Film;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Serial;
import com.example.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAllGenre() {

        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElseGet(Genre::new); // ЭТо где используется внутри контроллера и ::
    }

    public Genre saveGenre(String id, String name) throws IOException {
        Genre genre = new Genre();
        if (id != null && !id.isEmpty()) {
            genre.setId(Long.parseLong(id));
        }
        genre.setName(name);
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    public Optional<Genre> getFile(Long fileId) {
        return genreRepository.findById(fileId);
    }

    public List<Genre> getFiles() {
        return genreRepository.findAll();
    }

}
