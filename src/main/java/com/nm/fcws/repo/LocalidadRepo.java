/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.repo;

import com.nm.fcws.modeldb.Localidad;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author BlackSpider
 */
public interface LocalidadRepo extends CrudRepository<Localidad, Long> {
    
    public Localidad findByCodigoSifen(Long codigoSifen);
    
}
