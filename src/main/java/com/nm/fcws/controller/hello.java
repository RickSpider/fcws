/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.fcws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author BlackSpider
 */

@RestController
public class hello {
    
    @GetMapping(value = "/hello",produces ="application/json")
    public String sayHello(){
        
        return "hello lo perro";
        
    }
    
}
