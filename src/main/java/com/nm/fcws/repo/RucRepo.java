/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.repo;

import com.nm.fcws.modeldb.Distrito;
import com.nm.fcws.modeldb.Ruc;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author blackspider
 */
public interface RucRepo extends CrudRepository<Ruc,Long> {
   
    @Modifying
    @Transactional
    @Query( value ="DELETE FROM rucs", nativeQuery=true)
    void deleteAllNative();
    
    Ruc findByRuc(String ruc);
    
}
