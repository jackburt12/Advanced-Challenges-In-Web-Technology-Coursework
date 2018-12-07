/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.service.impl;

import com3014.league.model.League;
import com3014.league.model.Team;
import org.springframework.stereotype.Service;
import com3014.league.service.leagueService;
import com3014.league.dao.leagueDAO;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ahsan
 */
@Service
public class leagueServiceImpl implements leagueService {
    
    @Autowired
    leagueDAO leagueDAO;

    @Override
    public void removeTeam(int teamId, int leagueId) {
        leagueDAO.getAllTeams(leagueId).remove(teamId);
    }

    @Override
    public void addTeam(Team team, int leagueId) {
        leagueDAO.getAllLeagues().get(leagueId).setTeam(team);
    }

    @Override
    public void changeName(String name, int leagueId) {
        leagueDAO.getAllLeagues().get(leagueId).setName(name);
    }

    @Override
    public void changeMaxTeam(int maxTeam, int leagueId) {
        leagueDAO.getAllLeagues().get(leagueId).setMaxTeams(maxTeam);
    }

    @Override
    public void deleteLeague(League league) {
        leagueDAO.getAllLeagues().remove(league);
    }
    
    @Override
    public void deleteLeague(int leagueId) {
        List<League> leagues = leagueDAO.getAllLeagues();
        for (League l : leagues) {
            if (l.getId() == leagueId) {
                leagues.remove(l);
            }
        }
    }

    @Override
    public List<League> getAllLeagues() {
        return leagueDAO.getAllLeagues();
    }

    @Override
    public List<Team> getAllTeams(int leagueId) {
        List<Team> teams = leagueDAO.getAllTeams(leagueId);
        Collections.sort(teams);
        return teams;
    }
    
    @Override
    public Team getTeamByID(int id,int teamID) {
        List<Team> teams = leagueDAO.getAllTeams(id);
        Team team = new Team();
        for(Team t : teams ){
            if(teamID == t.getId()) return t;
        }
        return team;
    }
}
