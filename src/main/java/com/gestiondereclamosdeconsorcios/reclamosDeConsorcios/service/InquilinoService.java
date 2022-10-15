package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilinos;

import java.util.List;

public interface InquilinoService {
    List<Inquilinos> getAll();
    List<Inquilinos> getAllByDocument(String Documento);
    void save(Inquilinos newInquilinos);
    void delete(Integer id);
    Inquilinos update(Inquilinos newInquilinos, Integer id);
}
