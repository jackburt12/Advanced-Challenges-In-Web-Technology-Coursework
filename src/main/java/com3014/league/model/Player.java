/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author ahsan
 */
@Component
public class Player {
    private String name;
    private int number;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return this.number;
    }
}
