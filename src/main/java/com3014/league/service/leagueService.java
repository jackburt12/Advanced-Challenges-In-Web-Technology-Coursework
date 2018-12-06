/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.service;

import com3014.league.model.League;
import com3014.league.model.Team;
import java.util.List;

/**
 *
 * @author ahsan
 */
public interface leagueService {
    public void removeTeam(int teamId, int leagueId);
    public void addTeam(Team team, int leagueId);
    public void changeName(String name, int leagueId);
    public void changeMaxTeam(int maxTeam, int leagueId);
    public void deleteLeague(League league);
    public void deleteLeague(int leagueId);
    public List<League> getAllLeagues();
    public Team getTeamByID(int id,int teamID);
    public List<Team> getAllTeams(int id);
}
