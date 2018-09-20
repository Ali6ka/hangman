package kg.edu.iaau.hangman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kg.edu.iaau.hangman.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer>
{
    Person findByUsername(String username);
}
