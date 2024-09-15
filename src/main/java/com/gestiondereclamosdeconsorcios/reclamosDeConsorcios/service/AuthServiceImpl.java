package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.CondoOwnerNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IncorrectDocumentOrPasswordException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.existingAccount;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.AuthDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person signUp(AuthDto signUpData) throws CondoOwnerNotFoundException, existingAccount {
        Optional<Person> personByID = this.personRepository.findById(signUpData.getDocument());
        if (personByID.isPresent()) {
            if (personByID.get().getPassword() == null) {
                personByID.get().setCredential(this.bCryptPasswordEncoder.encode(signUpData.getPassword()));
                return this.personRepository.save(personByID.get());
            } else throw new existingAccount("Account with this document exists.");
        } else throw new CondoOwnerNotFoundException
                ("The document ID doesn't belong to a owner/tenant of the condo");
    }

    @Override
    public String logIn(AuthDto loginData) throws IncorrectDocumentOrPasswordException {
        Optional<Person> personByID = this.personRepository.findByDocument(loginData.getDocument());
        if (personByID.isPresent()) {
            //verify password
            if (this.bCryptPasswordEncoder.matches(loginData.getPassword(), personByID.get().getCredential())) {
                return personByID.get().getRole();
            } else throw new IncorrectDocumentOrPasswordException("Incorrect document or password");
        } else throw new IncorrectDocumentOrPasswordException("Incorrect document or password");
    }

}

