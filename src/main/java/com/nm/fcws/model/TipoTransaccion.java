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
public class TipoTransaccion {
    
    private Long tipoTransaccionid;
    private Long codigoSifen;
    private String descripcion;

    public Long getTipoTransaccionid() {
        return tipoTransaccionid;
    }

    public void setTipoTransaccionid(Long tipoTransaccionid) {
        this.tipoTransaccionid = tipoTransaccionid;
    }

    public Long getCodigoSifen() {
        return codigoSifen;
    }

    public void setCodigoSifen(Long codigoSifen) {
        this.codigoSifen = codigoSifen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
