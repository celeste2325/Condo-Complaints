package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioCreadoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioNoEsDuenioNiIquilinoDelEdificioException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioOContraseniaIncorrecta;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;

public interface UserService {
    Persona saveUser(Persona personaLogin) throws UsuarioNoEsDuenioNiIquilinoDelEdificioException, UsuarioCreadoException;

    String getUser(String documento, String contrasenia) throws UsuarioOContraseniaIncorrecta;
}

