/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.services;

import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.repo.ContribuyenteRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author BlackSpider
 */
@Service
public class ContribuyenteServicio {
    
        @Autowired
        private ContribuyenteRepo contribuyenteRepo;
    
        public Optional<Contribuyente> verfificarContribuyente(Long id, String pass) {

        Optional<Contribuyente> oContribuyente = contribuyenteRepo.findById(id);

        if (oContribuyente.isPresent()) {

            if (oContribuyente.get().getPass().compareTo(pass) == 0) {

                return oContribuyente;

            }

        }

        return Optional.empty();

    }
    
}
