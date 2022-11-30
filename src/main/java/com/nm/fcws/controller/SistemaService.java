/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import com.nm.fcws.modeldb.Ruc;
import com.nm.fcws.repo.RucRepo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author blackspider
 */

@Service
public class SistemaService {
    
    @Autowired
    private RucRepo rucRepo;
    
    private static Logger log= LoggerFactory.getLogger(SistemaService.class);
    
     @Async
    public void cargarRuc() throws MalformedURLException, IOException{
        
        URL url = new URL("http://34.209.151.210:8080/backups/ruc/ruc1.txt");
        URLConnection conexion = url.openConnection();
        conexion.connect();
        
        InputStream is = conexion.getInputStream();
        BufferedReader br = new BufferedReader (new InputStreamReader(is));
        
        StringBuffer sb = new StringBuffer();
        
        String linea;
        int c = 1;
        
        log.info("Iniciando carga asincrona");
        
        while ((linea = br.readLine()) != null){
            
            log.info(c+"");
            
            String[] ls = linea.split("\\|");
            
           
            
            Ruc r = new Ruc();
            r.setRucid(new Long(c));
            r.setRuc(ls[0]);
            r.setDv(ls[2]);
            r.setRazonSocial(ls[1]);
            
            rucRepo.save(r);
            
            c++;
        
            //sb.append(linea);
            
        }
        
        log.info("Fin Carga Asincrona");
        
       // System.out.println(sb.toString());
        
    
    
    }
}
