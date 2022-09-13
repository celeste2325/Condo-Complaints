package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.DuenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuenioServiceImpl implements DuenioService {
    @Autowired
    DuenioRepository duenioRepository;

    @Override
    public List<Duenio> getAll() {
        return duenioRepository.findAll();
    }

    @Override
    public void saveDuenio(Duenio newDuenio) {
        duenioRepository.save(newDuenio);
    }

    @Override
    public Duenio update(Duenio newDuenio, Integer id) {
        return duenioRepository.findById(id).map(duenio -> {
            duenio.setIdentificador(newDuenio.getIdentificador());
            return duenioRepository.save(duenio);
        }).get();
    }

    @Override
    public void remove(Integer id) {
        duenioRepository.deleteById(id);
    }
}
