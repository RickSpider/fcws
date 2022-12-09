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
    private Long responsable;
    private String manifiestoNro;
    private String despachoNro;
    private Calendar fechaSalida;
    private Calendar fechaLlegada;
    private Long paisDestino;

    public Long getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(Long tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public Long getModoTransporte() {
        return modoTransporte;
    }

    public void setModoTransporte(Long modoTransporte) {
        this.modoTransporte = modoTransporte;
    }

    public Long getResponsable() {
        return responsable;
    }

    public void setResponsable(Long responsable) {
        this.responsable = responsable;
    }

    public String getManifiestoNro() {
        return manifiestoNro;
    }

    public void setManifiestoNro(String manifiestoNro) {
        this.manifiestoNro = manifiestoNro;
    }

    public String getDespachoNro() {
        return despachoNro;
    }

    public void setDespachoNro(String despachoNro) {
        this.despachoNro = despachoNro;
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
    
    
    
}
