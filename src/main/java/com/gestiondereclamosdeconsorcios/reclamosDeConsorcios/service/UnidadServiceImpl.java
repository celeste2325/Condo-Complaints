package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadServiceImpl implements UnidadService {
    @Autowired
    private UnidadRepository unidadRepository;

    public UnidadServiceImpl(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    @Override
    public void saveUnidad(Unidad unidad) {
        unidadRepository.save(unidad);
    }

    @Override
    public void remove(Integer id) {
        unidadRepository.deleteById(id);
    }

    @Override
    public Unidad update(Unidad newUnidad, Integer id) {
        return unidadRepository.findById(id).map(unidad -> {
            unidad.setPiso(newUnidad.getPiso());
            unidad.setNumero(newUnidad.getNumero());
            unidad.setHabitado(newUnidad.getHabitado());
            unidad.setEdificiosByCodigoEdificio(newUnidad.getEdificiosByCodigoEdificio());
            return unidadRepository.save(unidad);
        }).get();
    }

    @Override
    public List<Unidad> getAll() {
        return unidadRepository.findAll();
    }

}
