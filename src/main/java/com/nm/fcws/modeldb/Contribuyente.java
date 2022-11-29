/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    private Long contribuyenteid;
    private String ruc;
    private String dv;

    /*
        FISICA
        JURIDICA
     */
    
    @ManyToOne
    @JoinColumn(name = "tipocontribuyenteid")
    private TipoContribuyente tipoContribuyente;
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
    @JoinColumn(name = "tipotransaccionid")
    private TipoTransaccion tipoTransaccion;

    @ManyToOne
    @JoinColumn(name = "tipoimpuestoid")
    private TipoImpuesto tipoImpuesto;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "contribuyentesactividades",
            joinColumns = @JoinColumn(name = "contribuyenteid"),
            inverseJoinColumns = @JoinColumn(name = "actividadeconomicaid"))
    private Set<ActividadEconomica> actividades;

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
        return contribuyenteid;
    }

    public void setContribuyenteid(Long contribuyenteid) {
        this.contribuyenteid = contribuyenteid;
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

    public TipoContribuyente getTipoContribuyente() {
        return tipoContribuyente;
    }

    public void setTipoContribuyente(TipoContribuyente tipoContribuyente) {
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

    public Set<ActividadEconomica> getActividades() {
        return actividades;
    }

    public void setActividades(Set<ActividadEconomica> actividades) {
        this.actividades = actividades;
    }

}