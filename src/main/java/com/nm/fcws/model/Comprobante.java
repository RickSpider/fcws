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
    
    private Timbrado timbrado;

    private String sucursal;
   
    //conforme a la moneda en sifen
    private String operacionMoneda;

    private Receptor receptor;
    
    //private String receptorTipoPersona;
   // private int tipoTransaccion;
    //private int tipoImpuesto;
    
  
    private Date fecha;

   
    //detalles de pago
    
    /*
        1= Contado
        2= credito
    */
  //  private Long condicionOperacion;
    
    private CondicionOperacion condicionOperacion;
    
   // private ArrayList<TipoPago> tiposPagos = new ArrayList<TipoPago>() ;
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

    public Timbrado getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(Timbrado timbrado) {
        this.timbrado = timbrado;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    public CondicionOperacion getCondicionOperacion() {
        return condicionOperacion;
    }

    public void setCondicionOperacion(CondicionOperacion condicionOperacion) {
        this.condicionOperacion = condicionOperacion;
    }
    
    
   
}
