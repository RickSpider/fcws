/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import java.util.Calendar;

/**
 *
 * @author BlackSpider
 */
public class Remision {
    
    private Long motivoEmsion;
    private Long responsableEmision;
    private Integer kilometrosRecorrido;
    private Calendar fechaEmiFactura;

    public Long getMotivoEmsion() {
        return motivoEmsion;
    } 

    /**
     * 
     * 1: Devolucion y Ajuste de precios
     * 2: Devolucion
     * 3: Descuento
     * 4: Bonificacion
     * 5: Crodito incobrable
     * 6: Recupero de costo
     * 7: Recupero de gasto
     * 8: Ajuste de precio
     * 
     * @param motivoEmsion 
     */
    public void setMotivoEmsion(Long motivoEmsion) {
        this.motivoEmsion = motivoEmsion;
    }

    public Long getResponsableEmision() {
        return responsableEmision;
    }

    public void setResponsableEmision(Long responsableEmision) {
        this.responsableEmision = responsableEmision;
    }

    public Integer getKilometrosRecorrido() {
        return kilometrosRecorrido;
    }

    public void setKilometrosRecorrido(Integer kilometrosRecorrido) {
        this.kilometrosRecorrido = kilometrosRecorrido;
    }

    public Calendar getFechaEmiFactura() {
        return fechaEmiFactura;
    }

    
    /**
     * cuando no se ha emitido aún la factura electrónica, en caso que corresponda.
     *
     * 
     * @param fechaEmiFactura 
     */
    public void setFechaEmiFactura(Calendar fechaEmiFactura) {
        this.fechaEmiFactura = fechaEmiFactura;
    }
    
    
}
