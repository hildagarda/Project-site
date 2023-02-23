package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.repository.BookRepository;
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
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseGet(Book::new);
    }

    public Book saveBook(String id, String title, String genreStr, String personStr,
                         int created, int rating, MultipartFile cover) throws IOException {
        Book book = new Book();
        if (id != null && !id.isEmpty()) {
            book.setId(Integer.parseInt(id));
        }

        String[] strGenre = genreStr.split(",");
        List<Genre> genreList = new ArrayList<>();

        for (String genreIdStr : strGenre) {
            genreList.add(genreRepository.findById(Long.parseLong(genreIdStr)).get());
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

        book.setTitle(title);
        book.setGenres(genreList);
        book.setPersons(personList);
        book.setCreated(created);
        book.setRating(rating);

        book.setImg(cover.getBytes());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getFile(Long fileId) {
        return bookRepository.findById(fileId);
    }

    public List<Book> getFiles() {
        return bookRepository.findAll();
    }

}
