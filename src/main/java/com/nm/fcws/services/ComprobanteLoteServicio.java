/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.services;

import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.modeldb.ContribuyenteContacto;
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
import java.util.List;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
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

    private final int listaMaxima = 50;

    @Autowired
    private ComprobanteElectronicoRepo cer;

    @Autowired
    private ConexionSifenServicio css;

    @Autowired
    private TipoComprobanteElectronicoRepo tcer;

    @Autowired
    private LoteRepo lr;
    
    @Autowired
    private EmailServicio emailServicio;

    private List<DocumentoElectronico> generarLote(Contribuyente contribuyente, TipoComprobanteElectronico tce) throws SifenException {

        // List<ComprobanteElectronico> lce = cer.findByLoteAndEnviadoLoteAndContribuyente(true, false, contribuyente);
        List<ComprobanteElectronico> lce = cer.findByEnvioPorLoteAndContribuyenteAndTipoComprobanteElectronicoAndEnviadoLote(true, contribuyente, tce, false);
        List<DocumentoElectronico> lde = new ArrayList<DocumentoElectronico>();

        for (ComprobanteElectronico ce : lce) {

            DocumentoElectronico de = new DocumentoElectronico(ce.getXml());
            lde.add(de);
        }

        return lde;
    }

    @Async
    public void enviarLotes(Contribuyente contribuyente) throws SifenException, ParserConfigurationException, SAXException, IOException {

        List<TipoComprobanteElectronico> ltce = (List<TipoComprobanteElectronico>) tcer.findAll();

        for (TipoComprobanteElectronico x : ltce) {

            List<DocumentoElectronico> lde = this.generarLote(contribuyente, x);

            if (lde.size() > 0) {
                this.enviarLote(lde, contribuyente);
            }

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

        this.procesarRespuestaEnvioLote(respuesta, lde, contribuyente);

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

    private void procesarRespuestaEnvioLote(String respuesta, List<DocumentoElectronico> lde, Contribuyente contribuyente) throws ParserConfigurationException, SAXException, IOException {

        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(respuesta));

        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:rResEnviLoteDe");

        Lote lote = null;

        for (int i = 0; i < nl.getLength(); i++) {

            Node n = nl.item(i);

            if (n.getNodeType() == Node.ELEMENT_NODE) {

                Element e = (Element) n;

                if (e.getElementsByTagName("ns2:dCodRes").item(0).getTextContent().compareTo("0300") == 0) {

                    //loteNro = e.getElementsByTagName("ns2:dProtConsLote").item(0).getTextContent();
                    lote = new Lote();
                    lote.setNro(e.getElementsByTagName("ns2:dProtConsLote").item(0).getTextContent());
                    lote.setContribuyente(contribuyente);
                    lote.setRespuestaEnvio(respuesta);

                    lote = lr.save(lote);

                }

            }

        }

        for (DocumentoElectronico de : lde) {

            ComprobanteElectronico ce = cer.findByCdc(de.getId());
            //ce.setEnviadoLote(true);
            //ce.setFechaEnviadoLote(new Date());
            ce.setEnviado(true);
            //ce.setRespuesta(respuesta);
            ce.setEnviadoLote(true);
            if (!Objects.isNull(lote)) {
                ce.setLote(lote);
            } else {
                log.info("Error al enviar el lote.");
            }

            cer.save(ce);

        }
    }
    
    public String consultarLote (Lote lote) throws SifenException, ParserConfigurationException, SAXException, IOException{
    
        return this.consultarLote(lote.getNro(), lote.getContribuyente());
        
    }
    
    @Async
    public void ConsultarLotes() throws SifenException, ParserConfigurationException, SAXException, IOException{
    
        List <Lote> lotes = lr.findByEstadoNotContainingOrEstadoIsNull("Concluido");
        
        log.info("Cantidad de lotes a consultar: "+lotes.size());
        
        for (Lote x : lotes ){
        
            this.consultarLote(x.getNro(), x.getContribuyente());
            
        }
        
    }

    @Async
    public String consultarLote(String nro, Contribuyente contribuyente) throws SifenException, ParserConfigurationException, SAXException, IOException {

        SifenConfig config = css.getSifenConfig(contribuyente);

        RespuestaConsultaLoteDE rcl = Sifen.consultaLoteDE(nro, config);
        // System.out.println(rcl.getRespuestaBruta());

        Lote lote = lr.findByNro(nro);
        lote.setRespuestaconsulta(rcl.getRespuestaBruta());

        procesarConsultaLote(lote);

        return rcl.getRespuestaBruta();
    }

    private void procesarConsultaLote(Lote lote) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(lote.getRespuestaconsulta()));
        Document d = db.parse(is);

        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:rResEnviConsLoteDe");

        Node n = nl.item(0);
       // log.info(((Element) n).getElementsByTagName("ns2:dCodResLot").item(0).getTextContent());

        String codResLot = ((Element) n).getElementsByTagName("ns2:dCodResLot").item(0).getTextContent();
        
        StringBuffer mensajeEmail = new StringBuffer();

        if (codResLot.compareTo("0362") == 0) {

            lote.setEstado("Concluido");

            nl = d.getElementsByTagName("ns2:gResProcLote");

            for (int i = 0; i < nl.getLength(); i++) {

                n = nl.item(i);
                
                ComprobanteElectronico ce = cer.findByCdc(((Element) n).getElementsByTagName("ns2:id").item(0).getTextContent());
                ce.setRespuesta( ((Element) n).getElementsByTagName("ns2:dCodRes").item(0).getTextContent() + " - " +
                        ((Element) n).getElementsByTagName("ns2:dMsgRes").item(0).getTextContent());
                ce.setEstado(((Element) n).getElementsByTagName("ns2:dEstRes").item(0).getTextContent() );
                cer.save(ce);
                
                if (ce.getEstado().compareTo("Rechazado") == 0){
                
                    mensajeEmail.append("El comprobante "+ce.getTipoComprobanteElectronico().getTipoComprobanteElectronico()+" fue Rechazado. "+
                            "\nNRO "+ce.getNumero()+
                            "\nCDC "+ce.getCdc()+
                            "\nMensaje "+ce.getRespuesta()+"\n\n");
                    
                }
                
                

               // log.info(((Element) n).getElementsByTagName("ns2:id").item(0).getTextContent());
               // log.info(((Element) n).getElementsByTagName("ns2:dEstRes").item(0).getTextContent());
               // log.info(((Element) n).getElementsByTagName("ns2:dCodRes").item(0).getTextContent());
               // log.info(((Element) n).getElementsByTagName("ns2:dMsgRes").item(0).getTextContent());

            }
            
            int emailSize =0;
            
            if (mensajeEmail.length() > 0){
                
                List<ContribuyenteContacto> lcc = lote.getContribuyente().getContactos();
                
                if (!lcc.isEmpty()||!Objects.isNull(lcc)){
                
                    emailSize = lote.getContribuyente().getContactos().size();
                    
                }
        
                if (emailSize > 0){
                
                    String[] destinos = new String[emailSize]; 
                    int i = 0;
  
                    for (ContribuyenteContacto x : lote.getContribuyente().getContactos()){
                    
                        destinos[i]= x.getMail();
                        i++;
                    
                    }
                    
                    emailServicio.send("fe@vidrioluz.com.py", destinos , "Alerta", mensajeEmail.toString());
                    
                }
                
                
            }
            
           
        }

        if (codResLot.compareTo("361") == 0) {

            lote.setEstado("Procesamiento");

        }

        if (codResLot.compareTo("0363") == 0) {

            lote.setEstado("Lotes con tipos distintos de DE");

        }
        
         lr.save(lote);

    }

}
