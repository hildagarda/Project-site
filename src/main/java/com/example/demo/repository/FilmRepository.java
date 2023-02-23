package com.example.demo.repository;

import com.example.demo.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    // Напиши запрос который возвращает список фильмов определенного жанра (по Long Genre Id)
    @Query(value = "SELECT f FROM Film f LEFT JOIN FETCH f.genres g WHERE g.id = ?1")
    List<Film> findAllByGenreId(Long genreId);
}
