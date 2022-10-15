package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;

import java.util.List;
import java.util.Optional;

public interface ReclamoService {

    void createReclamo(Reclamo newReclamo);

    List<Reclamo> getAll();

    Reclamo updateEstado(Reclamo reclamo, Integer id);

    List<Reclamo> getAllByEstado(String estado);

    Optional<Reclamo> getById(Integer id);

}
