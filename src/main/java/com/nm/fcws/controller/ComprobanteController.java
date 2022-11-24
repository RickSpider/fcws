/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.model.Comprobante;
import com.nm.fcws.model.ComprobanteDetalle;
import com.nm.fcws.model.Contribuyente;
import com.nm.fcws.repo.ContribuyenteRepo;
import com.roshka.sifen.core.beans.DocumentoElectronico;
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
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BlackSpider
 */


@RestController
public class ComprobanteController {
    
    @Autowired
    private ContribuyenteRepo contribuyenteRepo;
    
    @PostMapping(value = "/factura",produces ="application/json")
    public void comprobante(Comprobante factura){
        
        
    
    }
    
    private void procesar(Comprobante comprobante){
    
        
       
        Contribuyente contribuyente = contribuyenteRepo.findById(comprobante.getContribuyente().getContribuyenteid()).get();
    
        DocumentoElectronico de = new DocumentoElectronico();
        
        //System.out.println(de.getdDVId());
        // fecha de la factura
       // Date date = new Date();
        de.setdFecFirma(comprobante.getFecha().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        
       // Integer dSisFact = 1;
        de.setdSisFact(new Integer(1).shortValue());
        
        //gOpeDE
        
        TgOpeDE gOpeDe = new TgOpeDE();
        gOpeDe.setiTipEmi(TTipEmi.NORMAL);
        de.setgOpeDE(gOpeDe);        
        
        //timbrado
        TgTimb gTimb = new TgTimb();

        gTimb.setiTiDE(TTiDE.FACTURA_ELECTRONICA);
        gTimb.setdNumTim(Integer.parseInt(comprobante.getTimbrado()));
        gTimb.setdEst(comprobante.getEstablecimiento());
        gTimb.setdPunExp(comprobante.getPuntoExpedicion());
        gTimb.setdNumDoc(comprobante.getDocumentoNum());
        gTimb.setdFeIniT(comprobante.getTimbradoFecIni().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        de.setgTimb(gTimb);
        
        //datos generales de operacion
        
        TdDatGralOpe gDatGralOpe = new TdDatGralOpe();
        gDatGralOpe.setdFeEmiDE(de.getdFecFirma());
        
        TgOpeCom gOpeCom = new TgOpeCom();
        gOpeCom.setiTipTra(TTipTra.MIXTO); //discutir
        gOpeCom.setiTImp(TTImp.IVA); //discutir
        gOpeCom.setcMoneOpe(CMondT.PYG); //discutir
        gDatGralOpe.setgOpeCom(gOpeCom); 
        
       
        
        TgEmis gEmis = new TgEmis();
        gEmis.setdRucEm(contribuyente.getRuc());
        gEmis.setdDVEmi(comprobante.getContribuyente().getDv());
        
        if (contribuyente.getTipoContribuyente().compareTo("FISICA")==0){
        
            gEmis.setiTipCont(TiTipCont.PERSONA_FISICA);
            
        }
        
         if (contribuyente.getTipoContribuyente().compareTo("JURIDICA")==0){
        
            gEmis.setiTipCont(TiTipCont.PERSONA_JURIDICA);
            
        }
        
        
        if (contribuyente.getAmbiente().compareTo("DEV") ==0 ){
        
             gEmis.setdNomEmi("DE generado en ambiente de prueba - sin valor comercial ni fiscal");
            
        }
        
        if (contribuyente.getAmbiente().compareTo("PROD") == 0){
        
             gEmis.setdNomEmi(contribuyente.getNombre());
            
        }
        
        
       
        gEmis.setdNomFanEmi(contribuyente.getNombreFantacia());
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
        TgActEco gActEco = new TgActEco(); 
        gActEco.setcActEco("46634"); //discutir
        gActEco.setdDesActEco("Comercio al por mayor de vidrio"); //discutir
        gActEcoList.add(gActEco); //discutir
        gEmis.setgActEcoList(gActEcoList); 
        
        gDatGralOpe.setgEmis(gEmis);
        
        TgDatRec gDatRec = new TgDatRec();
        gDatRec.setiNatRec(TiNatRec.CONTRIBUYENTE); //discutir
        gDatRec.setiTiOpe(TiTiOpe.B2B); // discutir
        gDatRec.setcPaisRec(PaisType.PRY);
        
        if (comprobante.getClienteTipoPersona().compareTo("FISICA") == 0){
        
            gDatRec.setiTiContRec(TiTipCont.PERSONA_FISICA);
        }
        
        if (comprobante.getClienteTipoPersona().compareTo("JURIDICA") == 0){
        
            gDatRec.setiTiContRec(TiTipCont.PERSONA_JURIDICA);
        }
        
        gDatRec.setdRucRec(comprobante.getClienteRuc());
        gDatRec.setdDVRec(new Short(comprobante.getClienteDV()));
        gDatGralOpe.setgDatRec(gDatRec);
        
       
                
        de.setgDatGralOpe(gDatGralOpe);
        
        //Detalles
        
        //delles de pago
        TgDtipDE gDtipDE = new TgDtipDE();
        
        TgCamFE gCamFE = new TgCamFE();
        gCamFE.setiIndPres(TiIndPres.OPERACION_ELECTRONICA); //discutir
        gDtipDE.setgCamFE(gCamFE);
        
        TgCamCond gCamCond = new TgCamCond();
        

        gCamCond.setiCondOpe(TiCondOpe.getByVal(new Short(String.valueOf(comprobante.getCondicionOperacion()))));
       
        List<TgPaConEIni> gPaConEIniList = new ArrayList<TgPaConEIni>();
        TgPaConEIni gPaConEIni = new TgPaConEIni();
        gPaConEIni.setiTiPago(TiTiPago.EFECTIVO);
        gPaConEIni.setdMonTiPag(new BigDecimal(120000.00));
        gPaConEIni.setcMoneTiPag(CMondT.PYG);
        gPaConEIniList.add(gPaConEIni);
        gCamCond.setgPaConEIniList(gPaConEIniList);
        gDtipDE.setgCamCond(gCamCond);
        
        
        List<TgCamItem> gCamItemList = new ArrayList<TgCamItem>();   
        for (ComprobanteDetalle x :comprobante.getDetalles()){
        
            TgCamItem gCamItem = new TgCamItem();
            gCamItem.setdCodInt(x.getItemCodigo());
            gCamItem.setdDesProSer(x.getItemDescripcion());
            gCamItem.setcUniMed(TcUniMed.UNI); //discutir
            gCamItem.setdCantProSer(new BigDecimal(x.getCantidad()));
            TgValorItem gValorItem = new TgValorItem();
            gValorItem.setdPUniProSer(new BigDecimal(x.getPrecioUnitario()));
            TgValorRestaItem gValorRestaItem = new TgValorRestaItem();
            gValorItem.setgValorRestaItem(gValorRestaItem);
            gCamItem.setgValorItem(gValorItem);
            TgCamIVA gCamIVA = new TgCamIVA();
            gCamIVA.setiAfecIVA(TiAfecIVA.getByVal(new Short(String.valueOf(x.getAfectacionTributaria()))));
            gCamIVA.setdPropIVA(new BigDecimal(x.getProporcionIVA()));
            gCamIVA.setdTasaIVA(new BigDecimal(x.getTasaIVA()));
            gCamItem.setgCamIVA(gCamIVA);
            gCamItemList.add(gCamItem);
            
        }
        
    
        gDtipDE.setgCamItemList(gCamItemList);
       
      
        
        de.setgDtipDE(gDtipDE);
        
        TgTotSub gTotSub = new TgTotSub();
        de.setgTotSub(gTotSub);
    
    
    }
    
    
    
}
