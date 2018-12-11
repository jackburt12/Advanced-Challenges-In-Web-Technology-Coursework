package com3014.league.dao;

import com3014.league.model.Fixture;
import com3014.league.model.League;
import com3014.league.model.Player;
import com3014.league.model.Team;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class leagueDAO {
    
    private List<League> leagues = new ArrayList<>();
    
    /**
     * creates dummy data for a league model
     * @return All the leagues present
     */
    public List<League> getAllLeagues() {
        if (leagues.isEmpty()) {
            addTestData();
        }
        return leagues;
    }
    
    /**
     * 
     * @param id
     * @return all team within a league
     */
    public List<Team> getAllTeams(int id) {
        List<Team> teams = getLeague(id).getTeamsList();
        return teams;
    }
    
    public void addLeague(League league) {
        leagues.add(league);
    }
    
    /**
     * 
     * @param leagueId
     * @return a specific league by id
     */
    public League getLeague(int leagueId) {
        League league = new League();
        for (League l : leagues) {
            if (l.getId() == leagueId) {
                league = l;
            }
        }
        return league;
    }
    
    public Team getTeamByID(int teamId, int leagueId) {
        return getLeague(leagueId).getTeamByID(teamId);
    }
    
    public void addTeam(Team team, int leagueId) {
        getLeague(leagueId).getTeamsList().add(team);
    }
    
    public void deleteTeam(int teamId, int leagueId) {
        getLeague(leagueId).getTeamsList().remove(getTeamByID(teamId, leagueId));
    }

    public void addFixture(Fixture fixture, int leagueId) {
        getLeague(leagueId).addFixture(fixture);
    }

    public void deleteFixture(int fixtureId, int leagueId) {
        getLeague(leagueId).deleteFixture(fixtureId);
    }

    public List<Fixture> getAllFixtures(int leagueId) {
        return getLeague(leagueId).getFixturesList();
    }
    public void addTestData() {
        League l1 = new League();
        l1.setName("English Premier League");
        l1.setMaxTeams(20);
        l1.setId(0);

        List<Team> teams = new ArrayList<>();

        Team t1 = new Team(0, "Manchester United", "Old Trafford");
        List<Player> pL1 = new ArrayList<>();
        Player p1 = new Player("Anthony Martial", 11, Player.Position.FORWARD);
        Player p2 = new Player("Luke Shaw", 3, Player.Position.DEFENDER);
        pL1.add(p1);
        pL1.add(p2);
        t1.setPlayers(pL1);
        teams.add(t1);
        
        Team t2 = new Team(1, "Chelsea", "Stamford Bridge");
        List<Player> pL2 = new ArrayList<>();
        Player p3 = new Player("Eden Hazard", 10, Player.Position.FORWARD);
        Player p4 = new Player("N'golo Kante", 7, Player.Position.MIDFIELDER);
        pL2.add(p3);
        pL2.add(p4);
        t2.setPlayers(pL2);
        teams.add(t2);
        
        Team t3 = new Team(2, "Liverpool", "Anfield");
        List<Player> pL3 = new ArrayList<>();
        Player p5 = new Player("Mohamed Salah", 11, Player.Position.FORWARD);
        pL3.add(p5);
        t3.setPlayers(pL3);
        teams.add(t3);
        
        Team t4 = new Team(3, "Tottenham Hotspur", "Wembley Stadium");
        List<Player> pL4 = new ArrayList<>();
        Player p6 = new Player("Dele Alli", 20, Player.Position.MIDFIELDER);
        pL4.add(p6);
        t4.setPlayers(pL4);
        teams.add(t4);

        l1.setTeamsList(teams);
        leagues.add(l1);

    }

    public Player getPlayerByNumber(int playerNum, int teamId, int leagueId) {
        return getLeague(leagueId).getTeamByID(teamId).getPlayerByNumber(playerNum);
    }

    public void addPlayer(Player player, int teamId, int leagueId) {
        getLeague(leagueId).getTeamByID(teamId).addPlayer(player);
    }

    public void deletePlayer(int playerNum, int teamId, int leagueId) {
        getLeague(leagueId).getTeamByID(teamId).deletePlayer(playerNum);
    }

    public List<Player> getAllPlayers(int teamId, int leagueId) {
        return getLeague(leagueId).getTeamByID(teamId).getPlayers();
    }
    
    public Fixture getFixtureByID(int fixtureId, int leagueId) {
        return getLeague(leagueId).getFixtureByID(fixtureId);
    }
}
