package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;

import java.util.List;

public interface InquilinoService {
    List<Inquilino> getAll();
    void save(Inquilino newInquilino);
    void delete(Integer id);
    Inquilino update(Inquilino newInquilino, Integer id);
}
