/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.repo;

import com.nm.fcws.modeldb.Contribuyente;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author BlackSpider
 */
public interface ContribuyenteRepo extends CrudRepository <Contribuyente, Long>{ 
    
    public List<Contribuyente> findBySoloLoteAndHabilitado(boolean soloLote, boolean habilitado);
    public List<Contribuyente> findByHabilitado(boolean habilitado);
    
}
