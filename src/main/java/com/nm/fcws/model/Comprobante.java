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
    
   // private Long comprobanteid;
    
    
    //datos contribuyente
    private Contribuyente contribuyente;
    
    private String timbrado;
    private String establecimiento;
    private String puntoExpedicion;
    private String documentoNum;
    private Date timbradoFecIni;
    
    
    private String sucursal;
    
    
    //conforme a la moneda en sifen
    private String operacionMoneda;
    
    //datos receptor
    /*
        1= Contribuyente1
        2= No Contribuyente
    */
    private int receptorNaturaleza;
    
    /*
        1= B2B
        2= B2C
        3= B2G
        4= B2F 
    */
    private int receptorTipoOperacion;
    private String receptorRuc;
    private String receptorDV;
    //private String receptorTipoPersona;
   // private int tipoTransaccion;
    //private int tipoImpuesto;
    
    
    private Date fecha;

   
    //detalles de pago
    
    /*
        1= Contado
        2= credito
    */
    private Long condicionOperacion;
    private ArrayList<TipoPago> tiposPagos = new ArrayList<TipoPago>() ;
    private ArrayList<ComprobanteDetalle> detalles = new ArrayList<ComprobanteDetalle>();

 /*   public Long getComprobanteid() {
        return comprobanteid;
    }

    public void setComprobanteid(Long comprobanteid) {
        this.comprobanteid = comprobanteid;
    }*/

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

    public int getReceptorNaturaleza() {
        return receptorNaturaleza;
    }

    public void setReceptorNaturaleza(int receptorNaturaleza) {
        this.receptorNaturaleza = receptorNaturaleza;
    }

    public int getReceptorTipoOperacion() {
        return receptorTipoOperacion;
    }

    public void setReceptorTipoOperacion(int receptorTipoOperacion) {
        this.receptorTipoOperacion = receptorTipoOperacion;
    }

    public String getReceptorRuc() {
        return receptorRuc;
    }

    public void setReceptorRuc(String receptorRuc) {
        this.receptorRuc = receptorRuc;
    }

    public String getReceptorDV() {
        return receptorDV;
    }

    public void setReceptorDV(String receptorDV) {
        this.receptorDV = receptorDV;
    }

    public Long getCondicionOperacion() {
        return condicionOperacion;
    }

    public void setCondicionOperacion(Long condicionOperacion) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
