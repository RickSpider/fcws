/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.controller;

import com.nm.fcws.interfaces.iCancelacion;
import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.services.ComprobanteLoteServicio;
import com.nm.fcws.services.ContribuyenteServicio;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.exceptions.SifenException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

/**
 *
 * @author BlackSpider
 */
@RestController
public class ComprobanteLoteController {
    
    @Autowired
    private ComprobanteLoteServicio cls;
    
    @Autowired
    private ContribuyenteServicio contribuyenteServicio;
    
    @PostMapping(value = "/enviarlote", produces = "application/json")
    public @ResponseBody
    ResponseEntity enviarLote(@Validated(iCancelacion.class) @RequestBody Contribuyente contribuyente) throws SifenException, ParserConfigurationException, SAXException, IOException {
    
        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(contribuyente.getContribuyenteid(), contribuyente.getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }
        
        List<DocumentoElectronico> lde = cls.generarLote(oContribuyente.get());
        
        if (lde.size() <= 0){
        
            return new ResponseEntity("No hay documentos a enviar",HttpStatus.OK);
            
        }
        
        cls.enviarLote(lde, oContribuyente.get());
        
        return new ResponseEntity("El Lote enviado contiene "+lde.size()+" lala",HttpStatus.CREATED);
    }
}
