/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nm.fcws.modeldb.Contribuyente;


/**
 *
 * @author BlackSpider
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Kude {
    
    
    public static String FACTURA_ELECTRONICA = "FE";
    public static String FACTURA_ELECTRONICA_EXPORTACION = "FEE";
    public static String FACTURA_ELECTRONICA_IMPORTACION = "FEI";
    public static String AUTOFACTURA_ELECTRONICA = "AE";
    public static String NOTA_CREDITO_ELECTRONICA = "NCE";
    public static String NOTA_DEBITO_ELECTRONICA = "NDE";
    public static String NOTA_REMISION_ELECTRONICA = "NRE";
    public static String COMPROBANTE_RETENCION_ELECTRONICA = "CRE";
    
    private Contribuyente Contribuyente;
    private String qr;
    private String cdc;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Kude(String qr, String cdc, String tipo){
    
        this.qr = qr;
        this.cdc = cdc;
        this.tipo = tipo;
       
        
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getCdc() {
        return cdc;
    }

    public void setCdc(String cdc) {
        this.cdc = cdc;
    }

    public Contribuyente getContribuyente() {
        return Contribuyente;
    }

    public void setContribuyente(Contribuyente Contribuyente) {
        this.Contribuyente = Contribuyente;
    }
    
    
    
}
