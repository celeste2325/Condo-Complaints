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

    public List<Edificio> getEdificioByName(String name) {

        return edificioRepository.findByNombre(name);
    }
    @Transactional
    public void saveEdificio(Edificio edificio) {
        edificioRepository.save(edificio); /*es conveniente usar try catch?*/
    }

    @Override
    public List<Edificio> getAll() {
       return edificioRepository.findAll();
    }

}
