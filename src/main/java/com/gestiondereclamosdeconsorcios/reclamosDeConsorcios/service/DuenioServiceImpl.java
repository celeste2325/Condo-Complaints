package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenios;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.DuenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuenioServiceImpl implements DuenioService {
    @Autowired
    DuenioRepository duenioRepository;

    @Override
    public List<Duenios> getAll() {
        return duenioRepository.findAll();
    }

    @Override
    public void saveDuenio(Duenios newDuenios) {
        duenioRepository.save(newDuenios);
    }

    @Override
    public Duenios update(Duenios newDuenios, Integer id) {
        return duenioRepository.findById(id).map(duenio -> {
            duenio.setIdentificador(newDuenios.getIdentificador());
            return duenioRepository.save(duenio);
        }).get();
    }

    @Override
    public void remove(Integer id) {
        duenioRepository.deleteById(id);
    }
}
