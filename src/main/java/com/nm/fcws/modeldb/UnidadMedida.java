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
@Table(name="UnidadesMedidas")
public class UnidadMedida implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private Long unidadmedidaid;
    private Long codigoSifen;
    private String unidadMediad;
    private String abreviatura;

    public Long getUnidadmedidaid() {
        return unidadmedidaid;
    }

    public void setUnidadmedidaid(Long unidadmedidaid) {
        this.unidadmedidaid = unidadmedidaid;
    }

    

    public Long getCodigoSifen() {
        return codigoSifen;
    }

    public void setCodigoSifen(Long codigoSifen) {
        this.codigoSifen = codigoSifen;
    }

    public String getUnidadMediad() {
        return unidadMediad;
    }

    public void setUnidadMediad(String unidadMediad) {
        this.unidadMediad = unidadMediad;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    
    
}
