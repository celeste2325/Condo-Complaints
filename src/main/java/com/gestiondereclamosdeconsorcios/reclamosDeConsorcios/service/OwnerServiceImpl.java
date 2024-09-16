package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoAsignadoPreviamenteAlAUnidadException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoSeEncontraronDueniosException;
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
        List<OwnerResponseDto> dueniosDto = new ArrayList<>();
        ownerRepository.findAll().stream().forEach(duenio -> {
            OwnerResponseDto duenioDto = new OwnerResponseDto(duenio);
            dueniosDto.add(duenioDto);
        });
        return dueniosDto;
    }

    @Override
    @Transactional
    public void createOwner(OwnerDto newDuenio) throws DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException {
        //para validar si la persona a la que se quiere asignar como dueño ya existe en la base
        Optional<Person> personaEncontrada = this.personRepository.findById(newDuenio.getDocument());
        if (this.losDatosSonValidos(newDuenio)) {
            if (personaEncontrada.isPresent()) {
                this.ownerRepository.asignarDuenio(newDuenio.getUnitID(), newDuenio.getDocument());
            } else
                throw new DocumentoNoEncontradoException("El documento del dueño que desea asignar no corresponde a una persona registrada en el consorcio");
        }
    }

    private boolean losDatosSonValidos(OwnerDto newDuenio) throws DocumentoAsignadoPreviamenteAlAUnidadException {
        //para validar si existe la unidad a la cual quiere asignar el dueño.
        Optional<Unit> unidadEncontrada = this.unitRepository.findById(newDuenio.getUnitID());

        //para validar si el dueño a asignar ya fue asignado a esa misma unidad
        boolean yaFueAsignado = this.ownerRepository.existsByDocumentAndUnitID(newDuenio.getDocument(), unidadEncontrada.get().getUnitID());
        if (unidadEncontrada.isPresent()) {
            if (!yaFueAsignado) {
                return true;
            } else
                throw new DocumentoAsignadoPreviamenteAlAUnidadException("Ya el duenio fue asignado previamente a la unidad");
        }
        return false;

    }

    @Override
    public List<Owner> dueniosPorEdificio(Integer codigo) {
        return this.ownerRepository.ownersByBuildingID(codigo);
    }

    @Override
    @Transactional
    public void update(String newDocumentoDuenio, Integer id, String documentoDuenioAntiguo) throws NoSeEncontraronDueniosException {
        Owner duenio = this.ownerRepository.findByDocumentAndUnitID(documentoDuenioAntiguo, id);
        if (duenio != null) {
            duenio.setDocument(newDocumentoDuenio);
            this.ownerRepository.deleteById(duenio.getId());
            this.ownerRepository.asignarDuenio(id, duenio.getDocument());
        } else throw new NoSeEncontraronDueniosException("No existe el dueño");
    }

    @Override
    public List<OwnerResponseDto> findByParameter(Integer codigoUnidad, Integer idDuenio, String documento) throws NoSeEncontraronDueniosException {
        List<OwnerResponseDto> dueniosDto = new ArrayList<>();
        List<Owner> duenios = this.ownerRepository.findAllByUnitIDOrIdOrDocument(codigoUnidad, idDuenio, documento);
        if (!duenios.isEmpty()) {
            duenios.stream().forEach(duenio -> {
                OwnerResponseDto duenioDto = new OwnerResponseDto(duenio);
                dueniosDto.add(duenioDto);
            });
        } else throw new NoSeEncontraronDueniosException("No se encontraron dueños");
        return dueniosDto;
    }

    @Override
    public void remove(Integer id) {
        ownerRepository.deleteById(id);
    }


}
