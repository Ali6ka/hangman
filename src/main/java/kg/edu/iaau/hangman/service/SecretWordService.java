package kg.edu.iaau.hangman.service;

import kg.edu.iaau.hangman.entity.SecretWord;

import java.util.List;

public interface SecretWordService
{
    SecretWord findById(int id);

    List<SecretWord> findAll();

    SecretWord getRandom();

    void save(SecretWord secretWord);

    void delete(SecretWord secretWord);
}
