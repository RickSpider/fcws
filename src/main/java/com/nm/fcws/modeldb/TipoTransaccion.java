/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author blackspider
 */
@Entity
@Table(name="tipstransaccion")
public class TipoTransaccion implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
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
