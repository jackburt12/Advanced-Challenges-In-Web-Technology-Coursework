/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.service.impl;

import com3014.league.model.Fixture;
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
    public void deleteTeam(int teamId, int leagueId) {
        leagueDAO.deleteTeam(teamId, leagueId);
    }

    @Override
    public void addTeam(Team team, int leagueId) {
        leagueDAO.getAllLeagues().get(leagueId).setTeam(team);
    }

    @Override
    public void updateName(String name, int leagueId) {
        leagueDAO.getAllLeagues().get(leagueId).setName(name);
    }

    @Override
    public void updateMaxTeam(int maxTeam, int leagueId) {
        leagueDAO.getAllLeagues().get(leagueId).setMaxTeams(maxTeam);
    }

    @Override
    public void deleteLeague(League league) {
        leagueDAO.deleteLeague(league);
    }
    
    @Override
    public void deleteLeague(int leagueId) {
        leagueDAO.deleteLeague(leagueId);
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
    public Team getTeamByID(int leagueId,int teamId) {
        return leagueDAO.getTeamByID(leagueId, teamId);
    }

    @Override
    public League getLeagueByID(int leagueId) {
        return leagueDAO.getLeague(leagueId);
    }

    @Override
    public void updateLeague(int leagueId, League league) {
        leagueDAO.updateLeague(leagueId, league);
    }

    @Override
    public void addLeague(League league) {
        leagueDAO.addLeague(league);
    }

    @Override
    public void updateTeam(int teamId, Team team, int leagueId) {
        leagueDAO.updateTeam(teamId, team, leagueId);
    }

    @Override
    public Fixture getFixtureByID(int fixtureId, int leagueId) {
        return leagueDAO.getFixtureByID(fixtureId, leagueId);
    }

    @Override
    public void addFixture(Fixture fixture, int leagueId) {
        leagueDAO.addFixture(fixture, leagueId);
    }

    @Override
    public void updateFixture(int fixtureId, Fixture fixture, int leagueId) {
        leagueDAO.updateFixture(fixtureId, fixture, leagueId);
    }

    @Override
    public void deleteFixture(int fixtureId, int leagueId) {
        leagueDAO.deleteFixture(fixtureId, leagueId);
    }

    @Override
    public List<Fixture> getAllFixtures(int leagueId) {
        return leagueDAO.getAllFixtures(leagueId);
    }


}
