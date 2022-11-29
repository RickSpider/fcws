/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BlackSpider
 */

@Entity
@Table(name="tiposcontribuyentes")
public class TipoContribuyente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private Long tipocontribuyenteid;
    private String tipoContribuyente;
    private Long codigoSifen;

    public Long getCodigoSifen() {
        return codigoSifen;
    }

    public void setCodigoSifen(Long codigoSifen) {
        this.codigoSifen = codigoSifen;
    }

   

    

   
    public Long getTipocontribuyenteid() {
        return tipocontribuyenteid;
    }

    public void setTipocontribuyenteid(Long tipocontribuyenteid) {
        this.tipocontribuyenteid = tipocontribuyenteid;
    }

    public String getTipoContribuyente() {
        return tipoContribuyente;
    }

    public void setTipoContribuyente(String tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

  
    
}
