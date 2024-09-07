package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ReclamoInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Imagen;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImagenDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ImagenRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImagenRepository imagenRepository;

    @Autowired
    ReclamoRepository reclamoRepository;

    @Override
    public void agregarImagen(ImagenDto imagenes) throws ReclamoInexistenteException {
        Optional<Reclamo> reclamo = this.reclamoRepository.findById(imagenes.getIdReclamo());
        if (reclamo.isPresent() && !reclamo.get().getEstado().equalsIgnoreCase("terminado")) {
            reclamo.get().setImagenesByIdReclamo(imagenes.getImagenes());
            imagenes.getImagenes().forEach(imagen -> {
                imagen.getReclamosByIdReclamo().setIdReclamo(imagenes.getIdReclamo());
                imagen.setDataFoto("assets//" + imagen.getDataFoto());
            });
            this.reclamoRepository.save(reclamo.get());
        } else
            throw new ReclamoInexistenteException("No existe el reclamo");
    }

    @Override
    public Imagen getImageByComplaintID(String complaintID) {
        return this.imagenRepository.getImageByComplaintID(Integer.parseInt(complaintID));
    }

}
