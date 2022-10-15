package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadOcupadaException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.InquilinoRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InquilinoServiceImpl implements InquilinoService {
    @Autowired
    InquilinoRepository inquilinoRepository;

    @Autowired
    UnidadRepository unidadRepository;

    @Override
    public List<Inquilino> getAll() {
        return this.inquilinoRepository.findAll();
    }

    @Override
    public void save(Inquilino newInquilino) throws UnidadInexistenteException, UnidadOcupadaException {
        Optional<Unidad> unidadEncontrada = this.unidadRepository.findById(newInquilino.getId());
        if (unidadEncontrada.isPresent()) {
            if (!unidadEncontrada.get().getHabitado().equalsIgnoreCase("S")) {
                this.inquilinoRepository.save(newInquilino);
                unidadEncontrada.get().setHabitado("S");
            } else throw new UnidadOcupadaException("La unidad esta ocupada");
        } else throw new UnidadInexistenteException("La unidad no existe");
    }

    /*@Override
    public Inquilino update(Inquilino newInquilino, Integer id) {
        return this.inquilinoRepository.findByInquilinoID(id).map(inquilino -> {
            inquilino.setId(newInquilino.getId());
            return this.inquilinoRepository.save(inquilino);
        }).get();
    }
    */

    @Override
    public void delete(Integer id) {
        this.inquilinoRepository.eliminarInquilinoDeLaUnidad(id);

    }
}
