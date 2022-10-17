package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioCreadoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioNoEsDuenioNiIquilinoDelEdificioException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioOContraseniaIncorrecta;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona saveUser(Persona personasLogin) throws UsuarioNoEsDuenioNiIquilinoDelEdificioException, UsuarioCreadoException {
        Optional<Persona> personaEncontrada = this.personaRepository.findById(personasLogin.getDocumento());
        if (personaEncontrada.isPresent()) {
            if (personaEncontrada.get().getContrasenia() == null) {
                personaEncontrada.get().setContrasenia(this.bCryptPasswordEncoder.encode(personasLogin.getContrasenia()));
                return this.personaRepository.save(personaEncontrada.get());
            } else throw new UsuarioCreadoException("Ya existe un usuario creado con mismo documento");


        } else throw new UsuarioNoEsDuenioNiIquilinoDelEdificioException
                ("El número de documento no corresponde a un inquilino o dueño válido para el consorcio");
    }

    @Override
    public String getUser(String documento, String contrasenia) throws UsuarioOContraseniaIncorrecta {
        Optional<Persona> usuarioEncontrado = this.personaRepository.findById(documento);
        if (usuarioEncontrado.isPresent()) {
            if (this.bCryptPasswordEncoder.matches(contrasenia, usuarioEncontrado.get().getContrasenia())) {
                return usuarioEncontrado.get().getRoles();
            } else throw new UsuarioOContraseniaIncorrecta("El documento o constraseña ingresado es incorrecto");
        } else throw new UsuarioOContraseniaIncorrecta("El documento o constraseña ingresado es incorrecto");
    }

}

