/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import com.nm.fcws.modeldb.Contribuyente;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author BlackSpider
 */

//por el momento solo soporte para guaranies

public class Comprobante {
    
    private Long comprobanteid;
    
    
    //datos contribuyente
    private Contribuyente contribuyente;
    
    private String timbrado;
    private String timbradoNum;
    private String establecimiento;
    private String puntoExpedicion;
    private String documentoNum;
    private Date timbradoFecIni;
    
    
    private String sucursal;
    
    
    //conforme a la moneda en sifen
    private String operacionMoneda;
    
    //datos cliente
    /*
        1= Contribuyente
        2= No Contribuyente
    */
    private String clienteNaturaleza;
    
    /*
        1= B2B
        2= B2C
        3= B2G
        4= B2F 
    */
    private int clienteTipoOperacion;
    private String clienteRuc;
    private String clienteDV;
    private String clienteTipoPersona;
    private int tipoTransaccion;
    private int tipoImpuesto;
    
    
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    //detalles de pago
    
    /*
        1= Contado
        2= Cr�dito
    */
    private int condicionOperacion;
    private ArrayList<TipoPago> tiposPagos = new ArrayList<TipoPago>() ;
    private ArrayList<ComprobanteDetalle> detalles = new ArrayList<ComprobanteDetalle>();

    public Long getComprobanteid() {
        return comprobanteid;
    }

    public void setComprobanteid(Long comprobanteid) {
        this.comprobanteid = comprobanteid;
    }

    public Contribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Contribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public String getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(String timbrado) {
        this.timbrado = timbrado;
    }

    public String getTimbradoNum() {
        return timbradoNum;
    }

    public void setTimbradoNum(String timbradoNum) {
        this.timbradoNum = timbradoNum;
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

    public String getDocumentoNum() {
        return documentoNum;
    }

    public void setDocumentoNum(String documentoNum) {
        this.documentoNum = documentoNum;
    }

    public Date getTimbradoFecIni() {
        return timbradoFecIni;
    }

    public void setTimbradoFecIni(Date timbradoFecIni) {
        this.timbradoFecIni = timbradoFecIni;
    }

    public String getClienteNaturaleza() {
        return clienteNaturaleza;
    }

    public void setClienteNaturaleza(String clienteNaturaleza) {
        this.clienteNaturaleza = clienteNaturaleza;
    }

    public int getClienteTipoOperacion() {
        return clienteTipoOperacion;
    }

    public void setClienteTipoOperacion(int clienteTipoOperacion) {
        this.clienteTipoOperacion = clienteTipoOperacion;
    }

    public String getClienteRuc() {
        return clienteRuc;
    }

    public void setClienteRuc(String clienteRuc) {
        this.clienteRuc = clienteRuc;
    }

    public String getClienteDV() {
        return clienteDV;
    }

    public void setClienteDV(String clienteDV) {
        this.clienteDV = clienteDV;
    }

    public int getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(int tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public int getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(int tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public int getCondicionOperacion() {
        return condicionOperacion;
    }

    public void setCondicionOperacion(int condicionOperacion) {
        this.condicionOperacion = condicionOperacion;
    }

    public ArrayList<ComprobanteDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<ComprobanteDetalle> detalles) {
        this.detalles = detalles;
    }
    
    
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
    
     public String getClienteTipoPersona() {
        return clienteTipoPersona;
    }

    public void setClienteTipoPersona(String clienteTipoPersona) {
        this.clienteTipoPersona = clienteTipoPersona;
    }

    public String getOperacionMoneda() {
        return operacionMoneda;
    }

    public void setOperacionMoneda(String operacionMoneda) {
        this.operacionMoneda = operacionMoneda;
    }

    public ArrayList<TipoPago> getTiposPagos() {
        return tiposPagos;
    }

    public void setTiposPagos(ArrayList<TipoPago> tiposPagos) {
        this.tiposPagos = tiposPagos;
    }

    
   
    
}
