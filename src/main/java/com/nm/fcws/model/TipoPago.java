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
public class TipoPago {
    
    private Long tipoPagoCodigo;
    private double monto;
    private String modeda;
    
    //seccion tarjeta credito/debito
    private Long denominacionTarjeta;
    private Long formaProcesamiento;
    
    //Campos no Obligatorios
    private String procesadora;
    private String ProcesadoraRuc;
    private Integer procesadoraDV;
   
    private Integer codigoAutorizacion;
    private String tarjetaNombre;
    private Integer tarjetaNro;
 
    public Long getTipoPagoCodigo() {
        return tipoPagoCodigo;
    }

    /**
     * Posibles valores, si no se carga valor se toma como 1
     * 
     * 1:Efectivo
     * 2:Cheque
     * 3:Tarjeta de credito
     * 4:Tarjeta de debito
     * 5:Transferencia
     * 6:Giro
     * 7:Billetera electronica
     * 8:Tarjeta empresarial
     * 9:Vale
     * 10:Retencion
     * 11:Pago por anticipo
     * 12:Valor fiscal
     * 13:Valor comercial
     * 14:Compensacion
     * 15:Permuta
     * 16:Pago bancario
     * 17:Pago Movil
     * 18:Donacion
     * 19:Promocion
     * 20:Consumo Interno
     * 21:Pago Electronico
     * 
     * 
     * @param tipoPagoCodigo 
     */
    public void setTipoPagoCodigo(Long tipoPagoCodigo) {
        this.tipoPagoCodigo = tipoPagoCodigo;
    }
    
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getModeda() {
        return modeda;
    }

    public void setModeda(String modeda) {
        this.modeda = modeda;
    }

    public String getProcesadora() {
        return procesadora;
    }

    public void setProcesadora(String procesadora) {
        this.procesadora = procesadora;
    }

    public String getProcesadoraRuc() {
        return ProcesadoraRuc;
    }

    public void setProcesadoraRuc(String ProcesadoraRuc) {
        this.ProcesadoraRuc = ProcesadoraRuc;
    }

    public Integer getProcesadoraDV() {
        return procesadoraDV;
    }

    public void setProcesadoraDV(Integer procesadoraDV) {
        this.procesadoraDV = procesadoraDV;
    }

    public Long getFormaProcesamiento() {
        return formaProcesamiento;
    }

    /**
     * 
     * 1:POS
     * 2:Pago Electronico (Ej. Compras por internet)
     * 9:Otro
     * 
     * @param formaProcesamiento 
     */
    public void setFormaProcesamiento(Long formaProcesamiento) {
        this.formaProcesamiento = formaProcesamiento;
    }

    public Integer getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public void setCodigoAutorizacion(Integer codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }

    public String getTarjetaNombre() {
        return tarjetaNombre;
    }

    public void setTarjetaNombre(String tarjetaNombre) {
        this.tarjetaNombre = tarjetaNombre;
    }

    public Integer getTarjetaNro() {
        return tarjetaNro;
    }

    /**
     * 
     * Cuatro ultimos digitos de la tarjeta
     * 
     * @param tarjetaNro 
     */
    public void setTarjetaNro(Integer tarjetaNro) {
        this.tarjetaNro = tarjetaNro;
    }

    public Long getDenominacionTarjeta() {
        return denominacionTarjeta;
    }

    /**
     * 
     * 1:Visa
     * 2:Mastercard
     * 3:American Express
     * 4:Maestro
     * 5:Panal
     * 6:Cabal
     * 99:Otro
     * 
     * @param denominacionTarjeta 
     */
    public void setDenominacionTarjeta(Long denominacionTarjeta) {
        this.denominacionTarjeta = denominacionTarjeta;
    }

 
    
    
    
}
