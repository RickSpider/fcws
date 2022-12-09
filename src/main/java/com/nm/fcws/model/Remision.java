/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import java.util.Date;

/**
 *
 * @author BlackSpider
 */
public class Remision {
    
    private Long motivoEmsion;
    private Long responsableEmision;
    private double kilometrosRecorrido;
    private Date fechaEmiFactura;

    public Long getMotivoEmsion() {
        return motivoEmsion;
    }

    public void setMotivoEmsion(Long motivoEmsion) {
        this.motivoEmsion = motivoEmsion;
    }

    public Long getResponsableEmision() {
        return responsableEmision;
    }

    public void setResponsableEmision(Long responsableEmision) {
        this.responsableEmision = responsableEmision;
    }

    public double getKilometrosRecorrido() {
        return kilometrosRecorrido;
    }

    public void setKilometrosRecorrido(double kilometrosRecorrido) {
        this.kilometrosRecorrido = kilometrosRecorrido;
    }

    public Date getFechaEmiFactura() {
        return fechaEmiFactura;
    }

    public void setFechaEmiFactura(Date fechaEmiFactura) {
        this.fechaEmiFactura = fechaEmiFactura;
    }
    
    
}
