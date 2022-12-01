/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author BlackSpider
 */
@Entity
@Table(name="comprobanteselectronicos")
public class ComprobanteElectronico implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "contribuyenteid")
    private Contribuyente contribuyente;
    
    @Column(columnDefinition="text")
    private String xml;
    
    @CreationTimestamp 
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCarga;
    
    private double totalFactura;
    @Column(columnDefinition="text")
    private String respuestaBruta;

    public String getRespuestaBruta() {
        return respuestaBruta;
    }

    public void setRespuestaBruta(String respuestaBruta) {
        this.respuestaBruta = respuestaBruta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Contribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

}
