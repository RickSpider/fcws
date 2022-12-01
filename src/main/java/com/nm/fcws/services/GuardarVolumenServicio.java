/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.services;

import com.nm.fcws.modeldb.Ruc;
import com.nm.fcws.repo.RucRepo;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author BlackSpider
 */
@Service
public class GuardarVolumenServicio {
    
     private static Logger log = LoggerFactory.getLogger(rucService.class);
    
    @Autowired
    private RucRepo rucRepo;
    
    @Async
    public void guardar(List<Ruc> lruc, long start ,int registro, int cont){
        
            log.info("iniciando guardado "+lruc.size()+" "+registro+" "+cont);
            rucRepo.saveAll(lruc);
            long end = System.currentTimeMillis() - start;
            log.info("Fin Carga Asincrona ejecucion "+registro+" "+cont+" "+ end);
        
    }
    
}
