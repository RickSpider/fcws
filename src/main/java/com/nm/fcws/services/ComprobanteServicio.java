/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.services;

import com.nm.fcws.model.Comprobante;
import com.nm.fcws.model.ComprobanteDetalle;
import com.nm.fcws.model.TipoPago;
import com.nm.fcws.modeldb.ActividadEconomica;
import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.repo.ComprobanteElectronicoRepo;
import com.nm.fcws.repo.ContribuyenteRepo;
import com.nm.fcws.repo.RucRepo;
import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionDE;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.de.TdDatGralOpe;
import com.roshka.sifen.core.fields.request.de.TgActEco;
import com.roshka.sifen.core.fields.request.de.TgCamCond;
import com.roshka.sifen.core.fields.request.de.TgCamFE;
import com.roshka.sifen.core.fields.request.de.TgCamIVA;
import com.roshka.sifen.core.fields.request.de.TgCamItem;
import com.roshka.sifen.core.fields.request.de.TgDatRec;
import com.roshka.sifen.core.fields.request.de.TgDtipDE;
import com.roshka.sifen.core.fields.request.de.TgEmis;
import com.roshka.sifen.core.fields.request.de.TgOpeCom;
import com.roshka.sifen.core.fields.request.de.TgOpeDE;
import com.roshka.sifen.core.fields.request.de.TgPaConEIni;
import com.roshka.sifen.core.fields.request.de.TgTimb;
import com.roshka.sifen.core.fields.request.de.TgTotSub;
import com.roshka.sifen.core.fields.request.de.TgValorItem;
import com.roshka.sifen.core.fields.request.de.TgValorRestaItem;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.core.types.PaisType;
import com.roshka.sifen.core.types.TDepartamento;
import com.roshka.sifen.core.types.TTImp;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.core.types.TTipEmi;
import com.roshka.sifen.core.types.TTipTra;
import com.roshka.sifen.core.types.TcUniMed;
import com.roshka.sifen.core.types.TiAfecIVA;
import com.roshka.sifen.core.types.TiCondOpe;
import com.roshka.sifen.core.types.TiIndPres;
import com.roshka.sifen.core.types.TiNatRec;
import com.roshka.sifen.core.types.TiTiOpe;
import com.roshka.sifen.core.types.TiTiPago;
import com.roshka.sifen.core.types.TiTipCont;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
public class ComprobanteServicio {

    @Autowired
    private ContribuyenteRepo contribuyenteRepo;

    @Autowired
    private ComprobanteElectronicoRepo comprobanteElectronicoRepo;

    @Autowired
    private RucRepo rucRepo;

    @Async
    public void procesar(Comprobante comprobante) throws SifenException, ParserConfigurationException, SAXException, IOException {

        Contribuyente contribuyente = contribuyenteRepo.findById(comprobante.getContribuyente().getContribuyenteid()).get();

        DocumentoElectronico de = new DocumentoElectronico();

        de.setdFecFirma(comprobante.getFecha().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        de.setdSisFact(new Long(1).shortValue());

        //gOpeDE
        TgOpeDE gOpeDe = new TgOpeDE();
        gOpeDe.setiTipEmi(TTipEmi.NORMAL);
        de.setgOpeDE(gOpeDe);

        //timbrado
        
        de.setgTimb(this.procesarTimbrado(comprobante));

        //datos generales de operacion
        TdDatGralOpe gDatGralOpe = new TdDatGralOpe();
        gDatGralOpe.setdFeEmiDE(de.getdFecFirma());

        TgOpeCom gOpeCom = new TgOpeCom();
        gOpeCom.setiTipTra(TTipTra.getByVal(contribuyente.getTipoTransaccion().getCodigoSifen().shortValue())); 
        gOpeCom.setiTImp(TTImp.getByVal(contribuyente.getTipoImpuesto().getCodigoSifen().shortValue())); 

        if (comprobante.getOperacionMoneda() == null) {

            gOpeCom.setcMoneOpe(CMondT.PYG); //discutir

        } else {

            gOpeCom.setcMoneOpe(CMondT.getByName(comprobante.getOperacionMoneda())); //discutir

        }

        //estandarizar a guaranies
        gDatGralOpe.setgOpeCom(gOpeCom);

        //Inicio Emisor de Documento Electronico
        gDatGralOpe.setgEmis(procesarEmisor(comprobante, contribuyente));

        //procesar Receptor 
        gDatGralOpe.setgDatRec(procesarReceptor(comprobante));

        de.setgDatGralOpe(gDatGralOpe);

        //Detalles
        //delles de pago
        TgDtipDE gDtipDE = new TgDtipDE();

        TgCamFE gCamFE = new TgCamFE();
        gCamFE.setiIndPres(TiIndPres.OPERACION_ELECTRONICA); //default operacion electronica
        gDtipDE.setgCamFE(gCamFE);

        gDtipDE.setgCamCond(this.procesarMetodoPago(comprobante));

        gDtipDE.setgCamItemList(this.procesarDetalle(comprobante));

        de.setgDtipDE(gDtipDE);

        TgTotSub gTotSub = new TgTotSub();
        de.setgTotSub(gTotSub);

        ComprobanteElectronico ce = new ComprobanteElectronico();
        
        SifenConfig config =getSifenConfig(contribuyente);

        ce.setContribuyente(contribuyente);
        ce.setNumero(comprobante.getEstablecimiento()+"-"+comprobante.getPuntoExpedicion()+"-"+comprobante.getDocumentoNum());
        ce.setXml(de.generarXml(config));
        ce.setTotal(de.getgTotSub().getdTotalGs().doubleValue());

        RespuestaRecepcionDE rrde = Sifen.recepcionDE(de, config);
        String respuesta = rrde.getRespuestaBruta();
        ce.setRespuesta(respuesta);

        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(respuesta));
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:rProtDe");

        for (int i = 0; i < nl.getLength(); i++) {

            Node n = nl.item(i);
            //log.info(n.getChildNodes().item(0).getNodeName());
            Element e = (Element) n;
            
            ce.setCdc(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
            ce.setEstado((e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent()));

           // log.info(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
           // log.info(e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent());

        }

        this.comprobanteElectronicoRepo.save(ce);

    }
    
    private TgCamCond procesarMetodoPago(Comprobante comprobante){
    
        
        TgCamCond gCamCond = new TgCamCond();

        //System.out.println("Condiciones de operacion");
        //System.out.println("operacion contado " + TiCondOpe.CONTADO.getVal());
        //System.out.println("operacion credito " + TiCondOpe.CREDITO.getVal());
        gCamCond.setiCondOpe(TiCondOpe.getByVal(comprobante.getCondicionOperacion().shortValue()));

        List<TgPaConEIni> gPaConEIniList = new ArrayList<TgPaConEIni>();

        for (TipoPago fp : comprobante.getTiposPagos()) {

            TgPaConEIni gPaConEIni = new TgPaConEIni();

            if (fp.getTipoPagoCodigo() == null) {

                gPaConEIni.setiTiPago(TiTiPago.EFECTIVO);

            } else {

                gPaConEIni.setiTiPago(TiTiPago.getByVal(fp.getTipoPagoCodigo().shortValue()));

            }

            gPaConEIni.setdMonTiPag(new BigDecimal(fp.getMonto())); // si viene solo el monto es efectivo por defecto
            //en caso que se multiple se tiene que definier efectivo tarjeta etc

            if (fp.getModeda() == null) {

                gPaConEIni.setcMoneTiPag(CMondT.PYG); // si no pasa nada es guaranies

            } else {
                gPaConEIni.setcMoneTiPag(CMondT.getByName(fp.getModeda())); // si no pasa nada es guaranies
            }

            gPaConEIniList.add(gPaConEIni);

        }

        gCamCond.setgPaConEIniList(gPaConEIniList);
        
        return gCamCond;
    }
    
    private TgTimb procesarTimbrado(Comprobante comprobante){
    
        TgTimb gTimb = new TgTimb();

        gTimb.setiTiDE(TTiDE.FACTURA_ELECTRONICA);
        gTimb.setdNumTim(Integer.parseInt(comprobante.getTimbrado()));
        gTimb.setdEst(comprobante.getEstablecimiento());
        gTimb.setdPunExp(comprobante.getPuntoExpedicion());
        gTimb.setdNumDoc(comprobante.getDocumentoNum());
        gTimb.setdFeIniT(comprobante.getTimbradoFecIni().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    
        return gTimb;
    }

    private TgEmis procesarEmisor(Comprobante comprobante, Contribuyente contribuyente) {

        TgEmis gEmis = new TgEmis();
        gEmis.setdRucEm(contribuyente.getRuc());
        gEmis.setdDVEmi(contribuyente.getDv());

        gEmis.setiTipCont(TiTipCont.getByVal(contribuyente.getTipoContribuyente().getCodigoSifen().shortValue()));

        if (contribuyente.getAmbiente().compareTo("DEV") == 0) {

            gEmis.setdNomEmi("DE generado en ambiente de prueba - sin valor comercial ni fiscal");

        }

        if (contribuyente.getAmbiente().compareTo("PROD") == 0) {

            gEmis.setdNomEmi(contribuyente.getNombre());

        }

        if (contribuyente.getNombreFantacia() != null) {
            gEmis.setdNomFanEmi(contribuyente.getNombreFantacia());
        }/*else{
            gEmis.setdNomFanEmi(contribuyente.getNombre());
        }*/

        gEmis.setdDirEmi(contribuyente.getDireccion());
        gEmis.setdNumCas(contribuyente.getNumCasa());

        gEmis.setcDepEmi(TDepartamento.getByVal(contribuyente.getDistrito().getDepartamento().getDepartamentoid().shortValue()));

        gEmis.setcDisEmi(contribuyente.getDistrito().getCodigoSifen().shortValue());
        gEmis.setdDesDisEmi(contribuyente.getDistrito().getDistrito());

        gEmis.setcCiuEmi(contribuyente.getDistrito().getCodigoSifen().shortValue());
        gEmis.setdDesCiuEmi(contribuyente.getDistrito().getDistrito());

        gEmis.setdTelEmi(contribuyente.getTelefono());
        gEmis.setdEmailE(contribuyente.getEmail());
        gEmis.setdDenSuc(comprobante.getSucursal());

        List<TgActEco> gActEcoList = new ArrayList<TgActEco>(); //discutir

        for (ActividadEconomica acti : contribuyente.getActividades()) {

            TgActEco gActEco = new TgActEco();
            gActEco.setcActEco(acti.getCodigo());
            gActEco.setdDesActEco(acti.getDescripcion());
            //esta info pasa el contribuyente si es mas de uno
            gActEcoList.add(gActEco); //discutir

        }

        gEmis.setgActEcoList(gActEcoList);

        return gEmis;

    }

    private TgDatRec procesarReceptor(Comprobante comprobante) {

        int naturaleza = 1;

        if (comprobante.getReceptorDV() == null || comprobante.getReceptorDV().length() == 0) {

            naturaleza = 2;

        }

        TgDatRec gDatRec = new TgDatRec();
        gDatRec.setiNatRec(TiNatRec.getByVal(new Short(String.valueOf(naturaleza)))); //discutir

        if (naturaleza == 1) {

            gDatRec.setiTiOpe(TiTiOpe.B2B);

        }
        if (naturaleza == 2) {

            gDatRec.setiTiOpe(TiTiOpe.B2C);

        }

        if (gDatRec.getiTiOpe().getVal() != TiTiOpe.B2F.getVal()) {

            gDatRec.setcPaisRec(PaisType.PRY); // en caso de b2f cambiaria el pais

        } else {

            // aca agregar el codigo para pais distinto
        }

        if (naturaleza == 1) {

            if (Integer.parseInt(comprobante.getReceptorRuc()) >= 80000000) {

                gDatRec.setiTiContRec(TiTipCont.PERSONA_JURIDICA);

            } else {

                gDatRec.setiTiContRec(TiTipCont.PERSONA_FISICA);

            }

            /*if (comprobante.getClienteTipoPersona().compareTo("FISICA") == 0){ // automatizar deacuerdo al ruc
        
                gDatRec.setiTiContRec(TiTipCont.PERSONA_FISICA);
            }

            if (comprobante.getClienteTipoPersona().compareTo("JURIDICA") == 0){

                gDatRec.setiTiContRec(TiTipCont.PERSONA_JURIDICA);
            }*/
            gDatRec.setdRucRec(comprobante.getReceptorRuc());
            gDatRec.setdNomRec(rucRepo.findByRuc(comprobante.getReceptorRuc()).getRazonSocial());
            gDatRec.setdDVRec(new Short(comprobante.getReceptorDV()));

        }

        if (naturaleza == 2 && gDatRec.getiTiOpe().getVal() != TiTiOpe.B2F.getVal()) {

        }

        return gDatRec;

        // Fin receptor
    }
    
    private List<TgCamItem> procesarDetalle(Comprobante comprobante){
    
         List<TgCamItem> gCamItemList = new ArrayList<TgCamItem>();
        for (ComprobanteDetalle x : comprobante.getDetalles()) {

            TgCamItem gCamItem = new TgCamItem();
            gCamItem.setdCodInt(x.getItemCodigo());
            gCamItem.setdDesProSer(x.getItemDescripcion());

            if (x.getItemUndMedida() == null) {

                gCamItem.setcUniMed(TcUniMed.UNI);

            } else {

                gCamItem.setcUniMed(TcUniMed.getByVal(x.getItemUndMedida().shortValue()));

            }

            //discutir va a pasar el cliente proveer la tabla del sifen
            gCamItem.setdCantProSer(new BigDecimal(x.getCantidad()));
            TgValorItem gValorItem = new TgValorItem();
            gValorItem.setdPUniProSer(new BigDecimal(x.getPrecioUnitario()));
            TgValorRestaItem gValorRestaItem = new TgValorRestaItem();
            gValorItem.setgValorRestaItem(gValorRestaItem);
            gCamItem.setgValorItem(gValorItem);
            TgCamIVA gCamIVA = new TgCamIVA();
            gCamIVA.setiAfecIVA(TiAfecIVA.getByVal(x.getAfectacionTributaria().shortValue()));
            gCamIVA.setdPropIVA(new BigDecimal(x.getProporcionIVA()));
            gCamIVA.setdTasaIVA(new BigDecimal(x.getTasaIVA()));
            gCamItem.setgCamIVA(gCamIVA);
            gCamItemList.add(gCamItem);

        }
    
        return gCamItemList;
    }

    private SifenConfig getSifenConfig(Contribuyente contribuyente) {

        SifenConfig config = new SifenConfig(
                SifenConfig.TipoAmbiente.DEV,
                "0001",
                "ABCD0000000000000000000000000000",
                SifenConfig.TipoCertificadoCliente.PFX,
                contribuyente.getPathkey(),
                contribuyente.getPassKey()
                //"127xqnCWu2KYHSHn"
        );

        return config;
    }

}
