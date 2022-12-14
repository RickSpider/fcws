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
    private Integer kilometrosRecorrido;
    private Date fechaEmiFactura;

    /**
     * 
     * Motivo de Remision
     * 1: Traslado por venta
     * 2: Traslado por consignacion
     * 3: Exportacion
     * 4: Traslado por compra
     * 5: Importacion
     * 6: Traslado por devolucion
     * 7: Traslado entre locales de la empresa
     * 8: Traslado de bienes por transformacion
     * 9: Traslado de bienes por reparacion
     * 10: Traslado por emisor movil 
     * 11: Exhibicion o demostracion
     * 12: Participacion en ferias
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
     * 2: Traslado por consignacion
     * 3: Exportacion
     * 4: Traslado por compra
     * 5: Importacion
     * 6: Traslado por devolucion
     * 7: Traslado entre locales de la empresa
     * 8: Traslado de bienes por transformacion
     * 9: Traslado de bienes por reparacion
     * 10: Traslado por emisor movil 
     * 11: Exhibicion o demostracion
     * 12: Participacion en ferias
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

    public Date getFechaEmiFactura() {
        return fechaEmiFactura;
    }

    
    /**
     * cuando no se ha emitido a??n la factura electr??nica, en caso que corresponda.
     *
     * 
     * @param fechaEmiFactura 
     */
    public void setFechaEmiFactura(Date fechaEmiFactura) {
        this.fechaEmiFactura = fechaEmiFactura;
    }
    
    
}
