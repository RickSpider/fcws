/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.model;

import com.nm.fcws.interfaces.iCancelacion;
import com.nm.fcws.interfaces.iEvento;
import com.nm.fcws.modeldb.Contribuyente;
import java.util.ArrayList;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.nm.fcws.interfaces.iFactura;
import com.nm.fcws.interfaces.iNotaCD;
import com.nm.fcws.interfaces.iRemision;

/**
 *
 * @author BlackSpider
 */

//por el momento solo soporte para guaranies

public class Comprobante{
    

    @NotNull(groups = {iFactura.class, iNotaCD.class, iRemision.class, iCancelacion.class, iEvento.class}, message = "Falta datos de Contribuyente" )
    private Contribuyente contribuyente;
    
    @NotNull(groups = {iFactura.class, iNotaCD.class, iRemision.class}, message = "Falta datos de Timbrado")
    private Timbrado timbrado;

    @NotBlank(groups = {iFactura.class, iNotaCD.class, iRemision.class}, message="Falta nombre de la Sucursal de Emision")
    private String sucursal;
   
    //conforme a la moneda en sifen
    private String operacionMoneda;

    @NotNull(groups = {iFactura.class, iNotaCD.class, iRemision.class}, message = "Falta datos del Receptor")
    private Receptor receptor;
  
    @NotNull(groups = {iFactura.class, iNotaCD.class, iRemision.class, iCancelacion.class, iEvento.class}, message = "Falta la Fecha de Emision del comprobante")
    private Date fecha;
    
    private CondicionOperacion condicionOperacion;
    
    @NotNull(groups = {iFactura.class, iNotaCD.class, iRemision.class}, message = "Faltan Los detalles del comprobante")
    private ArrayList<ComprobanteDetalle> detalles = new ArrayList<ComprobanteDetalle>();
    
    @NotNull(message = "Faltan datos de Transporte", groups = iRemision.class)
    private Transporte transporte;

    @NotNull(message = "Faltan datos de Remision", groups = iRemision.class)
    private Remision remision;
    
    @NotNull(message = "Faltan datos de Remision", groups = iNotaCD.class)
    private NotaCreditoDebito notaCreditoDebito;

    @NotNull(message = "Faltan datos de Remision", groups = iNotaCD.class)
    private ArrayList<DocAsociado> docAsociados;
    
    @NotNull(message = "Faltan datos de Informacion para el Fisco", groups = iRemision.class)
    private String infoFisco;
    
    @NotNull(message = "Falta el monto total del comprobante", groups = {iFactura.class, iNotaCD.class})
    private Double totalComprobante;
    
   
    private Double totalIVA10;
    
    
    private Double totalIVA5;
    
  
    private Double totalExcento;
    
    //seccion de cancelacion
    @NotBlank(message = "Falta el CDC del comprobante", groups = {iCancelacion.class, iEvento.class})
    private String cdc;
    
    private String motivoEvento;

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

    public Remision getRemision() {
        return remision;
    }

    public void setRemision(Remision remision) {
        this.remision = remision;
    }
    
    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public String getInfoFisco() {
        return infoFisco;
    }

    public void setInfoFisco(String infoFisco) {
        this.infoFisco = infoFisco;
    }

    public NotaCreditoDebito getNotaCreditoDebito() {
        return notaCreditoDebito;
    }

    public void setNotaCreditoDebito(NotaCreditoDebito notaCreditoDebito) {
        this.notaCreditoDebito = notaCreditoDebito;
    }

    public ArrayList<DocAsociado> getDocAsociados() {
        return docAsociados;
    }

    public void setDocAsociados(ArrayList<DocAsociado> docAsociados) {
        this.docAsociados = docAsociados;
    }

    public Double getTotalComprobante() {
        return totalComprobante;
    }

    public void setTotalComprobante(Double totalComprobante) {
        this.totalComprobante = totalComprobante;
    }

    public Double getTotalIVA10() {
        return totalIVA10;
    }

    public void setTotalIVA10(Double totalIVA10) {
        this.totalIVA10 = totalIVA10;
    }

    public Double getTotalIVA5() {
        return totalIVA5;
    }

    public void setTotalIVA5(Double totalIVA5) {
        this.totalIVA5 = totalIVA5;
    }

    public Double getTotalExcento() {
        return totalExcento;
    }

    public void setTotalExcento(Double totalExcento) {
        this.totalExcento = totalExcento;
    }

    public String getCdc() {
        return cdc;
    }

    public void setCdc(String cdc) {
        this.cdc = cdc;
    }

    public String getMotivoEvento() {
        return motivoEvento;
    }

    public void setMotivoEvento(String motivoEvento) {
        this.motivoEvento = motivoEvento;
    }
   
    
}
