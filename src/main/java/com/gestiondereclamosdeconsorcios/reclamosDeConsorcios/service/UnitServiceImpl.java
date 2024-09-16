package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.LaUnidadYaFueCreada;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;
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
    public Integer createUnit(UnidadDto unit) throws BuildingNotFoundException, LaUnidadYaFueCreada {
        if (this.buildingRepository.existsById(unit.getCodigoEdificio())) {
            if (!this.unitRepository.existsByFloorAndNumberAndBuildingID(unit.getPiso(), unit.getNumero(), unit.getCodigoEdificio())) {
                return this.unitRepository.saveUnit(unit.getPiso(), unit.getNumero(), unit.getCodigoEdificio());
            } else throw new LaUnidadYaFueCreada("La unidad que intenta crear ya existe en el edificio");

        } else
            throw new BuildingNotFoundException("El código del edificio ingresado no corresponde a un edificio del consorcio");
    }

    @Override
    public void deleteUnit(Integer unitID) {
        unitRepository.deleteById(unitID);
    }

    @Override
    public Unit updateUnit(Unit newUnit, Integer unitID) {
        return unitRepository.findById(unitID).map(unidad -> {
            unidad.setFloor(newUnit.getFloor());
            unidad.setNumber(newUnit.getNumber());
            unidad.setHabitado(newUnit.getHabitado());
            return unitRepository.save(unidad);
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
        } else throw new UnitNotFoundException("El id ingresado no corresponde a una unidad existente");
    }

}
