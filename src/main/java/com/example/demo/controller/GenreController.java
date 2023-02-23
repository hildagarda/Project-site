package com.example.demo.controller;

import com.example.demo.domain.Genre;
import com.example.demo.service.GenreService;
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
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/admin/genre")
    public String pageGenre(Model model){
        List<Genre> genreList = genreService.findAllGenre();
        model.addAttribute("genreList", genreList);
        return "admin/genre";
    }

    @PostMapping(value = "/admin/genre")
    public String genreTablePost(@RequestParam String id,
                                  @RequestParam String name,
                                  Model model) throws IOException {
        genreService.saveGenre(id, name);
        return "redirect:/admin/genre";
    }


    @GetMapping("/admin/genre/delete/{id}")
    public String deleteGenreById(@PathVariable("id") Long id) {
        genreService.deleteGenre(id);
        return "redirect:/admin/genre";
    }

    @GetMapping("/admin/genre/{id}")
    public ResponseEntity<Genre> getGenre(@PathVariable("id") Long id) {
        Genre genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }
}
