/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.repo;

import com.nm.fcws.modeldb.ComprobanteElectronico;
import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.modeldb.TipoComprobanteElectronico;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author BlackSpider
 */
public interface ComprobanteElectronicoRepo extends CrudRepository<ComprobanteElectronico, Long>{
    
    public ComprobanteElectronico findByCdc(String cdc);
    
    //List<ComprobanteElectronico> findByLoteAndEnviadoLoteAndContribuyente (boolean lote, boolean enviadoLote, Contribuyente contribuyente);
    
    public List<ComprobanteElectronico> findByEnvioPorLoteAndContribuyenteAndTipoComprobanteElectronicoAndEnviadoLote (boolean envioPorLote, Contribuyente contribuyente, TipoComprobanteElectronico tipoComprobateElectronico, boolean enviadoLote);
    
    public ComprobanteElectronico findByCdcAndEstado(String cdc, String estado);
    
    public List<ComprobanteElectronico> findByContribuyenteAndEstadoNotContaining (Contribuyente contribuyente, String estado);
    
}
