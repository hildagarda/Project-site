package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.domain.TypeOfPerson;
import com.example.demo.service.BookService;
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
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PersonService personService;

    @GetMapping("/books")
    public String pageBook(Model model) {
        List<Book> book = bookService.findAllBook();
        model.addAttribute("bookList", book);
        List<Genre> genres = genreService.findAllGenre();
        model.addAttribute("genres", genres); // вставить жанры для тимлифа индекс
        List<Person> producers = personService.findPersonsByType(TypeOfPerson.PRODUCER);
        model.addAttribute("producers", producers);
        return "public/books";
    }

    @PostMapping(value = "/books")
    public String bookTablePost(@RequestParam String id,
                                @RequestParam String title,
                                @RequestParam String genre,
                                @RequestParam String person,
                                @RequestParam int year,
                                @RequestParam int rating,
                                @RequestParam MultipartFile file,
                                Model model) throws IOException {
        bookService.saveBook(id, title, genre, person, year, rating, file);
        return "redirect:/books";
    }
    // Считывание информации с сайта !

    @GetMapping("/book-table/delete/{id}")
    public String deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/book-table/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }


}
