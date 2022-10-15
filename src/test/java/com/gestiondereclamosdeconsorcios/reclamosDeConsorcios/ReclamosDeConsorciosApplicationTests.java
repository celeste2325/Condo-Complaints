package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.InquilinoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReclamosDeConsorciosApplicationTests {

    @Autowired
    InquilinoRepository inquiRepo;


    @Test
    void contextLoads() {
        var encriptor = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, new SecureRandom("cele".getBytes()));
        var result = encriptor.encode("1234");
        var result2 = encriptor.encode("1234");

        var result24 =encriptor.matches("123",result);
        var result25 =encriptor.matches("123",result2);

        assertEquals(result24, result25);


    }

    @Test
    void TestInquilinoRepo() {

        var inquilino = inquiRepo.findDistinctByDocumento("CI 13230978");
        inquilino.forEach(inquilino1 -> {
            System.out.println("identificador:"+inquilino1.getIdentificador().toString());
            System.out.println("ID:"+inquilino1.getId().toString());
        });
        System.out.printf(inquilino.toString());
        //fmt
       // inquilino.

    }

}
