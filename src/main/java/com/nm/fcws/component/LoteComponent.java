/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.component;

import com.nm.fcws.controller.ComprobanteController;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.repo.ContribuyenteRepo;
import com.nm.fcws.services.ComprobanteLoteServicio;
import com.roshka.sifen.core.exceptions.SifenException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

/**
 *
 * @author blackspider
 */
@Component
public class LoteComponent {
    
    private static Logger log = LoggerFactory.getLogger(LoteComponent.class);
    @Autowired
    private ComprobanteLoteServicio cls;
    
    @Autowired
    private ContribuyenteRepo cr;
    
    //@Scheduled(fixedRate = 30000)
   // @Scheduled(fixedRate = 7200000) //2horas
    @Scheduled(cron="0 10 8,10,12,14,16,18 * * ?")
    public void envio() throws SifenException, ParserConfigurationException, SAXException, IOException{
        
        log.info("Iniciando envio atuomatico de lotes");
        
        List <Contribuyente> lc = (List <Contribuyente>) cr.findBySoloLote(true);
        
        cls.enviarLoteContribuyentes(lc);
        
    }
 
    @Scheduled(cron="0 10 9,11,13,15,17,19 * * ?")
    public void consultar() throws SifenException, ParserConfigurationException, SAXException, IOException{
    
        log.info("Ejecutando Consulta de Lotes");
        //System.out.println("Iniciar consulta we");
        
        cls.ConsultarLotes();
    }
    
    
    
}
