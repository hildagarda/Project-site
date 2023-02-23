package com.example.demo.service;

import com.example.demo.domain.Genre;
import com.example.demo.domain.Person;
import com.example.demo.domain.TypeOfPerson;
import com.example.demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAllPerson(){
        return personRepository.findAll();
    }

    public List<Person> findPersonsByType(TypeOfPerson typeOfPerson){
        return personRepository.findByTypeOfPerson(typeOfPerson);
    }
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseGet(Person::new);
    }
    public Person savePerson(String id, String name, String surname, TypeOfPerson typeOfPerson) throws IOException {
        Person person = new Person();
        if (id != null && !id.isEmpty()) {
            person.setId(Long.parseLong(id));
        }
        person.setName(name);
        person.setSurname(surname);
        person.setTypeOfPerson(typeOfPerson);
        return personRepository.save(person);
    }
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getFile(Long fileId) {
        return personRepository.findById(fileId);
    }

    public List<Person> getFiles() {
        return personRepository.findAll();
    }


}
