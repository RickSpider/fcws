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
public class Receptor {

    private String docNro;
    private String dv;
    
    //estos campos se debe agregar si el receptor no tiene ruc
    private Long tipoDocumento;
    private String direccion;
    private Integer casaNro;
    private Long departamento;
    private Long distrito;
    private Long ciudad;

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public Long getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Long tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getDocNro() {
        return docNro;
    }

    public void setDocNro(String docNro) {
        this.docNro = docNro;
    }

    public Integer getCasaNro() {
        return casaNro;
    }

    public void setCasaNro(Integer casaNro) {
        this.casaNro = casaNro;
    }
    
    
  
}
