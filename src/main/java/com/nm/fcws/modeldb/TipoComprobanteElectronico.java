/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.modeldb;

import javax.persistence.Column;
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
@Table(name="tiposcomprobanteselectronicos")
public class TipoComprobanteElectronico {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private Long tipocomprobanteelectronicoid;
    
    @Column(name="tipocomprobanteelectronico")
    private String tipoComprobanteElectronico;
    
    public TipoComprobanteElectronico(){}

    public TipoComprobanteElectronico(Long tipocomprobanteelectronicoid) {
        this.tipocomprobanteelectronicoid = tipocomprobanteelectronicoid;
    }

    public TipoComprobanteElectronico(Long tipocomprobanteelectronicoid, String tipoComprobanteElectronico) {
        this.tipocomprobanteelectronicoid = tipocomprobanteelectronicoid;
        this.tipoComprobanteElectronico = tipoComprobanteElectronico;
    }
    
    public Long getTipocomprobanteelectronicoid() {
        return tipocomprobanteelectronicoid;
    }

    public void setTipocomprobanteelectronicoid(Long tipocomprobanteelectronicoid) {
        this.tipocomprobanteelectronicoid = tipocomprobanteelectronicoid;
    }

    public String getTipoComprobanteElectronico() {
        return tipoComprobanteElectronico;
    }

    public void setTipoComprobanteElectronico(String tipoComprobanteElectronico) {
        this.tipoComprobanteElectronico = tipoComprobanteElectronico;
    }
    
    
}
