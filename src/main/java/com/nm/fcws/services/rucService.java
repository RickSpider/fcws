/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.services;

import com.nm.fcws.modeldb.Ruc;
import com.nm.fcws.repo.RucRepo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
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
public class rucService {

    private static Logger log = LoggerFactory.getLogger(rucService.class);
    
    @Autowired
    private RucRepo rucRepo;
    
    @Autowired
    private GuardarVolumenServicio guardarServicio;

    @Async
    public void cargarRuc() throws MalformedURLException, IOException {
        log.info("Borrando Registros de Ruc");
        rucRepo.deleteAllNative();

        int c = 1;
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {

            URL url = new URL("http://34.209.151.210:8080/backups/ruc/ruc"+i+".txt");
            URLConnection conexion = url.openConnection();
            conexion.connect();

            InputStream is = conexion.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linea;

            log.info("Iniciando carga asincrona");

            List<Ruc> lruc = new ArrayList<Ruc>();

            int d=1;
            
            while ((linea = br.readLine()) != null) {

                

                String[] ls = linea.split("\\|");

                Ruc r = new Ruc();
                r.setRucid(new Long(c));
                r.setRuc(ls[0]);
                r.setDv(ls[2]);
                r.setRazonSocial(ls[1]);

                lruc.add(r);

                //rucRepo.save(r);
                
                if (c % 5000 == 0) {

                    //log.info(c + "");
                    
                    guardarServicio.guardar(lruc, start, i, d);
                    
                    d++;
                    
                    lruc = new ArrayList<Ruc>();

                }
                
                c++;

                //sb.append(linea);
            }
            
             if (lruc.size()> 0) {

                    //log.info(c + "");
                    
                    guardarServicio.guardar(lruc, start, i, d);
                    
                    d++;
                    
                    lruc = new ArrayList<Ruc>();

                }
            
           
           /* log.info("iniciando guardado "+lruc.size()+" "+i);
        
            rucRepo.saveAll(lruc);
            long end = System.currentTimeMillis() - start;
            log.info("Fin Carga Asincrona ejecucion "+i+" " + end);*/
            
            //guardarServicio.saveAllRuc(lruc, i,start);
            
            br.close();
            

        }

    }

   

}
