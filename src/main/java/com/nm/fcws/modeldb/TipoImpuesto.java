/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author blackspider
 */

@Entity
@Table(name="tiposimpuestos")
public class TipoImpuesto implements Serializable{
 
    @Id
    private Long tipoImpuestoid;
    private Long codigoSifen;
    private String descripcion;

    public Long getTipoImpuestoid() {
        return tipoImpuestoid;
    }

    public void setTipoImpuestoid(Long tipoImpuestoid) {
        this.tipoImpuestoid = tipoImpuestoid;
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
