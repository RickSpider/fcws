/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.model.Comprobante;
import com.nm.fcws.model.Kude;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.repo.ContribuyenteRepo;
import com.nm.fcws.repo.RucRepo;
import com.nm.fcws.services.ComprobanteServicio;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.exceptions.SifenException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody ResponseEntity comprobante(@RequestBody Comprobante factura) throws SifenException, ParserConfigurationException, SAXException, IOException{
        
       //log.info("Esto llego"+factura.getTimbradoFecIni().getTime());
       //log.info("Esto llego"+factura.getTimbradoFecIni());
       
        String chequear = chequearCampos(factura) ;
        
        if (chequear != null){
        
            return new ResponseEntity(chequear, HttpStatus.BAD_REQUEST);
            
        }
        
        Contribuyente contribuyente = contribuyenteRepo.findById(factura.getContribuyente().getContribuyenteid()).get();
        
        DocumentoElectronico de = comprobanteServicio.procesar(factura, contribuyente);
        String cdc = de.obtenerCDC();
        log.info(cdc);
        Kude kude = new Kude(de.getEnlaceQR(), cdc);
        
        comprobanteServicio.enviarDE(de, contribuyente,cdc);
 
        return new ResponseEntity(kude,HttpStatus.CREATED);
    }

    private String chequearCampos(Comprobante comprobante){
        
        if (comprobante.getTimbrado() == null || comprobante.getTimbrado().length() != 8){
        
            return "Error en datos de timbrado";
            
        }
        
        if (comprobante.getEstablecimiento() == null || comprobante.getEstablecimiento().length() != 3){
        
            return "Error en datos de Establecimiento";
            
        }
        
        if (comprobante.getPuntoExpedicion()== null || comprobante.getPuntoExpedicion().length() != 3){
        
            return "Error en datos de Punto de Expedicion";
           
        }
        
        if (comprobante.getPuntoExpedicion()== null || comprobante.getPuntoExpedicion().length() != 3){
        
            return "Error en datos de Punto de Expedicion";
           
        }
        
        if (comprobante.getDocumentoNum()== null || comprobante.getDocumentoNum().length() != 7){
        
            return "Error en datos en el Numero de documento";
           
        }
        
        
        if (comprobante.getTimbradoFecIni()== null){
        
            return "Error en datos de Fecha de Timbrado";
           
        }
        
         if (comprobante.getFecha()== null){
        
            return "Error en datos de Fecha del comprobante";
           
        }
        
        if (comprobante.getSucursal() == null || comprobante.getSucursal().length() == 0){
            
            return "Error en datos de Sucursal";
            
        }
        
        if (comprobante.getTiposPagos().size() <= 0){
        
            return "Error no hay datos de pago";
            
        }
        
        if (comprobante.getDetalles().size() <= 0){
        
            return "Error no hay detalles de factora";
            
        }

        return null;
    }
   
    
       
    
    
    
}
