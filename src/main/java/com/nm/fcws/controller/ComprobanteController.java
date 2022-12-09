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
import com.roshka.sifen.core.types.TTiDE;
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

    @PostMapping(value = "/factura", produces = "application/json")
    public @ResponseBody
    ResponseEntity comprobante(@RequestBody Comprobante factura) throws SifenException, ParserConfigurationException, SAXException, IOException {

        //log.info("Esto llego"+factura.getTimbradoFecIni().getTime());
        //log.info("Esto llego"+factura.getTimbradoFecIni());
        String chequear = chequearCampos(factura);

        if (chequear != null) {

            return new ResponseEntity(chequear, HttpStatus.BAD_REQUEST);

        }

        Contribuyente contribuyente = contribuyenteRepo.findById(factura.getContribuyente().getContribuyenteid()).get();

        DocumentoElectronico de = comprobanteServicio.procesar(factura, contribuyente, TTiDE.FACTURA_ELECTRONICA);
        String cdc = de.obtenerCDC();
        log.info(cdc);
        Kude kude = new Kude(de.getEnlaceQR(), cdc);

        comprobanteServicio.enviarDE(de, contribuyente, cdc);

        return new ResponseEntity(kude, HttpStatus.CREATED);
    }

    private String chequearCampos(Comprobante comprobante) {

        if (comprobante.getTimbrado() == null || comprobante.getTimbrado().getTimbrado().length() != 8) {

            return "Error en datos de timbrado";

        }

        if (comprobante.getTimbrado().getEstablecimiento() == null || comprobante.getTimbrado().getEstablecimiento().length() != 3) {

            return "Error en datos de Establecimiento";

        }

        if (comprobante.getTimbrado().getPuntoExpedicion() == null || comprobante.getTimbrado().getPuntoExpedicion().length() != 3) {

            return "Error en datos de Punto de Expedicion";

        }

        if (comprobante.getTimbrado().getPuntoExpedicion() == null || comprobante.getTimbrado().getPuntoExpedicion().length() != 3) {

            return "Error en datos de Punto de Expedicion";

        }

        if (comprobante.getTimbrado().getDocumentoNro() == null || comprobante.getTimbrado().getDocumentoNro().length() != 7) {

            return "Error en datos en el Numero de documento";

        }

        if (comprobante.getTimbrado().getFecIni() == null) {

            return "Error en datos de Fecha de Timbrado";

        }

        if (comprobante.getFecha() == null) {

            return "Error en datos de Fecha del comprobante";

        }

        if (comprobante.getSucursal() == null || comprobante.getSucursal().length() == 0) {

            return "Error en datos de Sucursal";

        }

        if (comprobante.getDetalles().size() <= 0) {

            return "Error no hay detalles de factora";

        }

        if (comprobante.getReceptor().getDocNro() == null) {

            return "Error no Ruc o Numero de Documento del receptor";

        }

        if (comprobante.getReceptor().getDv() == null) {

            if (comprobante.getReceptor().getTipoDocumento() == null) {

                return "Error no hay Tipo de Documento";

            }

            if (comprobante.getReceptor().getDireccion() == null || comprobante.getReceptor().getDireccion().length() <= 0) {

                return "Error no hay datos de direccion";

            }
            
            if (comprobante.getReceptor().getCasaNro() == null) {

                return "Error no existe Numero de Residencia";

            }
            
            if (comprobante.getReceptor().getDepartamento() == null) {

                return "Error no hay datos de Departamento";

            }
            
            if (comprobante.getReceptor().getDistrito() == null) {

                return "Error no hay datos de Distrito";

            }
            
            if (comprobante.getReceptor().getCiudad() == null) {

                return "Error no hay datos de Ciudad";

            }

        }

        return null;
    }

}
