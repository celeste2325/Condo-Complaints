package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.*;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

public interface DuenioRepository extends JpaRepository<Duenios, Integer> {

    List<Duenios> findByDocumento(String Documento);

}
