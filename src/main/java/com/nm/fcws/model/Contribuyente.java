/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BlackSpider
 */
@Entity
@Table(name = "Contribuyentes")
public class Contribuyente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Contribuyenteid;
    private String ruc;
    private String dv;
    
    /*
        FISICA
        JURIDICA
    */
    private String tipoContribuyente;
    private String nombre; //razon social
    private String nombreFantacia;
    private String direccion;
    private String numCasa;
    
    @ManyToOne
    @JoinColumn(name = "distritoid")
    private Distrito distrito;
    
    private String telefono;

    
    private String email;
    private String sucursal;
    
    @ManyToOne
    @JoinColumn(name = "tipoTransaccionid")
    private TipoTransaccion tipoTransaccion;
    
    @ManyToOne
    @JoinColumn(name = "tipoImpuestoid")
    private TipoImpuesto tipoImpuesto;
    
    private String pass;
    
   

    /*
    PROD = produccion
    DEV = desarrollo
     */
    
    private String ambiente;
    
    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getContribuyenteid() {
        return Contribuyenteid;
    }

    public void setContribuyenteid(Long Contribuyenteid) {
        this.Contribuyenteid = Contribuyenteid;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getTipoContribuyente() {
        return tipoContribuyente;
    }

    public void setTipoContribuyente(String tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreFantacia() {
        return nombreFantacia;
    }

    public void setNombreFantacia(String nombreFantacia) {
        this.nombreFantacia = nombreFantacia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
    
    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    
}
