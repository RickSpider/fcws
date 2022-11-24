/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BlackSpider
 */

@Entity
@Table(name = "Localidades")
public class Localidad implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private Long localidadid;
    private String localidad;
    private Long codigoSifen;
    
    @ManyToOne
    @JoinColumn(name = "distritoid")
    private Distrito distrito;

    public Long getLocalidadid() {
        return localidadid;
    }

    public void setLocalidadid(Long localidadid) {
        this.localidadid = localidadid;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Long getCodigoSifen() {
        return codigoSifen;
    }

    public void setCodigoSifen(Long codigoSifen) {
        this.codigoSifen = codigoSifen;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }
    
    
}
