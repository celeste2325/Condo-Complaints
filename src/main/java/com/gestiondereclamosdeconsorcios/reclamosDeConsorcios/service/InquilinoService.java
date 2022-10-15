package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadOcupadaException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;

import java.util.List;

public interface InquilinoService {
    List<Inquilino> getAll();

    void save(Inquilino newInquilino) throws UnidadInexistenteException, UnidadOcupadaException;

    void delete(Integer id);

    //Inquilino update(Inquilino newInquilino, Integer id);

}
