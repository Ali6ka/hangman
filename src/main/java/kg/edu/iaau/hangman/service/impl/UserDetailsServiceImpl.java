package kg.edu.iaau.hangman.service.impl;

import kg.edu.iaau.hangman.entity.Person;
import kg.edu.iaau.hangman.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.emptyList;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private PersonService personService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Person person = personService.findByUsername(username);
        if (person == null)
        {
            throw new UsernameNotFoundException(username);
        }

        return new User(person.getUsername(), person.getPassword(), emptyList());
    }
}

