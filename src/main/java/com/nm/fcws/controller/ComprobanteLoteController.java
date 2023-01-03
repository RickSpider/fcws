/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.controller;

import com.nm.fcws.interfaces.iCancelacion;
import com.nm.fcws.interfaces.iLoginPost;
import com.nm.fcws.modeldb.Lote;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.services.ComprobanteLoteServicio;
import com.nm.fcws.services.ContribuyenteServicio;
import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.response.RespuestaConsultaLoteDE;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

/**
 *
 * @author BlackSpider
 */
@RestController
@RequestMapping("/lote")
public class ComprobanteLoteController {
    
    @Autowired
    private ComprobanteLoteServicio cls;
    
    @Autowired
    private ContribuyenteServicio contribuyenteServicio;
    
    @PostMapping(value = "/enviar", produces = "application/json")
    public @ResponseBody
    ResponseEntity enviarLote(@Validated(iLoginPost.class) @RequestBody Contribuyente contribuyente) throws SifenException, ParserConfigurationException, SAXException, IOException {
    
        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(contribuyente.getContribuyenteid(), contribuyente.getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }
        
        cls.enviarLotes(oContribuyente.get());
        
        return new ResponseEntity("Iniciando envio de lotes manual.",HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/consultar", produces = "application/json")
    public @ResponseBody
    ResponseEntity consultarLote(@RequestBody Lote lote) throws SifenException, ParserConfigurationException, SAXException, IOException {
        
        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(lote.getContribuyente().getContribuyenteid(), lote.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        return  new ResponseEntity(cls.consultarLote(lote),HttpStatus.OK);
        
    }
    
    
}
