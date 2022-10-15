package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioNoEsDuenioNiIquilinoDelEdificioException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioOContraseniaIncorrecta;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona saveUser(Persona personaLogin) throws UsuarioNoEsDuenioNiIquilinoDelEdificioException {
       Optional<Persona> personaEncontrada = this.personaRepository.findById(personaLogin.getDocumento());
        if (personaEncontrada.isPresent()) {
            personaEncontrada.get().setContrasenia(this.bCryptPasswordEncoder.encode(personaLogin.getContrasenia()));
            return this.personaRepository.save(personaEncontrada.get());

        } else throw new UsuarioNoEsDuenioNiIquilinoDelEdificioException
                ("El número de documento no corresponde a un inquilino o dueño válido para el consorcio");
    }

    @Override
    public Persona getUser(String documento, String contrasenia) throws UsuarioOContraseniaIncorrecta {
        var usuarioEncontrada = this.personaRepository.findPersonaByDocument(documento);
        if (usuarioEncontrada != null) {
            if (this.bCryptPasswordEncoder.matches(contrasenia, usuarioEncontrada.getContrasenia())){
                return usuarioEncontrada;
            }else throw new UsuarioOContraseniaIncorrecta("El documento o constraseña ingresado es incorrecto");
        } else throw  new UsuarioOContraseniaIncorrecta("El documento o constraseña ingresado es incorrecto");
    }

}
