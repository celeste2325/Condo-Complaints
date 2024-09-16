package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ComplaintNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Image;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImageDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ComplaintRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String UPLOAD_DIR = "D:\\programacion2024\\Condo-Complaints\\src\\main\\java\\com\\gestiondereclamosdeconsorcios\\reclamosDeConsorcios\\";
    private final ImageRepository imageRepository;
    private final ComplaintRepository complaintRepository;

    public ImageServiceImpl(ImageRepository imageRepository, ComplaintRepository complaintRepository) {
        this.imageRepository = imageRepository;
        this.complaintRepository = complaintRepository;
    }

    @Override
    public void addImage(ImageDto imageDto) throws ComplaintNotFoundException {
        Optional<Complaint> complaint = this.complaintRepository.findById(imageDto.getComplaintID());
        if (complaint.isPresent() && !complaint.get().getStatus().equalsIgnoreCase("terminado")) {
            complaint.get().setImagesByComplaintID(imageDto.getImages());
            imageDto.getImages().forEach(image -> {
                image.getComplaintsByComplaintID().setComplaintID(imageDto.getComplaintID());
                image.setPath("assets//" + image.getPath());
            });
            this.complaintRepository.save(complaint.get());
        } else
            throw new ComplaintNotFoundException("Complaint not found.");
    }

    @Override
    public Resource findByComplaintID(String complaintID) throws MalformedURLException {
        Image imageByComplaintID = this.imageRepository.getImageByComplaintID(Integer.parseInt(complaintID));
        Path imagePath = Paths.get(UPLOAD_DIR + imageByComplaintID.getPath() + "." + imageByComplaintID.getExtension());
        Resource image = new UrlResource(imagePath.toUri());
        return image;
    }
}
