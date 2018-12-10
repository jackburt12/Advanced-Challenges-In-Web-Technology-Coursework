/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.service;

import com3014.league.model.Fixture;
import com3014.league.model.League;
import com3014.league.model.Player;
import com3014.league.model.Team;
import java.util.List;

/**
 *
 * @author ahsan
 * @author matt
 */
public interface leagueService {
    public List<League> getAllLeagues();
    public League getLeagueByID(int leagueId);
    public void addLeague(League league);
    public void updateLeague(int leagueId, League league);
    public void deleteLeague(League league);
    public void deleteLeague(int leagueId);
    public Team getTeamByID(int id,int teamID);
    public void deleteTeam(int teamId, int leagueId);
    public void addTeam(Team team, int leagueId);
    public void updateTeam(int teamId, Team team, int leagueId);
    public void updateName(String name, int leagueId);
    public void updateMaxTeam(int maxTeam, int leagueId);
    public List<Team> getAllTeams(int id);
    public Fixture getFixtureByID(int fixtureId, int leagueId);
    public void addFixture(Fixture fixture, int leagueId);
    public void updateFixture(int fixtureId, Fixture fixture, int leagueId);
    public void deleteFixture(int fixtureId, int leagueId);
    public List<Fixture> getAllFixtures(int leagueId);
    public Player getPlayerByNumber(int playerNum, int teamId, int leagueId);
    public void addPlayer(Player player, int teamId, int leagueId);
    public void updatePlayer(int playerNum, Player player, int teamId, int leagueId);
    public void deletePlayer(int playerNum, int teamId, int leagueId);
    public List<Player> getAllPlayers(int teamId, int leagueId);
    public boolean containsLeague(int leagueId);
}
