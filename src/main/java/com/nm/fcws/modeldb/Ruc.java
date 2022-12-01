/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.modeldb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 *
 * @author BlackSpider
 */
@Entity
@Table(name="rucs"
        ,indexes = {
            @Index(name="ruc_index", columnList="ruc", unique=true)
        }
)
public class Ruc implements Serializable {
    
    @Id
    private Long rucid;
    @Column(nullable=false)
    private String ruc;
    private String dv;
    @Column(columnDefinition="varchar(500)")
    private String RazonSocial;

    public Long getRucid() {
        return rucid;
    }

    public void setRucid(Long rucid) {
        this.rucid = rucid;
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

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

}
