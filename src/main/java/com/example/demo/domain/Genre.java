package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Film> filmList = new ArrayList<>();
    @ManyToMany(mappedBy = "genres")
    private List<Serial> serialList = new ArrayList<>();
    @ManyToMany(mappedBy = "genres")
    private List<Anime> animelList = new ArrayList<>();
    @ManyToMany(mappedBy = "genres")
    private List<Book> booklList = new ArrayList<>();




}
