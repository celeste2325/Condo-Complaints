package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilinos;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.InquilinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquilinoServiceImpl implements InquilinoService{
    @Autowired
    InquilinoRepository inquilinoRepository;

    @Override
    public List<Inquilinos> getAll() {
       return this.inquilinoRepository.findAll();
    }

    @Override
    public List<Inquilinos> getAllByDocument(String Documento) {
        var value = this.inquilinoRepository.findDistinctByDocumento(Documento);
        return value;
    }

    @Override
    public void save(Inquilinos newInquilinos) {
        this.inquilinoRepository.save(newInquilinos);
    }

    @Override
    public void delete(Integer id) {
        this.inquilinoRepository.deleteById(id);
    }

    @Override
    public Inquilinos update(Inquilinos newInquilinos, Integer id) {
        return this.inquilinoRepository.findById(id).map(inquilino -> {
            inquilino.setIdentificador(newInquilinos.getIdentificador());
            return this.inquilinoRepository.save(inquilino);
        }).get();
    }
}
