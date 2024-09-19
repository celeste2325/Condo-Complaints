package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.CondoOwnerNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProvider implements UserDetailsManager, UserDetailsService {
    private final PasswordEncoder bCryptPasswordEncoder;
    private final PersonRepository personRepository;

    public UserProvider(PasswordEncoder bCryptPasswordEncoder, PersonRepository personRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personRepository = personRepository;
    }

    @Override
    public void createUser(UserDetails personaLogin) {
        Optional<Person> personByID = this.personRepository.findById(personaLogin.getUsername());
        if (personByID.isPresent()) {
            personByID.get().setCredential(this.bCryptPasswordEncoder.encode(personaLogin.getPassword()));
            this.personRepository.save(personByID.get());

        } else try {
            throw new DocumentNotFoundException
                    ("The document is not found in the condo data.");
        } catch (DocumentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAdmin(UserDetails personaLogin) {
        var AminPersona = (Person) personaLogin;
        this.personRepository.save(AminPersona);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context " + "for current user.");
        }
        String username = currentUser.getName();
        Optional<Person> persona = this.personRepository.findById(username);
        if (persona.isPresent()) {
            persona.get().setCredential(this.bCryptPasswordEncoder.encode(persona.get().getPassword()));
        } else try {
            throw new CondoOwnerNotFoundException("he document ID doesn't belong to a owner/tenant of the condo");
        } catch (CondoOwnerNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean userExists(String username) {
        return this.personRepository.findById(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = this.personRepository.findById(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }

}

