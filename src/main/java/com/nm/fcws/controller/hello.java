/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.repo.ComprobanteElectronicoRepo;
import com.nm.fcws.services.EmailServicio;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
public class hello {

    private static Logger log = LoggerFactory.getLogger(hello.class);

    @Autowired
    private ComprobanteElectronicoRepo cer;

    @GetMapping(value = "/hello", produces = "application/json")
    public String sayHello() {

        return "Web Service para Facturacion Electronica Sifen/n ";

    }

    @GetMapping(value = "/comprobante")
    public ComprobanteElectronico getComprobanteByCdc() {

        ComprobanteElectronico ce = cer.findByCdc("01800025245001001000005022022120518663267863");

        ce.setEstado("El estado");

        return ce;

    }

    @GetMapping(value = "/help", produces = "application/json")
    public void help() throws ParserConfigurationException, SAXException, IOException {

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<env:Envelope xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">\n"
                + "    <env:Header/>\n"
                + "    <env:Body>\n"
                + "        <ns2:rResEnviConsLoteDe xmlns:ns2=\"http://ekuatia.set.gov.py/sifen/xsd\">\n"
                + "            <ns2:dFecProc>2023-01-03T20: 07: 35-03: 00</ns2:dFecProc>\n"
                + "            <ns2:dCodResLot>0362</ns2:dCodResLot>\n"
                + "            <ns2:dMsgResLot>Procesamiento de lote {\n"
                + "                1163802704642288\n"
                + "                } concluido</ns2:dMsgResLot>\n"
                + "            <ns2:gResProcLote>\n"
                + "                <ns2:id>07800025245001001000000522023010312391959926</ns2:id>\n"
                + "                <ns2:dEstRes>Rechazado</ns2:dEstRes>\n"
                + "                <ns2:gResProc>\n"
                + "                    <ns2:dCodRes>1108</ns2:dCodRes>\n"
                + "                    <ns2:dMsgRes>TEST - c Fecha de inicio de vigencia del timbrado incorrecta</ns2:dMsgRes>\n"
                + "                </ns2:gResProc>\n"
                + "            </ns2:gResProcLote>\n"
                + "            <ns2:gResProcLote>\n"
                + "                <ns2:id>07800025245001001000000622023010312518092547</ns2:id>\n"
                + "                <ns2:dEstRes>Rechazado</ns2:dEstRes>\n"
                + "                <ns2:gResProc>\n"
                + "                    <ns2:dCodRes>1107</ns2:dCodRes>\n"
                + "                    <ns2:dMsgRes>TEST - Fecha de inicio de vigencia del timbrado incorrecta</ns2:dMsgRes>\n"
                + "                </ns2:gResProc>\n"
                + "            </ns2:gResProcLote>\n"
                + "        </ns2:rResEnviConsLoteDe>\n"
                + "    </env:Body>\n"
                + "</env:Envelope>";
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        Document d = db.parse(is);

        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:rResEnviConsLoteDe");

        log.info(nl.getLength() + "*****");

        Node n = nl.item(0);
        log.info(((Element) n).getElementsByTagName("ns2:dCodResLot").item(0).getTextContent());
        String codResLot = ((Element) n).getElementsByTagName("ns2:dMsgResLot").item(0).getTextContent();
       
        nl = d.getElementsByTagName("ns2:gResProcLote");
        
        for (int i= 0; i<nl.getLength() ; i++){
            
            n = nl.item(i);
            
            log.info(((Element) n).getElementsByTagName("ns2:id").item(0).getTextContent());
            log.info(((Element) n).getElementsByTagName("ns2:dEstRes").item(0).getTextContent());
            log.info(((Element) n).getElementsByTagName("ns2:dCodRes").item(0).getTextContent());
            log.info(((Element) n).getElementsByTagName("ns2:dMsgRes").item(0).getTextContent());
            
        }

       
    }
    
    @Autowired
    private EmailServicio emailServicio;
    
    @GetMapping(value = "/testMail", produces = "application/json")
    public void testmail() throws ParserConfigurationException, SAXException, IOException {
    
        String [] s = {"ricardo.rgi1989@gmail.com"};
        
        emailServicio.send("fe@vidrioluz.com.py", s , "Alerta", "prueba de alerta");
        
    }

}
