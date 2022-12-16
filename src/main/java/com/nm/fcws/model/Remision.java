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

    /**
     * 
     * Motivo de Remision
     * 1: Traslado por venta
     * 2: Traslado por consignacion
     * 3: Exportaci�n
     * 4: Traslado por compra
     * 5: Importaci�n
     * 6: Traslado por devolucion
     * 7: Traslado entre locales de la empresa
     * 8: Traslado de bienes por transformaci�n
     * 9: Traslado de bienes por reparaci�n
     * 10: Traslado por emisor movil 
     * 11: Exhibici�n o demostracion
     * 12: Participaci�n en ferias
     * 13: Traslado de encomienda
     * 14: Decomiso
     * 99: Otro
     * 
     * Responsables
     * 1:Emisor de la factura
     * 2:Poseedor de la Factura y Bienes
     * 3:Despachante de Aduanas
     * 5:Agente de transporte o intermediario
     * 
     * @param motivoEmsion
     * @param responsableEmision 
     */
    public Remision(Long motivoEmsion, Long responsableEmision) {
        this.motivoEmsion = motivoEmsion;
        this.responsableEmision = responsableEmision;
    }
    
    
 
    public Long getMotivoEmsion() {
        return motivoEmsion;
    } 

    /**
     * 
     * 
     * 1: Traslado por venta
     * 2: Traslado por consignaci�n
     * 3: Exportaci�n
     * 4: Traslado por compra
     * 5: Importaci�n
     * 6: Traslado por devoluci�n
     * 7: Traslado entre locales de la empresa
     * 8: Traslado de bienes por transformaci�n
     * 9: Traslado de bienes por reparaci�n
     * 10: Traslado por emisor m�vil 
     * 11: Exhibici�n o demostraci�n
     * 12: Participaci�n en ferias
     * 13: Traslado de encomienda
     * 14: Decomiso
     * 99: Otro
     * 
     * @param motivoEmsion 
     */
    public void setMotivoEmsion(Long motivoEmsion) {
        this.motivoEmsion = motivoEmsion;
    }

    public Long getResponsableEmision() {
        return responsableEmision;
    }

    /**
     * 
     * 1:Emisor de la factura
     * 2:Poseedor de la Factura y Bienes
     * 3:Despachante de Aduanas
     * 5:Agente de transporte o intermediario
     * 
     * @param responsableEmision 
     */
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
