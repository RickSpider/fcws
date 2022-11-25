/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

/**
 *
 * @author BlackSpider
 */
public class TipoPago {
    
    private Long tipoPagoCodigo;
    private double monto;
    private String modeda;
 
    public Long getTipoPagoCodigo() {
        return tipoPagoCodigo;
    }

    public void setTipoPagoCodigo(Long tipoPagoCodigo) {
        this.tipoPagoCodigo = tipoPagoCodigo;
    }
    
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getModeda() {
        return modeda;
    }

    public void setModeda(String modeda) {
        this.modeda = modeda;
    }

 
    
    
    
}
