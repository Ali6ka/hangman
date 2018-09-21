package kg.edu.iaau.hangman.repository;

import kg.edu.iaau.hangman.entity.SecretWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretWordDAO extends JpaRepository<SecretWord, Integer>
{
    @Query(value = "SELECT * FROM User "
            + "ORDER BY RAND() LIMIT 1",
            nativeQuery = true)
    SecretWord getRandom();
}
