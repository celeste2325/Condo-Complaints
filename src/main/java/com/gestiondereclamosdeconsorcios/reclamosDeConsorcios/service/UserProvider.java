package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioNoEsDuenioNiIquilinoDelEdificioException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;
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
    private PersonaRepository personaRepository;

    @Override
    public void createUser(UserDetails personaLogin) {
        Optional<Persona> personaEncontrada = this.personaRepository.findById(personaLogin.getUsername());
        if (personaEncontrada.isPresent()) {
            personaEncontrada.get().setContrasenia(this.bCryptPasswordEncoder.encode(personaLogin.getPassword()));
            this.personaRepository.save(personaEncontrada.get());

        } else try {
            throw new UsuarioNoEsDuenioNiIquilinoDelEdificioException
                    ("El número de documento no corresponde a un inquilino o dueño válido para el consorcio");
        } catch (UsuarioNoEsDuenioNiIquilinoDelEdificioException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAdmin(UserDetails personaLogin) {
        var AminPersona = (Persona) personaLogin;
        this.personaRepository.save(AminPersona);
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
        Optional<Persona> persona = this.personaRepository.findById(username);
        if (persona.isPresent()) {
            persona.get().setContrasenia(this.bCryptPasswordEncoder.encode(persona.get().getContrasenia()));
        } else try {
            throw new UsuarioNoEsDuenioNiIquilinoDelEdificioException("El usuario no existe");
        } catch (UsuarioNoEsDuenioNiIquilinoDelEdificioException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean userExists(String username) {
        return this.personaRepository.findById(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Persona> user = this.personaRepository.findById(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }

}

