package kg.edu.iaau.hangman.service.impl;

import kg.edu.iaau.hangman.entity.SecretWord;
import kg.edu.iaau.hangman.repository.SecretWordDAO;
import kg.edu.iaau.hangman.service.SecretWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service("secretWordService")
public class SecretWordServiceImpl implements SecretWordService
{
    @Autowired
    private SecretWordDAO secretWordDAO;

    @Override
    public SecretWord findById(int id)
    {
        return secretWordDAO.getOne(id);
    }

    @Override
    public List<SecretWord> findAll()
    {
        return secretWordDAO.findAll();
    }

    @Override
    public SecretWord getRandom()
    {
        return secretWordDAO.getRandom();
    }

    @Override
    public void save(SecretWord secretWord)
    {
        secretWordDAO.saveAndFlush(secretWord);
    }

    @Override
    public void delete(SecretWord secretWord)
    {
        secretWordDAO.delete(secretWord);
    }
}
