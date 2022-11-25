/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BlackSpider
 */
@Entity
@Table(name="actividadeseconomicas")
public class ActividadEconomica implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long actividadeconomicaid;
    private String codigo;
    private String descripcion;

    public Long getActividadeconomicaid() {
        return actividadeconomicaid;
    }

    public void setActividadeconomicaid(Long actividadeconomicaid) {
        this.actividadeconomicaid = actividadeconomicaid;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
