package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificios;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class EdificioServiceImpl implements EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    @Override

    public List<Edificios> getEdificioByName(String name) {

        return edificioRepository.findByNombre(name);
    }

    @Transactional
    public void saveEdificio(Edificios edificios) {
        edificioRepository.save(edificios); /*es conveniente usar try catch?*/
    }

    @Override
    public List<Edificios> getAll() {
        return edificioRepository.findAll();
    }

    @Override
    public void remove(Integer id) {
        edificioRepository.deleteById(id);
    }

    @Override
    public Edificios update(Edificios newEdificios, Integer id) {
        return edificioRepository.findById(id).map(edificio -> {
            edificio.setDireccion(newEdificios.getDireccion());
            edificio.setNombre(newEdificios.getNombre());
            return edificioRepository.save(edificio);
        }).get();
    }

}
