package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.YaExisteUnaPersonaConMismoDniException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getAll() {
        return this.personaRepository.findAll();
    }

    @Override
    public void save(Persona newPersona) throws YaExisteUnaPersonaConMismoDniException {
        boolean existeLaPersona = this.personaRepository.existsById(newPersona.getDocumento());
        if (!existeLaPersona) {
            this.personaRepository.save(newPersona);
        } else {
            throw new YaExisteUnaPersonaConMismoDniException("ya existe una persona con mismo documento");
        }
    }

    @Override
    public Persona update(Persona newPersona, String documento) {
        return this.personaRepository.findById(documento).map(persona -> {
            persona.setNombre(newPersona.getNombre());
            return this.personaRepository.save(persona);
        }).get();
    }

    @Override
    public void delete(String documento) {
        this.personaRepository.deleteById(documento);
    }

    @Override
    public Persona existePersonaByDocumento(String documento) throws DocumentoNoEncontradoException {
        Optional<Persona> persona = this.personaRepository.findById(documento);
        if (persona.isPresent()) {
            return persona.get();
        } else {
            throw new DocumentoNoEncontradoException("El documento debe pertenecer a un dueño/inquilino del consorcio");
        }
    }


}
