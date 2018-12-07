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
 * model class that encapsulates the leagues
 */
@Component
public class League {
    private int id;
    private String Name;
    private int maxTeams;
    private List<Team> teamsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    public List<Team> getTeamsList() {
        return teamsList;
    }

    public void setTeamsList(List<Team> teamsList) {
        this.teamsList = teamsList;
    }
    
    public Team getTeamByID(int teamId) {
        Team team = null;
        for (Team t: teamsList) {
            if (t.getId() == teamId) {
                team = t;
            }
        }
        return team;
    }
    
    public void setTeam(Team team) {
        teamsList.add(team);
    }
    
    public void deleteTeam(int teamId) {
        teamsList.remove(getTeamByID(teamId));
    }
    
    public void updateTeam(int teamId, Team team) {
        teamsList.set(teamsList.indexOf(getTeamByID(teamId)), team);
    }
}
