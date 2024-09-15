package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.LaUnidadYaFueCreada;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.BuildingRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.DuenioRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private DuenioRepository dueniosRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Integer saveUnit(UnidadDto unit) throws BuildingNotFoundException, LaUnidadYaFueCreada {
        if (this.buildingRepository.existsById(unit.getCodigoEdificio())) {
            if (!this.unitRepository.existsByFloorAndNumberAndBuildingID(unit.getPiso(), unit.getNumero(), unit.getCodigoEdificio())) {
                return this.unitRepository.saveUnit(unit.getPiso(), unit.getNumero(), unit.getCodigoEdificio());
            } else throw new LaUnidadYaFueCreada("La unidad que intenta crear ya existe en el edificio");

        } else
            throw new BuildingNotFoundException("El código del edificio ingresado no corresponde a un edificio del consorcio");
    }

    @Override
    public void remove(Integer id) {
        unitRepository.deleteById(id);
    }

    @Override
    public Unit update(Unit newUnit, Integer unitID) {
        return unitRepository.findById(unitID).map(unidad -> {
            unidad.setFloor(newUnit.getFloor());
            unidad.setNumber(newUnit.getNumber());
            unidad.setHabitado(newUnit.getHabitado());
            return unitRepository.save(unidad);
        }).get();
    }

    @Override
    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    @Override
    public Unit getID(Integer unitID) throws UnitNotFoundException {
        Optional<Unit> unidad = this.unitRepository.findById(unitID);
        if (unidad.isPresent()) {
            return unidad.get();
        } else throw new UnitNotFoundException("El id ingresado no corresponde a una unidad existente");
    }

}
