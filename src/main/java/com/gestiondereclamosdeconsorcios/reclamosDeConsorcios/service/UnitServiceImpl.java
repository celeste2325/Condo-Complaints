package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ExistingUnitException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.CreationUnitDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.BuildingRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;
    private final BuildingRepository buildingRepository;

    public UnitServiceImpl(UnitRepository unitRepository, BuildingRepository buildingRepository) {
        this.unitRepository = unitRepository;
        this.buildingRepository = buildingRepository;
    }

    @Override
    public Integer createUnit(CreationUnitDto unit) throws ExistingUnitException, BuildingNotFoundException {
        if (this.buildingRepository.existsById(unit.getBuildingID())) {
            if (!this.unitRepository.existsByFloorAndNumberAndBuildingID(unit.getFloor(), unit.getNumber(), unit.getBuildingID())) {
                return this.unitRepository.saveUnit(unit.getFloor(), unit.getNumber(), unit.getOccupied(), unit.getBuildingID());
            } else throw new ExistingUnitException("The unit you're trying to create already exists in the building.");

        } else
            throw new BuildingNotFoundException("Building not found.");
    }

    @Override
    public void deleteUnit(Integer unitID) {
        unitRepository.deleteById(unitID);
    }

    @Override
    public Unit updateUnit(Unit newUnit, Integer unitID) {
        return unitRepository.findById(unitID).map(unit -> {
            unit.setFloor(newUnit.getFloor());
            unit.setNumber(newUnit.getNumber());
            unit.setOccupied(newUnit.getOccupied());
            return unitRepository.save(unit);
        }).get();
    }

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public Unit findByID(Integer unitID) throws UnitNotFoundException {
        Optional<Unit> unitByUnitID = this.unitRepository.findById(unitID);
        if (unitByUnitID.isPresent()) {
            return unitByUnitID.get();
        } else throw new UnitNotFoundException("Unit not found");
    }
}
