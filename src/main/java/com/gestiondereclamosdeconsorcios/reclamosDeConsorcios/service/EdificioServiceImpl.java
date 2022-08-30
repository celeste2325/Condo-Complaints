package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.EdificioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EdificioServiceImpl implements EdificioService {
    private EdificioRepository edificioRepository;
    @Override
    public List<Edificio> getEdificioByName(String name) {
        return edificioRepository.findByNombre(name);
    }
}
