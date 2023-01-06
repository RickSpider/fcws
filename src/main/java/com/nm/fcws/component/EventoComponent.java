/*
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
import com.nm.fcws.services.EventoServicio;
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
public class EventoComponent {
    
    private static Logger log = LoggerFactory.getLogger(EventoComponent.class);
    @Autowired
    private EventoServicio es;
    


     @Scheduled(cron="0 50 9,11,13,15,17,19 * * ?")
    public void envio() throws SifenException, ParserConfigurationException, SAXException, IOException{
        
        log.info("Iniciando envio atuomatico de Eventos");
        
        es.enviarEventos();
        
    }
 
   /* @Scheduled(cron="0 10 9,11,13,15,17,19 * * ?")
    public void consultar() throws SifenException, ParserConfigurationException, SAXException, IOException{
    
        log.info("Ejecutando Consulta de Lotes");
        //System.out.println("Iniciar consulta we");
        
        cls.ConsultarLotes();
    }*/
    
    
    
}
