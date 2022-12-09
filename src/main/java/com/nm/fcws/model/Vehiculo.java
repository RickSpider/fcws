/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

/**
 *
 * @author BlackSpider
 */
public class Vehiculo {
    
    private Long tipoVehiculo;
    private String marca;
    private Long tipoIdentificacion;
    private String nroIdentificacion;
    private String datosAdicionales;
    private String matriculaNro;
    private String vueloNro;

    public Long getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Long tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Long tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNroIdentificacion() {
        return nroIdentificacion;
    }

    public void setNroIdentificacion(String nroIdentificacion) {
        this.nroIdentificacion = nroIdentificacion;
    }

    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public String getMatriculaNro() {
        return matriculaNro;
    }

    public void setMatriculaNro(String matriculaNro) {
        this.matriculaNro = matriculaNro;
    }

    public String getVueloNro() {
        return vueloNro;
    }

    public void setVueloNro(String vueloNro) {
        this.vueloNro = vueloNro;
    }
    
    
    
}
