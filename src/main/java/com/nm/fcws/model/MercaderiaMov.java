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
public class MercaderiaMov {
    
    private String dicreccion;
    private Integer numCasa;
    private String direccion1;
    private String direccion2;
    
    private Long departamento;
    private Long distrito;
    private Long ciudad;
    
    private String telf;

    public String getDicreccion() {
        return dicreccion;
    }

    public void setDicreccion(String dicreccion) {
        this.dicreccion = dicreccion;
    }

    public Integer getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(Integer numCasa) {
        this.numCasa = numCasa;
    }

    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public Long getDistrito() {
        return distrito;
    }

    public void setDistrito(Long distrito) {
        this.distrito = distrito;
    }

    public Long getCiudad() {
        return ciudad;
    }

    public void setCiudad(Long ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }
    
    
}
