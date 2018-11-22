/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.model;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author ahsan
 */
@Component
public class League {
    private String Name;
    private int maxTeam;
    private List<Team> teams;

    public String getName() {
        return Name;
    }

    public int getMaxTeam() {
        return maxTeam;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setMaxTeam(int maxTeam) {
        this.maxTeam = maxTeam;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
}
