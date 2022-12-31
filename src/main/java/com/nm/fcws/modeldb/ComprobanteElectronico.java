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
import javax.persistence.Index;
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
@Table(name="comprobanteselectronicos"
        ,indexes = {
            @Index(name="cdc_index", columnList="cdc", unique=true)
        }
)
public class ComprobanteElectronico implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "contribuyenteid")
    private Contribuyente contribuyente;
    
    @Column(columnDefinition="text")
    private String xml;
    
    @CreationTimestamp 
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    private String cdc;

    private String numero;
    private double total;
    
    @Column(columnDefinition="text")
    private String respuesta;
    
    private String estado;
    
    @Column(columnDefinition = "boolean default false")
    private boolean enviado = false;
    
    //Seccion Lote
    @Column(columnDefinition = "boolean default false")
    private boolean lote = false;
    
    @Column(columnDefinition = "boolean default false", name="enviadolote")
    private boolean enviadoLote = false;
    
    @Column(name="fechaenviadolote")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnviadoLote;
    
    @Column(name="lotenro")
    private String loteNro;
    
    //Seccion evento
    @ManyToOne
    @JoinColumn(name = "tipocomprobanteelectronicoid")
    private TipoComprobanteElectronico tipoComprobanteElectronico;
    
    @Column(columnDefinition = "boolean default false")
    private boolean evento = false;
    
    @Column(name="eventoestado")
    private String eventoEstado;
    
    @Column(name="eventorespuesta", columnDefinition="text")
    private String eventoRespuesta;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

     public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getCdc() {
        return cdc;
    }
    
      public void setCdc(String cdc) {
        this.cdc = cdc;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public TipoComprobanteElectronico getTipoComprobanteElectronico() {
        return tipoComprobanteElectronico;
    }

    public void setTipoComprobanteElectronico(TipoComprobanteElectronico tipoComprobanteElectronico) {
        this.tipoComprobanteElectronico = tipoComprobanteElectronico;
    }

    public boolean isEvento() {
        return evento;
    }

    public void setEvento(boolean evento) {
        this.evento = evento;
    }

    public String getEventoEstado() {
        return eventoEstado;
    }

    public void setEventoEstado(String eventoEstado) {
        this.eventoEstado = eventoEstado;
    }

    public String getEventoRespuesta() {
        return eventoRespuesta;
    }

    public void setEventoRespuesta(String eventoRespuesta) {
        this.eventoRespuesta = eventoRespuesta;
    }

    public boolean isLote() {
        return lote;
    }

    public void setLote(boolean lote) {
        this.lote = lote;
    }

    public boolean isEnviadoLote() {
        return enviadoLote;
    }

    public void setEnviadoLote(boolean enviadoLote) {
        this.enviadoLote = enviadoLote;
    }

    public Date getFechaEnviadoLote() {
        return fechaEnviadoLote;
    }

    public void setFechaEnviadoLote(Date fechaEnviadoLote) {
        this.fechaEnviadoLote = fechaEnviadoLote;
    }

    public String getLoteNro() {
        return loteNro;
    }

    public void setLoteNro(String loteNro) {
        this.loteNro = loteNro;
    }

    
    
    
}
