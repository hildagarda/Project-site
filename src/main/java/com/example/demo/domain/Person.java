package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private String Surname;
    @Enumerated(EnumType.STRING)
    private TypeOfPerson typeOfPerson;

    @ManyToMany (mappedBy = "persons")
    private List<Film> filmList = new ArrayList<>();

}
