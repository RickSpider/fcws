/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.services;

import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.repo.ComprobanteElectronicoRepo;
import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionDE;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionLoteDE;
import com.roshka.sifen.core.exceptions.SifenException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author BlackSpider
 */
@Service
public class ComprobanteLoteServicio {
    
    private static Logger log = LoggerFactory.getLogger(ComprobanteLoteServicio.class);
    
    @Autowired
    private ComprobanteElectronicoRepo cer;
    
    @Autowired
    private ConexionSifenServicio css;
    
    public List<DocumentoElectronico> generarLote(Contribuyente contribuyente) throws SifenException{
    
        List<ComprobanteElectronico> lce = cer.findByLoteAndEnviadoLoteAndContribuyente(true, false, contribuyente);
        List<DocumentoElectronico> lde = new ArrayList<DocumentoElectronico>();
        
        for (ComprobanteElectronico ce : lce){
        
            DocumentoElectronico de = new DocumentoElectronico(ce.getXml());
            lde.add(de);
        }
        
        return lde;
    }
    
    @Async
    public void enviarLote(List<DocumentoElectronico> lde, Contribuyente contribuyente) throws SifenException, ParserConfigurationException, SAXException, IOException{
    
        SifenConfig config = css.getSifenConfig(contribuyente);
        
        RespuestaRecepcionLoteDE rrlde = Sifen.recepcionLoteDE(lde, config);
        String respuesta = rrlde.getRespuestaBruta();
        log.info(respuesta);
        
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(respuesta));
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:rResEnviLoteDe");
        String loteNro = "-1";

        for (int i = 0; i < nl.getLength(); i++) {

            Node n = nl.item(i);

            if (n.getNodeType() == Node.ELEMENT_NODE) {

                Element e = (Element) n;

               
                if (e.getElementsByTagName("ns2:dCodRes").item(0).getTextContent().compareTo("0300") == 0) {

                   loteNro = e.getElementsByTagName("ns2:dProtConsLote").item(0).getTextContent();

                }

            }

           
        }
        
        for (DocumentoElectronico de : lde){
        
            ComprobanteElectronico ce = cer.findByCdc(de.getId());
            ce.setEnviadoLote(true);
            ce.setFechaEnviadoLote(new Date());
            ce.setEnviado(true);
            ce.setRespuesta(respuesta);
            ce.setLoteNro(loteNro);
            
            cer.save(ce);
            
        }
    }
    
}
