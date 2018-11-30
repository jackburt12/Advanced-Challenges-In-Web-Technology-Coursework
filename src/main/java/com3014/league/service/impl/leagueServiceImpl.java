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
    public void removeTeam(Team team) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTeam(Team team) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeMaxTeam(int maxTeam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLeague() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<League> getAllLeagues() {
        List<League> leagues = leagueDAO.getAllLeagues();
        return leagues;
    }

    @Override
    public List<Team> getAllTeams(int id) {
        List<Team> teams = leagueDAO.getAllTeams(id);
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
