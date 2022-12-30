/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.services;

import com.nm.fcws.model.Comprobante;
import com.nm.fcws.modeldb.Contribuyente;
import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.beans.EventosDE;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionEvento;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.event.TgGroupTiEvt;
import com.roshka.sifen.core.fields.request.event.TrGeVeCan;
import com.roshka.sifen.core.fields.request.event.TrGesEve;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author BlackSpider
 */
@Service
public class EventoServicio {
    
     private static Logger log = LoggerFactory.getLogger(EventoServicio.class);
    
    @Autowired
    private ConexionSifenServicio css;

    public void procesarCancelacion(Comprobante comprobante, Contribuyente contribuyente) throws SifenException{
        
        EventosDE ede = new EventosDE();
        
        TrGesEve rGesEve = new TrGesEve();
        
       /* rGesEve.setdFecFirma(comprobante.getFecha().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());*/
        
        TgGroupTiEvt gGroupTiEvt = new TgGroupTiEvt();
        
        TrGeVeCan rGeVeCan = new TrGeVeCan();
        rGeVeCan.setId(comprobante.getCdc());
        
       /* if ( !Objects.(comprobante.getMotivoEvento())){
            
            rGeVeCan.setmOtEve(comprobante.getMotivoEvento());
            
        }*/
        gGroupTiEvt.setrGeVeCan(rGeVeCan);
        
        rGesEve.setgGroupTiEvt(gGroupTiEvt);
        List <TrGesEve> lrGesEve =  new ArrayList<TrGesEve>();
        lrGesEve.add(rGesEve);
        
        ede.setrGesEveList(lrGesEve);
        
        
        enviarEvento(ede, contribuyente);
    }
    
    @Async
    private void enviarEvento(EventosDE ede, Contribuyente contribuyente) throws SifenException{
        
        RespuestaRecepcionEvento rre =  Sifen.recepcionEvento(ede, css.getSifenConfig(contribuyente));
        log.info(rre.getRespuestaBruta());

    }


    
}
