package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;
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
    @Transactional
    public void saveEdificio(Edificio edificio) {
        edificioRepository.save(edificio); /*es conveniente usar try catch?*/
    }

    @Override
    public List<Edificio> getEdificioByName(String name) {
        return null;
    }

    @Override
    public List<Edificio> getAll() {
        return edificioRepository.findAll();
    }

    @Override
    public void remove(Integer id) {
        edificioRepository.deleteById(id);
    }

    @Override
    public Edificio update(Edificio newEdificio, Integer id) {
        return edificioRepository.findById(id).map(edificio -> {
            edificio.setDireccion(newEdificio.getDireccion());
            edificio.setNombre(newEdificio.getNombre());
            return edificioRepository.save(edificio);
        }).get();
    }

}
