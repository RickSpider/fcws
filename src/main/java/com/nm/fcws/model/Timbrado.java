/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BlackSpider
 */
public class Timbrado {
 
    @NotNull(message="Falta numero de Timbrado")
    private String timbrado;
    
    @NotBlank(message="Falta numero de establecimiento ej: 001")
    private String establecimiento;
    
    @NotBlank(message="Falta punto de expedicion ej: 001")
    private String puntoExpedicion;
    
    @NotBlank(message="Falta numero de documento ej: 0000001")
    private String documentoNro;
    
    @NotNull(message="Falta la fecha de inicio del timbrado formato ")
    private Date fecIni;

    public String getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(String timbrado) {
        this.timbrado = timbrado;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getPuntoExpedicion() {
        return puntoExpedicion;
    }

    public void setPuntoExpedicion(String puntoExpedicion) {
        this.puntoExpedicion = puntoExpedicion;
    }

    public String getDocumentoNro() {
        return documentoNro;
    }

    public void setDocumentoNro(String documentoNro) {
        this.documentoNro = documentoNro;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }
    
    
    
}
