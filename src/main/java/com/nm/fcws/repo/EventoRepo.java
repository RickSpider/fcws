/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.repo;

import com.nm.fcws.modeldb.Evento;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author blackspider
 */
public interface EventoRepo extends CrudRepository<Evento, Long> {
    
    public List<Evento> findByEnviado (boolean enviado);
    
}
