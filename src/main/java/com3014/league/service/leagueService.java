package com3014.league.service;

import com3014.league.model.Fixture;
import com3014.league.model.League;
import com3014.league.model.Player;
import com3014.league.model.Team;
import java.util.List;

public interface leagueService {
    public List<League> getAllLeagues();
    public League getLeagueByID(int leagueId);
    public void addLeague(League league);
    public Team getTeamByID(int id,int teamID);
    public void deleteTeam(int teamId, int leagueId);
    public void addTeam(Team team, int leagueId);
    public void updateName(String name, int leagueId);
    public void updateMaxTeam(int maxTeam, int leagueId);
    public List<Team> getAllTeams(int id);
    public Fixture getFixtureByID(int fixtureId, int leagueId);
    public void addFixture(Fixture fixture, int leagueId);
    public void deleteFixture(int fixtureId, int leagueId);
    public List<Fixture> getAllFixtures(int leagueId);
    public Player getPlayerByNumber(int playerNum, int teamId, int leagueId);
    public void addPlayer(Player player, int teamId, int leagueId);
    public List<Player> getAllPlayers(int teamId, int leagueId);
    public boolean containsLeague(int leagueId);
}
