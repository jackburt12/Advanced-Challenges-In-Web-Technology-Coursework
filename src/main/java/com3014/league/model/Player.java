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
 * model class that encapsulates the player
 */
@Component
public class Player {
    public enum Position {GOALKEEPER, DEFENDER, MIDFIELDER, FORWARD};

    private String name;
    private int number;
    private Position position;

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
