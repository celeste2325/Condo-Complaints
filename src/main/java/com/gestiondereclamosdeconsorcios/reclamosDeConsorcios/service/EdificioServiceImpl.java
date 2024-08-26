package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.BuildingWithUnitsByTenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnitDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EdificioServiceImpl implements EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    @Override
    @Transactional
    public void saveEdificio(Edificio edificio) {
        edificioRepository.save(edificio);
    }

    @Override
    public List<Edificio> getEdificioByName(String name) {
        return null;
    }

    @Override
    public List<Edificio> getAll() {
        return edificioRepository.findAll();
    }

    @Override
    public void remove(Integer id) {
        edificioRepository.deleteById(id);
    }

    @Override
    public Edificio update(Edificio newEdificio, Integer id) {
        return edificioRepository.findById(id).map(edificio -> {
            edificio.setDireccion(newEdificio.getDireccion());
            edificio.setNombre(newEdificio.getNombre());
            return edificioRepository.save(edificio);
        }).get();
    }

    @Override
    public List<Inquilino> getHabitantes(Integer codigo) {
        return this.edificioRepository.getHabitantes(codigo);
    }

    @Override
    public List<Inquilino> getHabilitados(Integer codigo) {
        List<Object[]> habilitados = this.edificioRepository.getHabilitados(codigo);
        return habilitados
                .stream()
                .map(habilitado -> new Inquilino(((Integer) habilitado[0]), (Integer) habilitado[1], (String) habilitado[2]))
                .collect(Collectors.toList());
    }

    @Override
    public Edificio getEdificio(Integer codigo) {
        return this.edificioRepository.findById(codigo).get();
    }

    @Override
    public List<BuildingWithUnitsByTenant> getBuildingByTenant(String tenantDocumentID) {
        List<Object[]> results = this.edificioRepository.getBuildingByTenant(tenantDocumentID);
        List<BuildingWithUnitsByTenant> buildings = new ArrayList<>();

        //// Transforms the received data into the BuildingWithUnitsByTenant DTO.
        for (Object[] row : results) {
            if (!buildings.stream()
                    .anyMatch(buil -> buil.getBuildingID() == (Integer) row[5])) {
                BuildingWithUnitsByTenant building = new BuildingWithUnitsByTenant(
                        (String) row[0],    // buildingName
                        (String) row[1],    // buildingAddress
                        (Integer) row[2],   // unitID
                        (String) row[3],    // floor
                        (String) row[4],    // unitNumber
                        (Integer) row[5]    // buildingID
                );
                buildings.add(building);
            } else {
                //add the unit to the existing building
                for (BuildingWithUnitsByTenant building : buildings) {
                    if (building.getBuildingID() == (Integer) row[5]) {
                        UnitDto unit = new UnitDto((Integer) row[2], (String) row[3], (String) row[4]);
                        building.getUnits().add(unit);
                    }
                }
            }
        }
        return buildings;
    }
}
