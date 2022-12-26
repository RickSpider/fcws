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
    
    private Contribuyente Contribuyente;
    private String qr;
    private String cdc;
    
    public Kude(String qr, String cdc){
    
        this.qr = qr;
        this.cdc = cdc;
        
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
