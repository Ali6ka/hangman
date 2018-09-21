package kg.edu.iaau.hangman.service.impl;

import kg.edu.iaau.hangman.entity.Person;
import kg.edu.iaau.hangman.repository.PersonDAO;
import kg.edu.iaau.hangman.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService
{
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Person findById(int id)
    {
        return personDAO.getOne(id);
    }

    @Override
    public Person findByUsername(String username)
    {
        return personDAO.findByUsername(username);
    }

    @Override
    public List<Person> findAll()
    {
        return personDAO.findAll();
    }

    @Override
    public void save(Person person)
    {
        if (person.getPassword() != null){
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        } else {
            person.setPassword(findById(person.getId()).getPassword());
        }

        personDAO.saveAndFlush(person);
    }

    @Override
    public void delete(Person person)
    {
        personDAO.delete(person);
    }

    @Override
    public boolean isAdmin(String username)
    {
        return findByUsername(username).getIsAdmin();
    }
}
