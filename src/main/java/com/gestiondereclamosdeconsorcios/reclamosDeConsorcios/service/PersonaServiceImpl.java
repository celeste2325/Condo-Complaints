package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getAll() {
        return this.personaRepository.findAll();
    }

    @Override
    public void save(Persona newPersona) {
        this.personaRepository.save(newPersona);
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
}
