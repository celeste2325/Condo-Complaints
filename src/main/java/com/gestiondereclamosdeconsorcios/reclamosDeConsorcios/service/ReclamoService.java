package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamos;

import java.util.List;
import java.util.Optional;

public interface ReclamoService {
    
    void createReclamo(Reclamos newReclamo);

    List<Reclamos> getAll();

    Reclamos updateEstado(Reclamos reclamo, Integer id);

    List<Reclamos> getAllByEstado(String estado);

    Optional<Reclamos> getById(Integer id);

}
