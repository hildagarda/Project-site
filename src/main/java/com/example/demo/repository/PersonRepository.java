package com.example.demo.repository;

import com.example.demo.domain.Person;
import com.example.demo.domain.TypeOfPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Type;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByTypeOfPerson(TypeOfPerson typeOfPerson);

}
