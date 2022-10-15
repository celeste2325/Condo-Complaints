package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.*;

import java.util.List;

public interface DuenioRepository extends JpaRepository<Duenios, Integer> {

    List<Duenios> findByDocumento(String Documento);

}
