package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.CondoOwnerNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IncorrectDocumentOrPasswordException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.existingAccount;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.AuthDto;

public interface AuthService {
    Person signUp(AuthDto signUpData) throws CondoOwnerNotFoundException, existingAccount;

    String logIn(AuthDto loginData) throws IncorrectDocumentOrPasswordException;
}

