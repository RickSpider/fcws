/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.services;

import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.modeldb.Lote;
import com.nm.fcws.modeldb.TipoComprobanteElectronico;
import com.nm.fcws.repo.ComprobanteElectronicoRepo;
import com.nm.fcws.repo.LoteRepo;
import com.nm.fcws.repo.TipoComprobanteElectronicoRepo;
import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.response.RespuestaConsultaLoteDE;
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

    private final int listaMaxima = 70;

    @Autowired
    private ComprobanteElectronicoRepo cer;

    @Autowired
    private ConexionSifenServicio css;

    @Autowired
    private TipoComprobanteElectronicoRepo tcer;
    
    @Autowired
    private LoteRepo lr;

    private List<DocumentoElectronico> generarLote(Contribuyente contribuyente, TipoComprobanteElectronico tce) throws SifenException {

        // List<ComprobanteElectronico> lce = cer.findByLoteAndEnviadoLoteAndContribuyente(true, false, contribuyente);
        List<ComprobanteElectronico> lce = cer.findByEnvioPorLoteAndContribuyenteAndTipoComprobanteElectronicoAndLoteIsNull(true, contribuyente, tce);
        List<DocumentoElectronico> lde = new ArrayList<DocumentoElectronico>();

        for (ComprobanteElectronico ce : lce) {

            DocumentoElectronico de = new DocumentoElectronico(ce.getXml());
            lde.add(de);
        }

        return lde;
    }
    
    @Async
    public void enviarLotes(Contribuyente contribuyente) throws SifenException, ParserConfigurationException, SAXException, IOException{
    
        List<TipoComprobanteElectronico> ltce = (List<TipoComprobanteElectronico>) tcer.findAll();
        
        for (TipoComprobanteElectronico x : ltce){
        
            this.enviarLote(this.generarLote(contribuyente,x), contribuyente);
            
        }
        
    }

    @Async
    public void enviarLoteContribuyentes(List<Contribuyente> lcontribuyentes) throws SifenException, ParserConfigurationException, SAXException, IOException {

        
        for (Contribuyente x : lcontribuyentes) {

            this.enviarLotes(x);

        }

    }
    
    

 
    private void enviarLote(List<DocumentoElectronico> lde, Contribuyente contribuyente)
            throws SifenException, ParserConfigurationException, SAXException, IOException {

        if (lde.size() <= this.listaMaxima) {

            this.enviarLoteCorto(lde, contribuyente);

        } else {

            this.enviarLoteLargo(lde, contribuyente);

        }

    }

    private void enviarLoteCorto(List<DocumentoElectronico> lde, Contribuyente contribuyente) throws SifenException, ParserConfigurationException, SAXException, IOException {

        SifenConfig config = css.getSifenConfig(contribuyente);

        RespuestaRecepcionLoteDE rrlde = Sifen.recepcionLoteDE(lde, config);
        String respuesta = rrlde.getRespuestaBruta();
        log.info(respuesta);

        this.procesarRespuesta(respuesta, lde, contribuyente);

    }

    private void enviarLoteLargo(List<DocumentoElectronico> lde, Contribuyente contribuyente) throws SifenException, ParserConfigurationException, SAXException, IOException {

        int c = 0;

        List<DocumentoElectronico> ldeAux = new ArrayList<DocumentoElectronico>();

        for (DocumentoElectronico x : lde) {

            c++;

            ldeAux.add(x);

            if (c == this.listaMaxima) {

                this.enviarLoteCorto(ldeAux, contribuyente);

            }

        }

        if (ldeAux.size() > 0) {

            this.enviarLoteCorto(ldeAux, contribuyente);

        }

    }

    private void procesarRespuesta(String respuesta, List<DocumentoElectronico> lde, Contribuyente contribuyente) throws ParserConfigurationException, SAXException, IOException {

        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(respuesta));
        log.info(respuesta);
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:rResEnviLoteDe");
        //String loteNro = "-1";

        Lote lote = new Lote();

        for (int i = 0; i < nl.getLength(); i++) {

            Node n = nl.item(i);

            if (n.getNodeType() == Node.ELEMENT_NODE) {

                Element e = (Element) n;

                if (e.getElementsByTagName("ns2:dCodRes").item(0).getTextContent().compareTo("0300") == 0) {

                    //loteNro = e.getElementsByTagName("ns2:dProtConsLote").item(0).getTextContent();
                    lote.setNro(e.getElementsByTagName("ns2:dProtConsLote").item(0).getTextContent());
                    lote.setContribuyente(contribuyente);
                    lote.setRespuestaEnvio(respuesta);

                    lote = lr.save(lote);

                } else {

                    return;

                }

            }

        }

        for (DocumentoElectronico de : lde) {

            ComprobanteElectronico ce = cer.findByCdc(de.getId());
            //ce.setEnviadoLote(true);
            //ce.setFechaEnviadoLote(new Date());
            ce.setEnviado(true);
            ce.setRespuesta(respuesta);

            ce.setLote(lote);

            //ce.setLoteNro(loteNro);
            cer.save(ce);

        }
    }

    public String consultarLote(Lote lote) throws SifenException{
    
        SifenConfig config = css.getSifenConfig(lote.getContribuyente());
        
        RespuestaConsultaLoteDE rcl = Sifen.consultaLoteDE(lote.getNro(), config);
       // System.out.println(rcl.getRespuestaBruta());
        
        return rcl.getRespuestaBruta();
    }
}
