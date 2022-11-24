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
public class ComprobanteDetalle {
 
    private String itemCodigo;
    private String itemDescripcion;
    private String itemUndMedida;
    private int cantidad;
    private double precioUnitario;
    
    /*
        1= �Gravado IVA�
        2= �Exonerado (Art. 83- Ley
        125/91)�
        3= �Exento�
        4= �Gravado parcial (Grav-
        Exento)�
    
    */
    private int afectacionTributaria;
    private int proporcionIVA;
    private int tasaIVA;

    public int getAfectacionTributaria() {
        return afectacionTributaria;
    }

    public void setAfectacionTributaria(int afectacionTributaria) {
        this.afectacionTributaria = afectacionTributaria;
    }

    public String getItemCodigo() {
        return itemCodigo;
    }

    public void setItemCodigo(String itemCodigo) {
        this.itemCodigo = itemCodigo;
    }

    public String getItemDescripcion() {
        return itemDescripcion;
    }

    public void setItemDescripcion(String itemDescripcion) {
        this.itemDescripcion = itemDescripcion;
    }

    public String getItemUndMedida() {
        return itemUndMedida;
    }

    public void setItemUndMedida(String itemUndMedida) {
        this.itemUndMedida = itemUndMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getProporcionIVA() {
        return proporcionIVA;
    }

    public void setProporcionIVA(int proporcionIVA) {
        this.proporcionIVA = proporcionIVA;
    }

    public int getTasaIVA() {
        return tasaIVA;
    }

    public void setTasaIVA(int tasaIVA) {
        this.tasaIVA = tasaIVA;
    }

   
    
}
