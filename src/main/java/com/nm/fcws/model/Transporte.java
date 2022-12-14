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
public class Transporte {
    
    private Long tipoTransporte;
    private Long modoTransporte;
    private Long responsableFlete;
    private String condicionNeg;
    private String manifiestoNro;
    private String despachoImpNro;
    private Calendar fechaSalida;
    private Calendar fechaLlegada;
    private Long paisDestino;

    
    /**
     * Campos minimos para declarar un Transporte 
     * 
     * Posibles Tipos de Transporte
     * 1:Propio
     * 2:Tercero
     * 
     * Posibles responsables del costo de transporte
     * 1:Emisor de factura
     * 2:Receptor de la factura
     * 3:Tercero
     * 4:Agente intermediario del transorte (cuado interventa)
     * 5:Transporte propio
     * 
     * @param modoTransporte
     * @param responsableFlete 
     */
    public Transporte(Long modoTransporte, Long responsableFlete) {
        this.modoTransporte = modoTransporte;
        this.responsableFlete = responsableFlete;
    }
    
   

    public Long getTipoTransporte() {
        return tipoTransporte;
    }

    /**
     * 
     * Posibles modos de Transporte
     * 1:Terrestre
     * 2:Flvial
     * 3:Aereo
     * 4:Multimodal
     * 
     * @param tipoTransporte 
     */
    public void setTipoTransporte(Long tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public Long getModoTransporte() {
        return modoTransporte;
    }

    /**
     * Posibles modos de Transporte
     * 1:Terrestre
     * 2:Flvial
     * 3:Aereo
     * 4:Multimodal
     * 
     * @param modoTransporte 
     */
    public void setModoTransporte(Long modoTransporte) {
        this.modoTransporte = modoTransporte;
    }

    public Long getResponsableFlete() {
        return responsableFlete;
    }

    /**
     * 
     * Posibles responsables del costo de transporte
     * 1:Emisor de factura
     * 2:Receptor de la factura
     * 3:Tercero
     * 4:Agente intermediario del transorte (cuado interventa)
     * 5:Transporte propio
     * 
     * @param responsable 
     */
    public void setResponsableFlete(Long responsableFlete) {
        this.responsableFlete = responsableFlete;
    }

    public String getManifiestoNro() {
        return manifiestoNro;
    }

    public void setManifiestoNro(String manifiestoNro) {
        this.manifiestoNro = manifiestoNro;
    }

    public Calendar getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Calendar fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Calendar getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Calendar fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Long getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(Long paisDestino) {
        this.paisDestino = paisDestino;
    }


    public String getCondicionNeg() {
        return condicionNeg;
    }

    public void setCondicionNeg(String condicionNeg) {
        this.condicionNeg = condicionNeg;
    }

    public String getDespachoImpNro() {
        return despachoImpNro;
    }

    public void setDespachoImpNro(String despachoImpNro) {
        this.despachoImpNro = despachoImpNro;
    }
    
    
    
}
