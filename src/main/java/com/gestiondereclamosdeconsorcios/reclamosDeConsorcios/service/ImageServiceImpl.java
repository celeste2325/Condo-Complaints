package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ReclamoInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Image;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImagenDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ComplaintRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ComplaintRepository complaintRepository;

    @Override
    public void addImage(ImagenDto imageDto) throws ReclamoInexistenteException {
        Optional<Complaint> reclamo = this.complaintRepository.findById(imageDto.getIdReclamo());
        if (reclamo.isPresent() && !reclamo.get().getStatus().equalsIgnoreCase("terminado")) {
            reclamo.get().setImagesByComplaintID(imageDto.getImagenes());
            imageDto.getImagenes().forEach(imagen -> {
                imagen.getComplaintsByComplaintID().setComplaintID(imageDto.getIdReclamo());
                imagen.setPath("assets//" + imagen.getPath());
            });
            this.complaintRepository.save(reclamo.get());
        } else
            throw new ReclamoInexistenteException("No existe el reclamo");
    }

    @Override
    public Image getImageByComplaintID(String complaintID) {
        return this.imageRepository.getImageByComplaintID(Integer.parseInt(complaintID));
    }

}
