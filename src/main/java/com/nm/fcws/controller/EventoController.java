/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.interfaces.iCancelacion;
import com.nm.fcws.model.Comprobante;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.services.ContribuyenteServicio;
import com.nm.fcws.services.EventoServicio;
import com.roshka.sifen.core.exceptions.SifenException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

/**
 *
 * @author blackspider
 */
@RestController
@RequestMapping("/evento")
public class EventoController {
    
    @Autowired
    private ContribuyenteServicio contribuyenteServicio;
    
    @Autowired
    private EventoServicio eventoServicio;
    
    
    @PostMapping(value = "/cancelarfactura", produces = "application/json")
    public @ResponseBody
    ResponseEntity cancelarFactura(
            @RequestBody Comprobante comprobante) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(comprobante.getContribuyente().getContribuyenteid(), comprobante.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

         eventoServicio.guardarEventoCancelacion(comprobante, oContribuyente.get(), "1");
        
       // EventosDE ede = eventoServicio.procesarCancelacion(comprobante, oContribuyente.get(), "1");
        
       // eventoServicio.enviarEvento(ede, oContribuyente.get(), comprobante.getCdc());

        return new ResponseEntity("Cancelacion de factura enviada",HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/cancelarnotacredito", produces = "application/json")
    public @ResponseBody
    ResponseEntity cancelarNotaCredito(@Validated(iCancelacion.class) @RequestBody Comprobante comprobante) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(comprobante.getContribuyente().getContribuyenteid(), comprobante.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        
        //EventosDE ede = eventoServicio.procesarCancelacion(comprobante, oContribuyente.get(), "5");
        
        //eventoServicio.enviarEvento(ede, oContribuyente.get(), comprobante.getCdc());
        
         eventoServicio.guardarEventoCancelacion(comprobante, oContribuyente.get(), "5");

        return new ResponseEntity("Cancelacion de Nota de Credito recibida",HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/cancelarremision", produces = "application/json")
    public @ResponseBody
    ResponseEntity cancelarRemision(@Validated(iCancelacion.class) @RequestBody Comprobante comprobante) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(comprobante.getContribuyente().getContribuyenteid(), comprobante.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        eventoServicio.guardarEventoCancelacion(comprobante, oContribuyente.get(), "7");
        
        //EventosDE ede = eventoServicio.procesarCancelacion(comprobante, oContribuyente.get(), "7");
        
        //eventoServicio.guardarEvento(ede, oContribuyente.get(), comprobante.getCdc());

        return new ResponseEntity("Cancelacion de Remision recibida",HttpStatus.CREATED);
    }
    
       @GetMapping(value = "/iniciarEnvio")
    public @ResponseBody
    ResponseEntity consultarLotes() throws SifenException, ParserConfigurationException, SAXException, IOException {
        
        this.eventoServicio.enviarEventos();
        
         return  new ResponseEntity("Inicio manual de envio de lotes",HttpStatus.OK);
        
    }
    
   
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
