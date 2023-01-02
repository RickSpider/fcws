/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.interfaces.iCancelacion;
import com.nm.fcws.interfaces.iFactura;
import com.nm.fcws.interfaces.iNotaCD;
import com.nm.fcws.interfaces.iRemision;
import com.nm.fcws.model.Comprobante;
import com.nm.fcws.model.Kude;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.repo.RucRepo;
import com.nm.fcws.services.ComprobanteServicio;
import com.nm.fcws.services.ContribuyenteServicio;
import com.nm.fcws.services.EventoServicio;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.EventosDE;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TTiDE;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    private ComprobanteServicio comprobanteServicio;
    
    @Autowired
    private EventoServicio eventoServicio;

    @Autowired
    private ContribuyenteServicio contribuyenteServicio; 
    
    @Autowired
    private RucRepo rucRepo;

    @PostMapping(value = "/factura", produces = "application/json")
    public @ResponseBody
    ResponseEntity factura(@Validated({iFactura.class}) @RequestBody Comprobante factura) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(factura.getContribuyente().getContribuyenteid(), factura.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        DocumentoElectronico de = comprobanteServicio.procesar(factura, oContribuyente.get(), TTiDE.FACTURA_ELECTRONICA);

        return new ResponseEntity(generarKude(de, oContribuyente.get(), Kude.FACTURA_ELECTRONICA), HttpStatus.CREATED);
    }

    @PostMapping(value = "/remision", produces = "application/json")
    public @ResponseBody
    ResponseEntity remision(@Validated(iRemision.class) @RequestBody Comprobante remision) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(remision.getContribuyente().getContribuyenteid(), remision.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        DocumentoElectronico de = comprobanteServicio.procesar(remision, oContribuyente.get(), TTiDE.NOTA_DE_REMISION_ELECTRONICA);

        return new ResponseEntity(generarKude(de, oContribuyente.get(), Kude.NOTA_REMISION_ELECTRONICA), HttpStatus.CREATED);
    }

    @PostMapping(value = "/notacredito", produces = "application/json")
    public @ResponseBody
    ResponseEntity notaCredito(@Validated(iNotaCD.class) @RequestBody Comprobante notaCredito) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(notaCredito.getContribuyente().getContribuyenteid(), notaCredito.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        DocumentoElectronico de = comprobanteServicio.procesar(notaCredito, oContribuyente.get(), TTiDE.NOTA_DE_CREDITO_ELECTRONICA);

        return new ResponseEntity(generarKude(de, oContribuyente.get(), Kude.NOTA_CREDITO_ELECTRONICA), HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/cancelarfactura", produces = "application/json")
    public @ResponseBody
    ResponseEntity cancelarFactura(
            @RequestBody Comprobante comprobante) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(comprobante.getContribuyente().getContribuyenteid(), comprobante.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        
        EventosDE ede = eventoServicio.procesarCancelacion(comprobante, oContribuyente.get(), "1");
        
        eventoServicio.enviarEvento(ede, oContribuyente.get(), comprobante.getCdc());

        return new ResponseEntity("Cancelacion de factura enviada",HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/cancelarnotacredito", produces = "application/json")
    public @ResponseBody
    ResponseEntity cancelarNotaCredito(@Validated(iCancelacion.class) @RequestBody Comprobante comprobante) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(comprobante.getContribuyente().getContribuyenteid(), comprobante.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        
        EventosDE ede = eventoServicio.procesarCancelacion(comprobante, oContribuyente.get(), "5");
        
        eventoServicio.enviarEvento(ede, oContribuyente.get(), comprobante.getCdc());

        return new ResponseEntity("Cancelacion de Nota de Credito enviada",HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/cancelarremision", produces = "application/json")
    public @ResponseBody
    ResponseEntity cancelarRemision(@Validated(iCancelacion.class) @RequestBody Comprobante comprobante) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Optional<Contribuyente> oContribuyente = contribuyenteServicio.verfificarContribuyente(comprobante.getContribuyente().getContribuyenteid(), comprobante.getContribuyente().getPass());

        if (!oContribuyente.isPresent()) {

            return new ResponseEntity("Los datos para la identificacion del contribuyente no son correctos", HttpStatus.FORBIDDEN);
        }

        EventosDE ede = eventoServicio.procesarCancelacion(comprobante, oContribuyente.get(), "7");
        
        eventoServicio.enviarEvento(ede, oContribuyente.get(), comprobante.getCdc());

        return new ResponseEntity("Cancelacion de Remision enviada",HttpStatus.CREATED);
    }
    
   
    
    private Kude generarKude(DocumentoElectronico de, Contribuyente contribuyente, String tipoKude) throws SifenException, ParserConfigurationException, SAXException, IOException {

        String cdc = de.obtenerCDC();
        //log.info(cdc);
        Kude kude = new Kude(de.getEnlaceQR(), cdc, tipoKude);
        
        if (!contribuyente.isSoloLote()){
        
            comprobanteServicio.enviarDE(de, contribuyente, cdc);
        
        }else{
        
            log.info("El Contribuyente esta habilitado para solo envio de lotes.");
            
        }

        return kude;
    }

    /*private Optional<Contribuyente> verfificarContribuyente(Long id, String pass) {

        Optional<Contribuyente> oContribuyente = contribuyenteRepo.findById(id);

        if (oContribuyente.isPresent()) {

            if (oContribuyente.get().getPass().compareTo(pass) == 0) {

                return oContribuyente;

            }

        }

        return Optional.empty();

    }*/

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
