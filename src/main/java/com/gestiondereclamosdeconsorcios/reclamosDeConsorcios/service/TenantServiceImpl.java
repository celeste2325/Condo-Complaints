package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoAsignadoPreviamenteAlAUnidadException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Tenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.TenantDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.TenantResponseDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.TenantRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;
    private final UnitRepository unitRepository;
    private final PersonRepository personRepository;

    public TenantServiceImpl(TenantRepository tenantRepository, UnitRepository unitRepository, PersonRepository personRepository) {
        this.tenantRepository = tenantRepository;
        this.unitRepository = unitRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<TenantResponseDto> findAll() {
        List<TenantResponseDto> inquilinos = new ArrayList<>();
        tenantRepository.findAll().stream().forEach(inquilino -> {
            TenantResponseDto inquilinoDto = new TenantResponseDto(inquilino);
            inquilinos.add(inquilinoDto);
        });
        return inquilinos;
    }

    @Override
    public TenantResponseDto findByID(Integer id) throws IdInexistenteException {
        Optional<Tenant> tenantByID = this.tenantRepository.findById(id);
        if (tenantByID.isPresent()) {
            return new TenantResponseDto(tenantByID.get());
        } else throw new IdInexistenteException("El id ingresado no corresponde a un inquilino registrado");
    }

    @Override
    public List<TenantResponseDto> findByDocument(String document) throws DocumentoNoEncontradoException {
        List<TenantResponseDto> inquilinosDto = new ArrayList<>();
        List<Tenant> inquilinos = this.tenantRepository.findByDocument(document);
        if (!inquilinos.isEmpty()) {
            inquilinos.stream().forEach(inquilino -> {
                TenantResponseDto inquilinoDto = new TenantResponseDto(inquilino);
                inquilinosDto.add(inquilinoDto);
            });
        } else throw new DocumentoNoEncontradoException("El dni ingresado no corresponde a un inquilino del consorcio");

        return inquilinosDto;
    }

    @Override
    public void releaseUnit(Integer unitID, Integer buildingID) throws UnitNotFoundException {
        Optional<Unit> unit = this.unitRepository.findById(unitID);

        if (unit.isPresent()) {
            if (unit.get().getBuildingID() == buildingID) {
                this.tenantRepository.deleteByUnitID(unitID);
                unit.get().setHabitado("N");
                this.unitRepository.save(unit.get());
            } else throw new UnitNotFoundException("No existe una unidad en el edificio ingresado");

        } else throw new UnitNotFoundException("Unidad inexistente");

    }

    @Transactional
    @Override
    public void createTenant(TenantDto newTenant) throws DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException, UnitNotFoundException {
        //para validar si la persona a la que se quiere asignar como dueño ya existe en la base
        boolean existeLaPersona = this.personRepository.existsById(newTenant.getDocument());

        //para validar si existe la unidad a la cual quiere asignar el dueño.
        Optional<Unit> unidad = this.unitRepository.findById(newTenant.getUnitID());

        //para validar si el dueño a asignar ya fue asignado a esa misma unidad
        boolean yaFueAsignado = this.tenantRepository.existsByDocumentAndUnitID(newTenant.getDocument(), unidad.get().getUnitID());

        if (existeLaPersona) {
            if (unidad.isPresent()) {
                if (!yaFueAsignado) {
                    unidad.get().setHabitado("S");
                    this.unitRepository.save(unidad.get());
                    this.tenantRepository.asignarDuenio(newTenant.getUnitID(), newTenant.getDocument());
                    System.out.println(unidad.get().getHabitado());
                } else
                    throw new DocumentoAsignadoPreviamenteAlAUnidadException("Ya el inquilino fue asignado previamente a la unidad");
            } else
                throw new UnitNotFoundException("La unidad no existe");
        } else
            throw new DocumentoNoEncontradoException("El documento del inquilino que desea asignar no corresponde a una persona registrada en el consorcio");
    }

    @Override
    public void delete(Integer id) {
        this.tenantRepository.deleteById(id);

    }


}
