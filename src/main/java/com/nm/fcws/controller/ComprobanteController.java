/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.model.Comprobante;
import com.nm.fcws.repo.ContribuyenteRepo;
import com.nm.fcws.repo.RucRepo;
import com.nm.fcws.services.ComprobanteServicio;
import com.roshka.sifen.core.exceptions.SifenException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

/**
 *
 * @author BlackSpider
 */


@RestController
public class ComprobanteController {
    
    private static Logger log = LoggerFactory.getLogger(ComprobanteController.class);
    
    @Autowired
    private ContribuyenteRepo contribuyenteRepo;
    
    @Autowired
    private ComprobanteServicio comprobanteServicio;
    
    @Autowired 
    private RucRepo rucRepo;
    
    
    
    @PostMapping(value = "/factura",produces ="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void comprobante(@RequestBody Comprobante factura) throws SifenException, ParserConfigurationException, SAXException, IOException{
        
       log.info("Esto llego"+factura.getTimbradoFecIni().getTime());
       log.info("Esto llego"+factura.getTimbradoFecIni());
        
        comprobanteServicio.procesar(factura);
        //RespuestaRecepcionDE rrde = Sifen.recepcionDE(de,config);
        
        
        
       
        //return de ;
    }
    
   
    
       
    
    
    
}
