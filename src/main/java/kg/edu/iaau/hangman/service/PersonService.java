package kg.edu.iaau.hangman.service;

import kg.edu.iaau.hangman.entity.Person;

import java.util.List;

public interface PersonService
{
    Person findById(int id);

    Person findByUsername(String username);

    List<Person> findAll();

    Person save(Person person);

    void delete(Person person);

    boolean isAdmin(String username);
}
