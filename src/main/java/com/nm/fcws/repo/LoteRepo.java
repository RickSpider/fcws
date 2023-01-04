/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.repo;

import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.modeldb.Lote;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author blackspider
 */
public interface LoteRepo extends CrudRepository<Lote, Long>{
    
    public Lote findByNro(String nro);
    
   
    public List<Lote> findByEstadoNotContainingOrEstadoIsNull (String estado);
    
}
