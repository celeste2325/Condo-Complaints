package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.LaUnidadYaFueCreada;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;

import java.util.List;

public interface UnitService {
    Integer createUnit(UnidadDto unit) throws BuildingNotFoundException, LaUnidadYaFueCreada;

    void deleteUnit(Integer unitID);

    Unit updateUnit(Unit newUnit, Integer unitID);

    List<Unit> findAll();

    Unit findByID(Integer unitID) throws UnitNotFoundException;

}
