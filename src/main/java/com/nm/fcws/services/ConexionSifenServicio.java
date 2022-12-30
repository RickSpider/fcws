/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.services;

import com.nm.fcws.modeldb.Contribuyente;
import com.roshka.sifen.core.SifenConfig;
import org.springframework.stereotype.Service;

/**
 *
 * @author BlackSpider
 */
@Service
public class ConexionSifenServicio {
    
       public SifenConfig getSifenConfig(Contribuyente contribuyente) {

        SifenConfig config = null;

        if (contribuyente.getAmbiente().compareTo("DEV") == 0) {

            config = new SifenConfig(
                    SifenConfig.TipoAmbiente.DEV,
                    "0001",
                    "ABCD0000000000000000000000000000",
                    SifenConfig.TipoCertificadoCliente.PFX,
                    contribuyente.getPathkey(),
                    contribuyente.getPasskey()
            );

        }

        if (contribuyente.getAmbiente().compareTo("PROD") == 0) {

            config = new SifenConfig(
                    SifenConfig.TipoAmbiente.PROD,
                    contribuyente.getCscid(),
                    contribuyente.getCsc(),
                    SifenConfig.TipoCertificadoCliente.PFX,
                    contribuyente.getPathkey(),
                    contribuyente.getPasskey()
            );

        }

        return config;
    }
}
