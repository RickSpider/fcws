/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

/**
 *
 * @author blackspider
 */
public class Moneda {
    
    private Long monedaid;
    private String moneda;
    private String descripcion;
    private Long codigoSifen;

    public Long getMonedaid() {
        return monedaid;
    }

    public void setMonedaid(Long monedaid) {
        this.monedaid = monedaid;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCodigoSifen() {
        return codigoSifen;
    }

    public void setCodigoSifen(Long codigoSifen) {
        this.codigoSifen = codigoSifen;
    }
    
    
}
