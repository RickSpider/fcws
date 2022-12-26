/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 *
 * @author BlackSpider
 */
@Entity
@Table(name="actividadeseconomicas"
        ,indexes = {
            @Index(name="codigo_index", columnList="codigo", unique=true)
        }
)
public class ActividadEconomica implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long actividadeconomicaid;
    
    @Column(columnDefinition = "boolean default false")
    private boolean principal;
    
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

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
    
    
}
