package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerAlreadyAssignedToUnitException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Owner;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.OwnerDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.OwnerResponseDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.OwnerRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final UnitRepository unitRepository;
    private final PersonRepository personRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, UnitRepository unitRepository, PersonRepository personRepository) {
        this.ownerRepository = ownerRepository;
        this.unitRepository = unitRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<OwnerResponseDto> findAll() {
        List<OwnerResponseDto> ownersResponseDto = new ArrayList<>();
        ownerRepository.findAll().forEach(owner -> {
            OwnerResponseDto ownerResponseDto = new OwnerResponseDto(owner);
            ownersResponseDto.add(ownerResponseDto);
        });
        return ownersResponseDto;
    }

    @Override
    @Transactional
    public void assignOwnerToUnit(OwnerDto ownerDto) throws DocumentNotFoundException, OwnerAlreadyAssignedToUnitException, UnitNotFoundException {
        //document must exist in the condo
        Optional<Person> personByID = this.personRepository.findById(ownerDto.getDocument());
        if (personByID.isPresent()) {
            if (this.isValidToAssign(ownerDto)) {
                this.ownerRepository.assignOwnerToUnit(ownerDto.getUnitID(), ownerDto.getDocument());
            }
        } else
            throw new DocumentNotFoundException("The document is not found in the condo data.");
    }

    private boolean isValidToAssign(OwnerDto ownerDto) throws OwnerAlreadyAssignedToUnitException, UnitNotFoundException {
        Optional<Unit> unitByID = this.unitRepository.findById(ownerDto.getUnitID());
        //check if the unit exist
        if (unitByID.isPresent()) {
            //check if the owner was assigned to this unit
            boolean assigned = this.ownerRepository.existsByDocumentAndUnitID(ownerDto.getDocument(), unitByID.get().getUnitID());
            if (!assigned) {
                return true;
            } else
                throw new OwnerAlreadyAssignedToUnitException("The owner has already been assigned to this unit.");
        }
        throw new UnitNotFoundException("Unit not found");
    }

    @Override
    public List<Owner> findByBuildingID(Integer buildingID) {
        return this.ownerRepository.ownersByBuildingID(buildingID);
    }

    @Override
    @Transactional
    public void assignOwnerToUnit(String newOwnerDocument, Integer unitID, String previousOwnerDocument) throws OwnerNotFoundException, DocumentNotFoundException {
        //person is register in the condo?
        Optional<Person> newOwner = this.personRepository.findByDocument(newOwnerDocument);

        if (newOwner.isEmpty()) {
            throw new DocumentNotFoundException("The document is not found in the condo data.");
        }
        Owner owner = this.ownerRepository.findByDocumentAndUnitID(previousOwnerDocument, unitID);
        if (owner != null) {
            owner.setPerson(newOwner.get());
            this.ownerRepository.save(owner);
        } else throw new OwnerNotFoundException("Owner not found in that unit.");
    }

    @Override
    public List<OwnerResponseDto> findByParameter(Integer unitID, Integer ownerID, String document) throws OwnerNotFoundException {
        List<OwnerResponseDto> ownersResponseDto = new ArrayList<>();
        List<Owner> owners = this.ownerRepository.findAllByUnitIDOrIdOrDocument(unitID, ownerID, document);
        if (!owners.isEmpty()) {
            owners.forEach(owner -> {
                OwnerResponseDto ownerResponseDto = new OwnerResponseDto(owner);
                ownersResponseDto.add(ownerResponseDto);
            });
        } else throw new OwnerNotFoundException("Owners not found.");
        return ownersResponseDto;
    }

    @Override
    public void remove(Integer id) {
        ownerRepository.deleteById(id);
    }


}
