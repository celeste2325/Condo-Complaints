package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerAlreadyAssignedToUnitException;
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
import java.util.Objects;
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
        List<TenantResponseDto> tenants = new ArrayList<>();
        tenantRepository.findAll().forEach(tenant -> {
            TenantResponseDto tenantResponseDto = new TenantResponseDto(tenant);
            tenants.add(tenantResponseDto);
        });
        return tenants;
    }

    @Override
    public TenantResponseDto findByID(Integer id) throws IdNotFoundException {
        Optional<Tenant> tenantByID = this.tenantRepository.findById(id);
        if (tenantByID.isPresent()) {
            return new TenantResponseDto(tenantByID.get());
        } else throw new IdNotFoundException("ID not found");
    }

    @Override
    public List<TenantResponseDto> findByDocument(String document) throws DocumentNotFoundException {
        List<TenantResponseDto> tenantsResponseDto = new ArrayList<>();
        List<Tenant> tenants = this.tenantRepository.findByDocument(document);
        if (!tenants.isEmpty()) {
            tenants.forEach(tenant -> {
                TenantResponseDto tenantResponseDto = new TenantResponseDto(tenant);
                tenantsResponseDto.add(tenantResponseDto);
            });
        } else throw new DocumentNotFoundException("The document is not found in the condo data.");

        return tenantsResponseDto;
    }

    @Override
    public void releaseUnit(Integer unitID, Integer buildingID) throws UnitNotFoundException {
        Optional<Unit> unit = this.unitRepository.findById(unitID);

        if (unit.isPresent()) {
            if (Objects.equals(unit.get().getBuildingID(), buildingID)) {
                this.tenantRepository.deleteByUnitID(unitID);
                unit.get().setOccupied("N");
                this.unitRepository.save(unit.get());
            } else throw new UnitNotFoundException("unit not found in the building");

        } else throw new UnitNotFoundException("Unit not found");

    }

    @Transactional
    @Override
    public void assignTenantToUnit(TenantDto newTenant) throws DocumentNotFoundException, OwnerAlreadyAssignedToUnitException, UnitNotFoundException {
        //person is register in the condo?
        boolean personExists = this.personRepository.existsById(newTenant.getDocument());
        Optional<Unit> unit = this.unitRepository.findById(newTenant.getUnitID());

        if (!personExists) {
            throw new DocumentNotFoundException("The document is not found in the condo data.");
        }
        if (unit.isEmpty()) {
            throw new UnitNotFoundException("Unit not found");
        }
        // Validate if the tenant has already been assigned to the unit
        boolean isTenantAssigned = this.tenantRepository.existsByDocumentAndUnitID(newTenant.getDocument(), unit.get().getUnitID());
        if (isTenantAssigned) {
            throw new OwnerAlreadyAssignedToUnitException("The tenant has already been assigned to this unit.");
        }
        unit.get().setOccupied("Y");
        this.unitRepository.save(unit.get());
        this.tenantRepository.assignTenant(newTenant.getUnitID(), newTenant.getDocument());
    }

    @Override
    public void delete(Integer id) {
        this.tenantRepository.deleteById(id);

    }
}
