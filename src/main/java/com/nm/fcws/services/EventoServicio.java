/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.services;

import com.nm.fcws.model.Comprobante;
import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.modeldb.Evento;
import com.nm.fcws.repo.ComprobanteElectronicoRepo;
import com.nm.fcws.repo.EventoRepo;
import com.nm.fcws.repo.TipoComprobanteElectronicoRepo;
import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.beans.EventosDE;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionEvento;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.event.TgGroupTiEvt;
import com.roshka.sifen.core.fields.request.event.TrGeVeCan;
import com.roshka.sifen.core.fields.request.event.TrGesEve;
import java.io.IOException;
import java.io.StringReader;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
public class EventoServicio {
    
    private static Logger log = LoggerFactory.getLogger(EventoServicio.class);
    
    @Autowired
    private ConexionSifenServicio css;
    
    @Autowired
    private ComprobanteElectronicoRepo cer;
    
    @Autowired
    private EventoRepo er;
    
    @Autowired
    private TipoComprobanteElectronicoRepo tcer;

    public EventosDE procesarCancelacion(Comprobante comprobante, Contribuyente contribuyente, String id) throws SifenException{
        
        EventosDE ede = new EventosDE();
        
        TrGesEve rGesEve = new TrGesEve();

        rGesEve.setId(id);
        
        
        rGesEve.setdFecFirma(comprobante.getFecha().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        
        TgGroupTiEvt gGroupTiEvt = new TgGroupTiEvt();
        
        TrGeVeCan rGeVeCan = new TrGeVeCan();
        rGeVeCan.setId(comprobante.getCdc());
        rGeVeCan.setmOtEve("Cancelacion de CDC");
         
        gGroupTiEvt.setrGeVeCan(rGeVeCan);

        
        rGesEve.setgGroupTiEvt(gGroupTiEvt);
        //List <TrGesEve> lrGesEve =  new ArrayList<TrGesEve>();
        //lrGesEve.add(rGesEve);
        
        ede.setrGesEveList(Collections.singletonList(rGesEve));
        
        
        return ede;
    }
    
    public void guardarEvento(Comprobante comprobante, Contribuyente contribuyente, String id){
    
       Evento e = new Evento();
       e.setCdc(comprobante.getCdc());
       e.setFecha(comprobante.getFecha());
       e.setContribuyente(contribuyente);
       e.setTipoComprobanteElectronico(tcer.findById(Long.parseLong(id)).get());
       if(comprobante.getMotivoEvento() == null){
           e.setMotivo(comprobante.getMotivoEvento());
       }
       
       er.save(e);
       
    }
    
    private EventosDE procesarCancelacion(Evento e){
        
        ComprobanteElectronico ce = cer.findByCdc(e.getCdc());
    
        EventosDE ede = new EventosDE();
        
        TrGesEve rGesEve = new TrGesEve();

        rGesEve.setId(ce.getTipoComprobanteElectronico().getTipocomprobanteelectronicoid().toString());
        
        
        rGesEve.setdFecFirma(e.getFecha().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        
        TgGroupTiEvt gGroupTiEvt = new TgGroupTiEvt();
        
        TrGeVeCan rGeVeCan = new TrGeVeCan();
        rGeVeCan.setId(e.getCdc());
        rGeVeCan.setmOtEve("Cancelacion de CDC");
        if (e.getMotivo().length() > 0){
            rGeVeCan.setmOtEve(e.getMotivo());
        }
        
         
        gGroupTiEvt.setrGeVeCan(rGeVeCan);

        
        rGesEve.setgGroupTiEvt(gGroupTiEvt);
        //List <TrGesEve> lrGesEve =  new ArrayList<TrGesEve>();
        //lrGesEve.add(rGesEve);
        
        ede.setrGesEveList(Collections.singletonList(rGesEve));
        
        return ede;
        
    }
    
    
    @Async
    public void enviarEventos() throws SifenException, ParserConfigurationException, SAXException, IOException{
    
        List<Evento> levento = er.findByEnviado(false);
        
        for (Evento x : levento){
        
            this.enviarEvento(this.procesarCancelacion(x), x);
        
        }
        
    }
    
    public void enviarEvento(EventosDE ede, Evento evento) throws SifenException, ParserConfigurationException, SAXException, IOException{
        
        RespuestaRecepcionEvento rre =  Sifen.recepcionEvento(ede, css.getSifenConfig(evento.getContribuyente()));
        log.info(rre.getRespuestaBruta());
        
        String respuesta= rre.getRespuestaBruta();
        
        evento.setRespuesta(respuesta);
        
        ComprobanteElectronico ce = cer.findByCdc(evento.getCdc());
        ce.setEvento(evento);
        
        //ce.setEventoRespuesta(respuesta);
        
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(respuesta));
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:gResProcEVe");

        for (int i = 0; i < nl.getLength(); i++) {

            Node n = nl.item(i);

            if (n.getNodeType() == Node.ELEMENT_NODE) {

                Element e = (Element) n;

                // ce.setCdc(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
                evento.setEstado(e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent());
                

            }

        }
        
        evento.setEnviado(true);
        this.er.save(evento);
        this.cer.save(ce);

    }
    
    /*@Async
    public void enviarEvento(EventosDE ede, Contribuyente contribuyente, String cdc) throws SifenException, ParserConfigurationException, SAXException, IOException{
        
        RespuestaRecepcionEvento rre =  Sifen.recepcionEvento(ede, css.getSifenConfig(contribuyente));
        log.info(rre.getRespuestaBruta());
        
        String respuesta= rre.getRespuestaBruta();
        
        ComprobanteElectronico ce = cer.findByCdc(cdc);
        ce.setEventoRespuesta(respuesta);
        
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(respuesta));
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:gResProcEVe");

        for (int i = 0; i < nl.getLength(); i++) {

            Node n = nl.item(i);

            if (n.getNodeType() == Node.ELEMENT_NODE) {

                Element e = (Element) n;

                // ce.setCdc(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
                ce.setEventoEstado((e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent()));
                if (e.getElementsByTagName("ns2:dCodRes").item(0).getTextContent().compareTo("0600") == 0) {

                    ce.setEvento(true);

                }

                // log.info(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
                // log.info(e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent());
                // log.info(e.getElementsByTagName("ns2:dCodRes").item(0).getTextContent());
            }

            // log.info(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
            // log.info(e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent());
        }

        this.cer.save(ce);

    }*/
    
}
