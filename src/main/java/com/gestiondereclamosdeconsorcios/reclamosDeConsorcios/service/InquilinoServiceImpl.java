package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.InquilinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquilinoServiceImpl implements InquilinoService{
    @Autowired
    InquilinoRepository inquilinoRepository;

    @Override
    public List<Inquilino> getAll() {
       return this.inquilinoRepository.findAll();
    }

    @Override
    public void save(Inquilino newInquilino) {
        this.inquilinoRepository.save(newInquilino);
    }

    @Override
    public void delete(Integer id) {
        this.inquilinoRepository.deleteById(id);
    }

    @Override
    public Inquilino update(Inquilino newInquilino, Integer id) {
        return this.inquilinoRepository.findById(id).map(inquilino -> {
            inquilino.setIdentificador(newInquilino.getIdentificador());
            return this.inquilinoRepository.save(inquilino);
        }).get();
    }
}
