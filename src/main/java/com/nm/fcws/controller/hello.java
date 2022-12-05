/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.repo.ComprobanteElectronicoRepo;
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
    
    @GetMapping(value = "/hello",produces ="application/json")
    public String sayHello(){
        
        return "hello lo perro";
        
    }
    
    @GetMapping(value = "/comprobante")
    public ComprobanteElectronico getComprobanteByCdc(){
    
        ComprobanteElectronico ce =  cer.findByCdc("01800025245001001000005022022120518663267863");
        
        ce.setEstado("El estado");
        
        return ce;
        
    }
    
    @GetMapping(value = "/help",produces ="application/json")
    public void help() throws ParserConfigurationException, SAXException, IOException{
        
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<env:Envelope xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\"><env:Header/><env:Body><ns2:rRetEnviDe xmlns:ns2=\"http://ekuatia.set.gov.py/sifen/xsd\"><ns2:rProtDe><ns2:Id>01800025245001001000001722022120110448625146</ns2:Id><ns2:dFecProc>2022-12-01T15:17:19-03:00</ns2:dFecProc><ns2:dDigVal>aVplVGtNTEpEblNEcHFmd0Y1ZW9FaVRGS3kyOHE0Ni9hakZ4M1ByTXN5RT0=</ns2:dDigVal><ns2:dEstRes>Aprobado</ns2:dEstRes><ns2:dProtAut>34806044</ns2:dProtAut><ns2:gResProc><ns2:dCodRes>0260</ns2:dCodRes><ns2:dMsgRes>Autorizaci&#243;n del DE satisfactoria</ns2:dMsgRes></ns2:gResProc></ns2:rProtDe></ns2:rRetEnviDe></env:Body></env:Envelope>";
        
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       DocumentBuilder db = dbf.newDocumentBuilder();
       InputSource is = new InputSource();
       is.setCharacterStream(new StringReader(xml));
       Document d = db.parse(is);
      
       d.getDocumentElement().normalize();
       NodeList nl = d.getElementsByTagName("ns2:rProtDe");
       
       log.info(nl.getLength()+"");
       
       for (int i =  0 ; i<nl.getLength(); i++){
           
           Node n = nl.item(i);
           log.info(n.getChildNodes().item(0).getNodeName());
           
           if (n.getNodeType() == Node.ELEMENT_NODE){
           
           Element e = (Element) n;
           
           log.info(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
           log.info(e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent());
           log.info(e.getElementsByTagName("ns2:dCodRes").item(0).getTextContent());
           }
           
       
       }
       
       
    }
    
}
