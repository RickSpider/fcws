/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

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

/**
 *
 * @author blackspider
 */
@Entity
@Table(name = "Eventos")
public class Evento {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long eventoid;
    
    private String cdc;
    
     @Column(columnDefinition="text")
    private String motivo = "";
    
    @Column(columnDefinition = "boolean default false")
    private boolean enviado;
    
    private String estado;
    
     @Column(columnDefinition="text")
    private String respuesta;
    
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "tipocomprobanteelectronicoid")
    private TipoComprobanteElectronico tipoComprobanteElectronico;
    
    @ManyToOne
    @JoinColumn(name = "contribuyenteid")
    private Contribuyente contribuyente;

    public Long getEventoid() {
        return eventoid;
    }

    public void setEventoid(Long eventoid) {
        this.eventoid = eventoid;
    }

    public String getCdc() {
        return cdc;
    }

    public void setCdc(String cdc) {
        this.cdc = cdc;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Contribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Contribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public TipoComprobanteElectronico getTipoComprobanteElectronico() {
        return tipoComprobanteElectronico;
    }

    public void setTipoComprobanteElectronico(TipoComprobanteElectronico tipoComprobanteElectronico) {
        this.tipoComprobanteElectronico = tipoComprobanteElectronico;
    }
    
    
}
