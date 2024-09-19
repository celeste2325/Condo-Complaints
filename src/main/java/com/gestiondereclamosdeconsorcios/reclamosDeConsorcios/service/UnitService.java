package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ExistingUnitException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.CreationUnitDto;

import java.util.List;

public interface UnitService {
    Integer createUnit(CreationUnitDto unit) throws ExistingUnitException, BuildingNotFoundException;

    void deleteUnit(Integer unitID);

    Unit updateUnit(Unit newUnit, Integer unitID);

    List<Unit> findAll();

    Unit findByID(Integer unitID) throws UnitNotFoundException;

}
