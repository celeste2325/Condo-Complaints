package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DuenioAsignadoPreviamenteAlAUnidadException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.DuenioRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DuenioServiceImpl implements DuenioService {
    @Autowired
    DuenioRepository duenioRepository;
    @Autowired
    UnidadRepository unidadRepository;
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Duenio> getAll() {
        return duenioRepository.findAll();
    }

    @Override
    @Transactional
    public void saveDuenio(DuenioDto newDuenio) throws DocumentoNoEncontradoException, DuenioAsignadoPreviamenteAlAUnidadException {
        //para validar si la persona a la que se quiere asignar como dueño ya existe en la base
        Persona personaEncontrada = this.personaRepository.buscarPersona(newDuenio.getDocumento());
        if (this.losDatosSonValidos(newDuenio)) {
            if (personaEncontrada != null) {
                this.duenioRepository.asignarDuenio(newDuenio.getIdentificador(), newDuenio.getDocumento());
            } else
                throw new DocumentoNoEncontradoException("El documento del dueño que desea asignar no corresponde a una persona registrada en el consorcio");
        }
    }

    private boolean losDatosSonValidos(DuenioDto newDuenio) throws DuenioAsignadoPreviamenteAlAUnidadException {
        //para validar si existe la unidad a la cual quiere asignar el dueño.
        Optional<Unidad> unidadEncontrada = this.unidadRepository.findById(newDuenio.getIdentificador());

        //para validar si el dueño a asignar ya fue asignado a esa misma unidad
        Duenio unidadConDuenioAsignado = this.duenioRepository.findDuenioByDocumentoAndIdentificador(newDuenio.getDocumento(), unidadEncontrada.get().getIdentificador());
        if (unidadEncontrada.isPresent()) {
            if (unidadConDuenioAsignado == null) {
                return true;
            } else
                throw new DuenioAsignadoPreviamenteAlAUnidadException("Ya el duenio fue asignado previamente a la unidad");
        }
        return false;

    }

    /*@Override
    @Transactional
    public void modificarLaUnidadAsignada(Integer unidadAsignada, Integer unidadNueva, String documento) throws DuenioAsignadoPreviamenteAlAUnidadException, DocumentoNoEncontradoException {

        if (this.losDatosSonValidos(newDuenio)) {
                this.duenioRepository.modificarLaUnidadAsignada(unidadAsignada, unidadNueva, documento);
        }

    }*/

    @Override
    public void remove(Integer id) {
        duenioRepository.eliminarDuenioDeLaUnidad(id);
    }


}
