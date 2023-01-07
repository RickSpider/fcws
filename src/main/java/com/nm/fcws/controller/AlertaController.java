/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.services.ComprobanteLoteServicio;
import com.nm.fcws.services.ComprobanteServicio;
import com.nm.fcws.services.EventoServicio;
import com.roshka.sifen.core.exceptions.SifenException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

/**
 *
 * @author blackspider
 */
@RestController
@RequestMapping("/alerta")
public class AlertaController {
    
    @Autowired
    private ComprobanteServicio cs;
    
    @Autowired
    private ComprobanteLoteServicio ls;
    
    @Autowired
    private EventoServicio es;

    @GetMapping(value = "/enviar")
    public @ResponseBody
    ResponseEntity consultarLotes() throws SifenException, ParserConfigurationException, SAXException, IOException {
        
       
        cs.alertaComprobante();
        ls.alertaLote();
        es.alertaEvento();
        
        return  new ResponseEntity("Inicio manual de envio de Alertas Generales",HttpStatus.OK);
        
    }
    
}
