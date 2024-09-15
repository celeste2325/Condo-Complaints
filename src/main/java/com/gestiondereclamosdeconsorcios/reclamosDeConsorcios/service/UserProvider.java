package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.CondoOwnerNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void createUser(UserDetails personaLogin) {
        Optional<Person> personaEncontrada = this.personRepository.findById(personaLogin.getUsername());
        if (personaEncontrada.isPresent()) {
            personaEncontrada.get().setCredential(this.bCryptPasswordEncoder.encode(personaLogin.getPassword()));
            this.personRepository.save(personaEncontrada.get());

        } else try {
            throw new CondoOwnerNotFoundException
                    ("El número de documento no corresponde a un inquilino o dueño válido para el consorcio");
        } catch (CondoOwnerNotFoundException e) {
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
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }

}

