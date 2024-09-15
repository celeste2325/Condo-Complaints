package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.LaUnidadYaFueCreada;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;

import java.util.List;

public interface UnitService {
    Integer saveUnit(UnidadDto unit) throws BuildingNotFoundException, LaUnidadYaFueCreada;

    void remove(Integer id);

    Unit update(Unit newUnit, Integer unitID);

    List<Unit> getAll();

    Unit getID(Integer unitID) throws UnitNotFoundException;

}
