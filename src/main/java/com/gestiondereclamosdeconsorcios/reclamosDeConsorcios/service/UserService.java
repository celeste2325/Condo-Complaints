package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioNoEsDuenioNiIquilinoDelEdificioException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioOContraseniaIncorrecta;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Personas;

public interface UserService {
    public Personas saveUser(Personas personasLogin) throws UsuarioNoEsDuenioNiIquilinoDelEdificioException;
    public Personas getUser(String documento, String contrasenia) throws UsuarioOContraseniaIncorrecta;
}
