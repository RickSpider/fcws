/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import com.nm.fcws.modeldb.Contribuyente;
import java.util.ArrayList;
import java.util.Calendar;
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
    private Calendar timbradoFecIni;
    
    
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
    private String receptorDocNum;
    private String receptorDV;
    
    //estos campos se debe agregar si el receptor no tiene ruc
    private Long receptorTipoDocumento;
    private String receptorDireccion;
    private Integer receptorNumCasa;
    private Long receptorDepartamento;
    private Long receptorDistrito;
    private Long receptorCiudad;

    
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

    public Calendar getTimbradoFecIni() {
        return timbradoFecIni;
    }

    public void setTimbradoFecIni(Calendar timbradoFecIni) {
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

    public String getReceptorDocNum() {
        return receptorDocNum;
    }

    public void setReceptorDocNum(String receptorDocNum) {
        this.receptorDocNum = receptorDocNum;
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

    public Long getReceptorTipoDocumento() {
        return receptorTipoDocumento;
    }

    public void setReceptorTipoDocumento(Long receptorTipoDocumento) {
        this.receptorTipoDocumento = receptorTipoDocumento;
    }

    public String getReceptorDireccion() {
        return receptorDireccion;
    }

    public void setReceptorDireccion(String receptorDireccion) {
        this.receptorDireccion = receptorDireccion;
    }

    public Integer getReceptorNumCasa() {
        return receptorNumCasa;
    }

    public void setReceptorNumCasa(Integer receptorNumCasa) {
        this.receptorNumCasa = receptorNumCasa;
    }

    public Long getReceptorDepartamento() {
        return receptorDepartamento;
    }

    public void setReceptorDepartamento(Long receptorDepartamento) {
        this.receptorDepartamento = receptorDepartamento;
    }

    public Long getReceptorDistrito() {
        return receptorDistrito;
    }

    public void setReceptorDistrito(Long receptorDistrito) {
        this.receptorDistrito = receptorDistrito;
    }

    public Long getReceptorCiudad() {
        return receptorCiudad;
    }

    public void setReceptorCiudad(Long receptorCiudad) {
        this.receptorCiudad = receptorCiudad;
    }

  
    
}
