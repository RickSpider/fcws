/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.services;

import com.nm.fcws.model.Comprobante;
import com.nm.fcws.model.ComprobanteDetalle;
import com.nm.fcws.model.CondicionOperacion;
import com.nm.fcws.model.Cuota;
import com.nm.fcws.model.DocAsociado;
import com.nm.fcws.model.MercaderiaMov;
import com.nm.fcws.model.NotaCreditoDebito;
import com.nm.fcws.model.Receptor;
import com.nm.fcws.model.Remision;
import com.nm.fcws.model.Timbrado;
import com.nm.fcws.model.TipoPago;
import com.nm.fcws.model.Transporte;
import com.nm.fcws.model.Transportista;
import com.nm.fcws.model.Vehiculo;
import com.nm.fcws.modeldb.ActividadEconomica;
import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.modeldb.Distrito;
import com.nm.fcws.modeldb.Ruc;
import com.nm.fcws.repo.ComprobanteElectronicoRepo;
import com.nm.fcws.repo.DistritoRepo;
import com.nm.fcws.repo.RucRepo;
import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionDE;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.de.TdDatGralOpe;
import com.roshka.sifen.core.fields.request.de.TgActEco;
import com.roshka.sifen.core.fields.request.de.TgCamCond;
import com.roshka.sifen.core.fields.request.de.TgCamDEAsoc;
import com.roshka.sifen.core.fields.request.de.TgCamEnt;
import com.roshka.sifen.core.fields.request.de.TgCamFE;
import com.roshka.sifen.core.fields.request.de.TgCamIVA;
import com.roshka.sifen.core.fields.request.de.TgCamItem;
import com.roshka.sifen.core.fields.request.de.TgCamNCDE;
import com.roshka.sifen.core.fields.request.de.TgCamNRE;
import com.roshka.sifen.core.fields.request.de.TgCamSal;
import com.roshka.sifen.core.fields.request.de.TgCamTrans;
import com.roshka.sifen.core.fields.request.de.TgCuotas;
import com.roshka.sifen.core.fields.request.de.TgDatRec;
import com.roshka.sifen.core.fields.request.de.TgDtipDE;
import com.roshka.sifen.core.fields.request.de.TgEmis;
import com.roshka.sifen.core.fields.request.de.TgOpeCom;
import com.roshka.sifen.core.fields.request.de.TgOpeDE;
import com.roshka.sifen.core.fields.request.de.TgPaConEIni;
import com.roshka.sifen.core.fields.request.de.TgPagCheq;
import com.roshka.sifen.core.fields.request.de.TgPagCred;
import com.roshka.sifen.core.fields.request.de.TgPagTarCD;
import com.roshka.sifen.core.fields.request.de.TgTimb;
import com.roshka.sifen.core.fields.request.de.TgTotSub;
import com.roshka.sifen.core.fields.request.de.TgTransp;
import com.roshka.sifen.core.fields.request.de.TgValorItem;
import com.roshka.sifen.core.fields.request.de.TgValorRestaItem;
import com.roshka.sifen.core.fields.request.de.TgVehTras;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.core.types.PaisType;
import com.roshka.sifen.core.types.TDepartamento;
import com.roshka.sifen.core.types.TTImp;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.core.types.TTipEmi;
import com.roshka.sifen.core.types.TTipTra;
import com.roshka.sifen.core.types.TcUniMed;
import com.roshka.sifen.core.types.TiAfecIVA;
import com.roshka.sifen.core.types.TiCondCred;
import com.roshka.sifen.core.types.TiCondOpe;
import com.roshka.sifen.core.types.TiDenTarj;
import com.roshka.sifen.core.types.TiForProPa;
import com.roshka.sifen.core.types.TiIndPres;
import com.roshka.sifen.core.types.TiModTrans;
import com.roshka.sifen.core.types.TiMotEmi;
import com.roshka.sifen.core.types.TiMotivTras;
import com.roshka.sifen.core.types.TiNatRec;
import com.roshka.sifen.core.types.TiRespEmiNR;
import com.roshka.sifen.core.types.TiRespFlete;
import com.roshka.sifen.core.types.TiTIpoDoc;
import com.roshka.sifen.core.types.TiTTrans;
import com.roshka.sifen.core.types.TiTiOpe;
import com.roshka.sifen.core.types.TiTiPago;
import com.roshka.sifen.core.types.TiTipCont;
import com.roshka.sifen.core.types.TiTipDoc;
import com.roshka.sifen.core.types.TiTipDocAso;
import com.roshka.sifen.core.types.TiTipDocRec;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
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
public class ComprobanteServicio {

    private static Logger log = LoggerFactory.getLogger(ComprobanteServicio.class);

    @Autowired
    private ComprobanteElectronicoRepo comprobanteElectronicoRepo;

    @Autowired
    private RucRepo rucRepo;

    @Autowired
    private DistritoRepo distritoRepo;

    private SifenConfig getSifenConfig(Contribuyente contribuyente) {

        SifenConfig config = null;

        if (contribuyente.getAmbiente().compareTo("DEV") == 0) {

            config = new SifenConfig(
                    SifenConfig.TipoAmbiente.DEV,
                    "0001",
                    "ABCD0000000000000000000000000000",
                    SifenConfig.TipoCertificadoCliente.PFX,
                    contribuyente.getPathkey(),
                    contribuyente.getPasskey()
            );

        }

        if (contribuyente.getAmbiente().compareTo("PROD") == 0) {

            config = new SifenConfig(
                    SifenConfig.TipoAmbiente.PROD,
                    SifenConfig.TipoCertificadoCliente.PFX,
                    contribuyente.getPathkey(),
                    contribuyente.getPasskey()
            );

        }

        return config;
    }

    public DocumentoElectronico procesar(Comprobante comprobante, Contribuyente contribuyente, TTiDE tipoDE) throws SifenException, ParserConfigurationException, SAXException, IOException {

        DocumentoElectronico de = new DocumentoElectronico();

        de.setdFecFirma(comprobante.getFecha().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        // Sistema de facturacion del contribuyente siempre debe de ser 1
        de.setdSisFact(Long.valueOf(1).shortValue());

        //gOpeDE
        TgOpeDE gOpeDe = new TgOpeDE();
        gOpeDe.setiTipEmi(TTipEmi.NORMAL);

        if (!Objects.isNull(comprobante.getInfoFisco())) {

            gOpeDe.setdInfoFisc(comprobante.getInfoFisco());
        }

        de.setgOpeDE(gOpeDe);

        //timbrado
        de.setgTimb(this.procesarTimbrado(comprobante.getTimbrado(), tipoDE));

        //datos generales de operacion
        TdDatGralOpe gDatGralOpe = new TdDatGralOpe();
        gDatGralOpe.setdFeEmiDE(de.getdFecFirma());

        if (de.getgTimb().getiTiDE().getVal() == TTiDE.FACTURA_ELECTRONICA.getVal()
                || de.getgTimb().getiTiDE().getVal() == TTiDE.AUTOFACTURA_ELECTRONICA.getVal()
                || de.getgTimb().getiTiDE().getVal() == TTiDE.NOTA_DE_CREDITO_ELECTRONICA.getVal()) {

            //estandarizar a guaranies
            gDatGralOpe.setgOpeCom(this.procesarOperacionComercial(contribuyente, comprobante, de.getgTimb().getiTiDE().getVal()));

        }

        if (de.getgTimb().getiTiDE().getVal() == TTiDE.NOTA_DE_CREDITO_ELECTRONICA.getVal()
                || de.getgTimb().getiTiDE().getVal() == TTiDE.NOTA_DE_DEBITO_ELECTRONICA.getVal()) {

            de.setgCamDEAsocList(this.procesarDocumentoAsociado(comprobante.getDocAsociados()));

        }

        //Inicio Emisor de Documento Electronico
        gDatGralOpe.setgEmis(procesarEmisor(comprobante.getSucursal(), contribuyente));

        //procesar Receptor 
        gDatGralOpe.setgDatRec(procesarReceptor(comprobante.getReceptor()));

        de.setgDatGralOpe(gDatGralOpe);

        //Detalles
        //delles de pago
        TgDtipDE gDtipDE = new TgDtipDE();

        if (de.getgTimb().getiTiDE().getVal() == TTiDE.FACTURA_ELECTRONICA.getVal()
                || de.getgTimb().getiTiDE().getVal() == TTiDE.AUTOFACTURA_ELECTRONICA.getVal()) {

            TgCamFE gCamFE = new TgCamFE();
            gCamFE.setiIndPres(TiIndPres.OPERACION_ELECTRONICA); //default operacion electronica
            gDtipDE.setgCamFE(gCamFE);

            gDtipDE.setgCamCond(this.procesarMetodoPago(comprobante.getCondicionOperacion()));

        }

        if (de.getgTimb().getiTiDE().getVal() == TTiDE.NOTA_DE_REMISION_ELECTRONICA.getVal()) {

            gDtipDE.setgCamNRE(this.procesarRemision(comprobante.getRemision()));

            gDtipDE.setgTransp(this.procesarTransporte(comprobante.getTransporte()));

        }

        if (de.getgTimb().getiTiDE().getVal() == TTiDE.NOTA_DE_CREDITO_ELECTRONICA.getVal() || de.getgTimb().getiTiDE().getVal() == TTiDE.NOTA_DE_DEBITO_ELECTRONICA.getVal()) {

            gDtipDE.setgCamNCDE(procesarNotaCreditoDebito(comprobante.getNotaCreditoDebito()));

        }

        gDtipDE.setgCamItemList(this.procesarDetalle(comprobante.getDetalles()));

        de.setgDtipDE(gDtipDE);

        TgTotSub gTotSub = new TgTotSub();
        de.setgTotSub(gTotSub);

        //seccion de serializacion en base de datos
        ComprobanteElectronico ce = new ComprobanteElectronico();

        SifenConfig config = getSifenConfig(contribuyente);

        ce.setContribuyente(contribuyente);
        ce.setNumero(comprobante.getTimbrado().getEstablecimiento() + "-" + comprobante.getTimbrado().getPuntoExpedicion() + "-" + comprobante.getTimbrado().getDocumentoNro());
        ce.setXml(de.generarXml(config));
        ce.setCdc(de.obtenerCDC());

        ce.setTotal(comprobante.getTotalComprobante());
        // log.info(de.getEnlaceQR());

        this.comprobanteElectronicoRepo.save(ce);

        return de;
    }

    private TgOpeCom procesarOperacionComercial(Contribuyente contribuyente, Comprobante comprobante, short tipoComprobante) {

        TgOpeCom gOpeCom = new TgOpeCom();

        if (tipoComprobante == TTiDE.FACTURA_ELECTRONICA.getVal()
                || tipoComprobante == TTiDE.AUTOFACTURA_ELECTRONICA.getVal()) {

            gOpeCom.setiTipTra(TTipTra.getByVal(contribuyente.getTipoTransaccion().getCodigoSifen().shortValue()));
        }

        gOpeCom.setiTImp(TTImp.getByVal(contribuyente.getTipoImpuesto().getCodigoSifen().shortValue()));

        if (comprobante.getOperacionMoneda() == null) {

            gOpeCom.setcMoneOpe(CMondT.PYG); //discutir

        } else {

            gOpeCom.setcMoneOpe(CMondT.getByName(comprobante.getOperacionMoneda())); //discutir

        }

        return gOpeCom;

    }

    private TgCamCond procesarMetodoPago(CondicionOperacion condicionOperacion) {

        TgCamCond gCamCond = new TgCamCond();

        //System.out.println("Condiciones de operacion");
        //System.out.println("operacion contado " + TiCondOpe.CONTADO.getVal());
        //System.out.println("operacion credito " + TiCondOpe.CREDITO.getVal());
        gCamCond.setiCondOpe(TiCondOpe.getByVal(condicionOperacion.getCondicion().shortValue()));

        if (gCamCond.getiCondOpe().getVal() == TiCondOpe.CONTADO.getVal()) {

            List<TgPaConEIni> gPaConEIniList = new ArrayList<TgPaConEIni>();

            for (TipoPago fp : condicionOperacion.getTiposPagos()) {

                TgPaConEIni gPaConEIni = new TgPaConEIni();

                if (fp.getTipoPagoCodigo() == null) {

                    gPaConEIni.setiTiPago(TiTiPago.EFECTIVO);

                } else {

                    gPaConEIni.setiTiPago(TiTiPago.getByVal(fp.getTipoPagoCodigo().shortValue()));

                    if (gPaConEIni.getiTiPago().getVal() == TiTiPago.TARJETA_DE_CREDITO.getVal() || gPaConEIni.getiTiPago().getVal() == TiTiPago.TARJETA_DE_DEBITO.getVal()) {

                        TgPagTarCD gPagTarCD = new TgPagTarCD();

                        gPagTarCD.setiDenTarj(TiDenTarj.getByVal(fp.getTarjeta().getDenominacionTarjeta().shortValue()));

                        gPagTarCD.setiForProPa(TiForProPa.getByVal(fp.getTarjeta().getFormaProcesamiento().shortValue()));

                        try {
                            //estos campos no son obligatorios
                            if (fp.getTarjeta().getProcesadora().length() > 0) {

                                gPagTarCD.setdRSProTar(fp.getTarjeta().getProcesadora());

                            }

                            if (fp.getTarjeta().getProcesadoraRuc().length() > 0) {

                                gPagTarCD.setdRUCProTar(fp.getTarjeta().getProcesadoraRuc());

                            }

                            if (fp.getTarjeta().getProcesadoraDV() != null) {

                                gPagTarCD.setdDVProTar(fp.getTarjeta().getProcesadoraDV().shortValue());

                            }

                            if (fp.getTarjeta().getCodigoAutorizacion() != null) {

                                gPagTarCD.setdCodAuOpe(fp.getTarjeta().getCodigoAutorizacion());

                            }

                            if (fp.getTarjeta().getTarjetaNombre() != null || fp.getTarjeta().getTarjetaNombre().length() > 0) {

                                gPagTarCD.setdNomTit(fp.getTarjeta().getTarjetaNombre());

                            }

                            if (fp.getTarjeta().getTarjetaNro() != null) {

                                gPagTarCD.setdNumTarj(fp.getTarjeta().getTarjetaNro().shortValue());

                            }
                        } catch (java.lang.NullPointerException ex) {

                        }

                        gPaConEIni.setgPagTarCD(gPagTarCD);

                    }

                    if (gPaConEIni.getiTiPago().getVal() == TiTiPago.CHEQUE.getVal()) {

                        TgPagCheq gPagCheq = new TgPagCheq();

                        gPagCheq.setdNumCheq(fp.getCheque().getNro());
                        gPagCheq.setdBcoEmi(fp.getCheque().getBanco());

                        gPaConEIni.setgPagCheq(gPagCheq);

                    }

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
        }

        if (gCamCond.getiCondOpe().getVal() == TiCondOpe.CREDITO.getVal()) {

            TgPagCred gPagCred = new TgPagCred();

            gPagCred.setiCondCred(TiCondCred.getByVal(condicionOperacion.getOperacionTipo().shortValue()));

            if (gPagCred.getiCondCred().getVal() == TiCondCred.PLAZO.getVal()) {

                gPagCred.setdPlazoCre(condicionOperacion.getPlazoCredito());

            }

            if (gPagCred.getiCondCred().getVal() == TiCondCred.CUOTA.getVal()) {

                gPagCred.setdCuotas(condicionOperacion.getCantidadCuota().shortValue());

                List<TgCuotas> cuotas = new ArrayList<TgCuotas>();

                for (Cuota x : condicionOperacion.getCuotas()) {

                    TgCuotas tgCuotas = new TgCuotas();

                    if (x.getMoneda() == null) {

                        tgCuotas.setcMoneCuo(CMondT.PYG);

                    } else {

                        tgCuotas.setcMoneCuo(CMondT.getByName(x.getMoneda()));
                    }

                    tgCuotas.setdMonCuota(new BigDecimal(x.getMonto()));

                    if (x.getVencimiento() != null) {

                        tgCuotas.setdVencCuo(x.getVencimiento().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate());

                    }

                    cuotas.add(tgCuotas);

                }

                gPagCred.setgCuotasList(cuotas);

            }

            if (condicionOperacion.getMontoEntregaIni() != null) {

                gPagCred.setdMonEnt(new BigDecimal(condicionOperacion.getMontoEntregaIni()));

            }

            gCamCond.setgPagCred(gPagCred);

        }

        return gCamCond;
    }

    private TgTimb procesarTimbrado(Timbrado timbrado, TTiDE tTiDE) {

        TgTimb gTimb = new TgTimb();

        //gTimb.setiTiDE(TTiDE.FACTURA_ELECTRONICA);
        gTimb.setiTiDE(tTiDE);
        gTimb.setdNumTim(Integer.parseInt(timbrado.getTimbrado()));
        gTimb.setdEst(timbrado.getEstablecimiento());
        gTimb.setdPunExp(timbrado.getPuntoExpedicion());
        gTimb.setdNumDoc(timbrado.getDocumentoNro());
        gTimb.setdFeIniT(timbrado.getFecIni().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        return gTimb;
    }

    private TgEmis procesarEmisor(String sucursal, Contribuyente contribuyente) {

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
        gEmis.setdDenSuc(sucursal);

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

    private TgDatRec procesarReceptor(Receptor receptor) {

        int naturaleza = 1;

        if (receptor.getDv() == null || receptor.getDv().length() == 0) {

            naturaleza = 2;

        }

        TgDatRec gDatRec = new TgDatRec();
        gDatRec.setiNatRec(TiNatRec.getByVal(Short.parseShort(String.valueOf(naturaleza)))); //discutir

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

            if (Integer.parseInt(receptor.getDocNro()) >= 80000000) {

                gDatRec.setiTiContRec(TiTipCont.PERSONA_JURIDICA);

            } else {

                gDatRec.setiTiContRec(TiTipCont.PERSONA_FISICA);

            }

            gDatRec.setdRucRec(receptor.getDocNro());
            gDatRec.setdNomRec(rucRepo.findByRuc(receptor.getDocNro()).getRazonSocial());
            gDatRec.setdDVRec(Short.parseShort(receptor.getDv()));

        }

        if (naturaleza == 2 && gDatRec.getiTiOpe().getVal() != TiTiOpe.B2F.getVal()) {

            gDatRec.setiTipIDRec(TiTipDocRec.getByVal(receptor.getTipoDocumento().shortValue()));
            gDatRec.setdNumIDRec(receptor.getDocNro());

        }

        if (!Objects.isNull(receptor.getDireccion())) {
            gDatRec.setdDirRec(receptor.getDireccion());
        }
        if (!Objects.isNull(receptor.getCasaNro())) {
            gDatRec.setdNumCasRec(receptor.getCasaNro());
        }
        if (!Objects.isNull(receptor.getDepartamento())) {
            gDatRec.setcDepRec(TDepartamento.getByVal(receptor.getDepartamento().shortValue()));
        }

        Distrito dis = this.distritoRepo.findByCodigoSifen(receptor.getDistrito());

        if (!Objects.isNull(receptor.getDistrito())) {

            gDatRec.setcDisRec(dis.getCodigoSifen().shortValue());
            gDatRec.setdDesDisRec(dis.getDistrito());
        }

        if (!Objects.isNull(receptor.getCiudad())) {
            gDatRec.setcCiuRec(dis.getCodigoSifen().shortValue());
            gDatRec.setdDesCiuRec(dis.getDistrito());
        }

        return gDatRec;

        // Fin receptor
    }

    private TgCamNRE procesarRemision(Remision remision) {

        TgCamNRE gCamNRE = new TgCamNRE();

        gCamNRE.setiMotEmiNR(TiMotivTras.getByVal(remision.getMotivoEmsion().shortValue()));
        gCamNRE.setiRespEmiNR(TiRespEmiNR.getByVal(remision.getResponsableEmision().shortValue()));
        gCamNRE.setdKmR(remision.getKilometrosRecorrido());
        gCamNRE.setdFecEm(remision.getFechaEmiFactura().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        return gCamNRE;

    }

    private TgCamNCDE procesarNotaCreditoDebito(NotaCreditoDebito nota) {

        TgCamNCDE gCamNCDE = new TgCamNCDE();

        gCamNCDE.setiMotEmi(TiMotEmi.getByVal(nota.getMotivoEmision().shortValue()));

        return gCamNCDE;

    }

    private List<TgCamDEAsoc> procesarDocumentoAsociado(ArrayList<DocAsociado> documentosAsociados) {

        List<TgCamDEAsoc> lgCamDEAsoc = new ArrayList<TgCamDEAsoc>();

        for (DocAsociado x : documentosAsociados) {

            TgCamDEAsoc gCamDEAsoc = new TgCamDEAsoc();

            gCamDEAsoc.setiTipDocAso(TiTipDocAso.getByVal(x.getTipo().shortValue()));

            if (gCamDEAsoc.getiTipDocAso().getVal() == TiTipDocAso.ELECTRONICO.getVal()) {

                gCamDEAsoc.setdCdCDERef(x.getCdc());

            }

            if (gCamDEAsoc.getiTipDocAso().getVal() == TiTipDocAso.IMPRESO.getVal()) {

                gCamDEAsoc.setdNTimDI(String.valueOf(x.getTimbrado()));
                gCamDEAsoc.setdEstDocAso(x.getEstablecimiento());
                gCamDEAsoc.setdPExpDocAso(x.getPuntoExpedicion());
                gCamDEAsoc.setdNumDocAso(x.getDocNro());
                gCamDEAsoc.setiTipoDocAso(TiTIpoDoc.getByVal(x.getTipoDocAsociado().shortValue()));
                gCamDEAsoc.setdFecEmiDI(x.getFechaEmision().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());

            }

            //if (){}
            lgCamDEAsoc.add(gCamDEAsoc);
        }

        return lgCamDEAsoc;

    }

    private TgTransp procesarTransporte(Transporte transporte) {

        TgTransp gTransp = new TgTransp();

        gTransp.setiTipTrans(TiTTrans.getByVal(transporte.getTipo().shortValue()));
        gTransp.setiModTrans(TiModTrans.getByVal(transporte.getModo().shortValue()));
        gTransp.setiRespFlete(TiRespFlete.getByVal(transporte.getResponsableFlete().shortValue()));
        gTransp.setdIniTras(transporte.getFechaSalida().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        gTransp.setdFinTras(transporte.getFechaLlegada().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        gTransp.setgCamSal(procesarMercaderiaSalida(transporte.getSalida()));

        gTransp.setgCamSal(this.procesarMercaderiaSalida(transporte.getSalida()));
        gTransp.setgCamEntList(this.procesarMercaderiaEntrega(transporte.getEntregas()));
        gTransp.setgVehTrasList(this.procesarVehiculo(transporte.getVehiculos(), gTransp.getiModTrans().getDescripcion()));

        gTransp.setgCamTrans(this.procesarTransportista(transporte.getTransportista()));

        if (!Objects.isNull(transporte.getCondicionNeg())) {
            //gTransp.setcCondNeg(TcCondNeg.);
        }

        return gTransp;

    }

    private TgCamSal procesarMercaderiaSalida(MercaderiaMov mercaderiaMov) {

        TgCamSal gCamSal = new TgCamSal();

        gCamSal.setdDirLocSal(mercaderiaMov.getDireccion());
        gCamSal.setdNumCasSal(mercaderiaMov.getCasaNro().shortValue());
        gCamSal.setcDepSal(TDepartamento.getByVal(mercaderiaMov.getDepartamento().shortValue()));

        Distrito ciudad = distritoRepo.findByCodigoSifen(mercaderiaMov.getCiudad());

        gCamSal.setcCiuSal(ciudad.getCodigoSifen().shortValue());
        gCamSal.setdDesCiuSal(ciudad.getDistrito());

        return gCamSal;

    }

    private List<TgCamEnt> procesarMercaderiaEntrega(List<MercaderiaMov> lmercaderiaMov) {

        List<TgCamEnt> lgCamEnt = new ArrayList<TgCamEnt>();

        for (MercaderiaMov x : lmercaderiaMov) {

            TgCamEnt gCamEnt = new TgCamEnt();

            gCamEnt.setdDirLocEnt(x.getDireccion());
            gCamEnt.setdNumCasEnt(x.getCasaNro().shortValue());
            gCamEnt.setcDepEnt(TDepartamento.getByVal(x.getDepartamento().shortValue()));

            Distrito ciudad = distritoRepo.findByCodigoSifen(x.getCiudad());

            gCamEnt.setcCiuEnt(ciudad.getCodigoSifen().shortValue());
            gCamEnt.setdDesCiuEnt(ciudad.getDistrito());

            lgCamEnt.add(gCamEnt);

        }

        return lgCamEnt;

    }

    private List<TgVehTras> procesarVehiculo(List<Vehiculo> lvehiculo, String tipoVehiculo) {

        List<TgVehTras> lTgVehTras = new ArrayList<TgVehTras>();

        for (Vehiculo x : lvehiculo) {

            TgVehTras gVehTras = new TgVehTras();

            gVehTras.setdTiVehTras(tipoVehiculo);
            gVehTras.setdMarVeh(x.getMarca());
            gVehTras.setdTipIdenVeh(x.getTipoIdentificacion().shortValue());

            if (x.getTipoIdentificacion().longValue() == 1L) {

                gVehTras.setdNroIDVeh(x.getIdentificacionNro());

            }

            if (x.getTipoIdentificacion().longValue() == 2L) {

                gVehTras.setdMarVeh(x.getMatriculaNro());

            }
            lTgVehTras.add(gVehTras);

        }

        return lTgVehTras;

    }

    private TgCamTrans procesarTransportista(Transportista transportista) {

        TgCamTrans gCamTrans = new TgCamTrans();

        gCamTrans.setdNomTrans(transportista.getNombre());
        gCamTrans.setdDomFisc(transportista.getDomicilio());

        gCamTrans.setdNumIDChof(transportista.getChoferDocNum());
        gCamTrans.setdNomChof(transportista.getChoferNombre());
        gCamTrans.setdDirChof(transportista.getChoferDireccion());

        if (!Objects.isNull(transportista.getDv())) {

            gCamTrans.setiNatTrans(TiNatRec.CONTRIBUYENTE);
            Ruc ruc = this.rucRepo.findByRuc(transportista.getDocNro());

            gCamTrans.setdNomTrans(ruc.getRazonSocial());
            gCamTrans.setdRucTrans(transportista.getDocNro());
            gCamTrans.setdDVTrans(transportista.getDv().shortValue());

        } else {

            gCamTrans.setiNatTrans(TiNatRec.NO_CONTRIBUYENTE);
            gCamTrans.setiTipIDTrans(TiTipDoc.getByVal(transportista.getTipoDoc().shortValue()));
            gCamTrans.setdNumIDTrans(transportista.getDocNro());

            gCamTrans.setcNacTrans(PaisType.getByName(transportista.getNacionalidad()));

        }

        return gCamTrans;

    }

    private List<TgCamItem> procesarDetalle(List<ComprobanteDetalle> detalles) {

        List<TgCamItem> gCamItemList = new ArrayList<TgCamItem>();
        for (ComprobanteDetalle x : detalles) {

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

    @Async
    public void enviarDE(DocumentoElectronico de, Contribuyente contribuyente, String cdc) throws SifenException, ParserConfigurationException, SAXException, IOException {

        SifenConfig config = getSifenConfig(contribuyente);

        RespuestaRecepcionDE rrde = Sifen.recepcionDE(de, config);
        String respuesta = rrde.getRespuestaBruta();

        ComprobanteElectronico ce = comprobanteElectronicoRepo.findByCdc(cdc);
        ce.setRespuesta(respuesta);

        log.info(respuesta);

        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(respuesta));
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        d.getDocumentElement().normalize();
        NodeList nl = d.getElementsByTagName("ns2:rProtDe");

        for (int i = 0; i < nl.getLength(); i++) {

            Node n = nl.item(i);

            if (n.getNodeType() == Node.ELEMENT_NODE) {

                Element e = (Element) n;

                // ce.setCdc(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
                ce.setEstado((e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent()));
                if (e.getElementsByTagName("ns2:dCodRes").item(0).getTextContent().compareTo("0260") == 0) {

                    ce.setEnviado(true);

                }

                // log.info(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
                // log.info(e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent());
                // log.info(e.getElementsByTagName("ns2:dCodRes").item(0).getTextContent());
            }

            // log.info(e.getElementsByTagName("ns2:Id").item(0).getTextContent());
            // log.info(e.getElementsByTagName("ns2:dEstRes").item(0).getTextContent());
        }

        this.comprobanteElectronicoRepo.save(ce);

    }

}
