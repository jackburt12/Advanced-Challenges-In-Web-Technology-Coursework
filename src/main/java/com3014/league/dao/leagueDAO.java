package com3014.league.dao;

import com3014.league.model.League;
import com3014.league.model.Player;
import com3014.league.model.Team;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ahsan, matt
 */
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
    
    public League getLeague(int leagueId) {
        League league = new League();
        for (League l : leagues) {
            if (l.getId() == leagueId) {
                league = l;
            }
        }
        return league;
    }
    
    public void updateLeague(League league1, League league2) {
        leagues.set(leagues.indexOf(league1), league2);
    }
    
    public void updateLeague(int leagueId, League league2) {
        leagues.set(leagues.indexOf(getLeague(leagueId)), league2);
    }

    public void deleteLeague(League league) {
        leagues.remove(league);
    }
    
    public void deleteLeague(int leagueId) {
        League league = new League();
        for (League l: leagues) {
            if (l.getId() == leagueId) {
                league = l;
            }
        }
        leagues.remove(league);
    }
    
    public Team getTeamByID(int teamId, int leagueId) {
        return getLeague(leagueId).getTeamByID(teamId);
    }
    
    public void addTeam(Team team, int leagueId) {
        getLeague(leagueId).getTeamsList().add(team);
    }
    
    public void updateTeam(int teamId, Team team, int leagueId) {
        getLeague(leagueId).getTeamsList().set(getLeague(leagueId).getTeamsList().indexOf(getTeamByID(teamId, leagueId)), team);
    }
    
    public void deleteTeam(int teamId, int leagueId) {
        getLeague(leagueId).getTeamsList().remove(getTeamByID(teamId, leagueId));
    }
    public void addTestData() {
        League l1 = new League();
        l1.setName("Barclays Sunday League");
        l1.setMaxTeams(4);
        l1.setId(0);
        List<Team> teams = new ArrayList<>();
        
        Team t1 = new Team();
        t1.setName("Mans United");
        t1.setMatchPlayed(0);
        t1.setWon(0);
        t1.setDraw(0);
        t1.setLoss(0);
        t1.setGoalDifference(0);
        t1.setPoints(0);
        t1.setId(0);
        t1.setLocation("Old Trafford");
        List<Player> pL1 = new ArrayList<>();
        Player p1 = new Player();
        p1.setName("testplayer");
        p1.setNumber(7);
        pL1.add(p1);
        Player p2 = new Player();
        p2.setName("Name Surname");
        p2.setNumber(69);
        pL1.add(p2);
        t1.setPlayers(pL1);
        teams.add(t1);
        
        Team t2 = new Team();
        t2.setName("Chill Sea");
        t2.setMatchPlayed(0);
        t2.setWon(0);
        t2.setDraw(0);
        t2.setLoss(0);
        t2.setGoalDifference(0);
        t2.setPoints(0);
        t2.setId(1);
        t2.setLocation("Stamford Bridge");
        List<Player> pL2 = new ArrayList<>();
        Player p3 = new Player();
        p3.setName("testplayer");
        p3.setNumber(7);
        pL2.add(p3);
        Player p4 = new Player();
        p4.setName("Name Surname");
        p4.setNumber(69);
        pL2.add(p4);
        t1.setPlayers(pL2);
        teams.add(t2);
        
        Team t3 = new Team();
        t3.setName("Livers pools");
        t3.setMatchPlayed(0);
        t3.setWon(0);
        t3.setDraw(0);
        t3.setLoss(0);
        t3.setGoalDifference(0);
        t3.setPoints(0);
        t3.setId(2);
        t3.setLocation("Anfield");
        teams.add(t3);
        
        Team t4 = new Team();
        t4.setName("Tots Hams");
        t4.setMatchPlayed(0);
        t4.setWon(0);
        t4.setDraw(0);
        t4.setLoss(0);
        t4.setGoalDifference(0);
        t4.setPoints(0);
        t4.setId(3);
        t4.setLocation("White Hart Lane");
        teams.add(t4);
        l1.setTeamsList(teams);
        
        leagues.add(l1);
        
        League l2 = new League();
        l2.setName("My Test League");
        l2.setMaxTeams(2);
        l2.setId(1);
        List<Team> teams2 = new ArrayList<>();
        
        Team t5 = new Team();
        t5.setName("Test Team One");
        t5.setMatchPlayed(0);
        t5.setWon(0);
        t5.setDraw(0);
        t5.setLoss(0);
        t5.setGoalDifference(0);
        t5.setPoints(0);
        t5.setId(0);
        t5.setLocation("Old Trafford");
        List<Player> pL5 = new ArrayList<>();
        Player p9 = new Player();
        p9.setName("testplayer");
        p9.setNumber(7);
        pL5.add(p9);
        Player p10 = new Player();
        p10.setName("Name Surname");
        p10.setNumber(69);
        pL5.add(p10);
        t5.setPlayers(pL5);
        teams2.add(t5);
        
        Team t6 = new Team();
        t6.setName("Test Team Two");
        t6.setMatchPlayed(0);
        t6.setWon(0);
        t6.setDraw(0);
        t6.setLoss(0);
        t6.setGoalDifference(0);
        t6.setPoints(0);
        t6.setId(1);
        t6.setLocation("Stamford Bridge");
        List<Player> pL6 = new ArrayList<>();
        Player p11 = new Player();
        p11.setName("testplayer");
        p11.setNumber(7);
        pL6.add(p11);
        Player p12 = new Player();
        p12.setName("Name Surname");
        p12.setNumber(69);
        pL6.add(p12);
        t6.setPlayers(pL6);
        teams2.add(t6);
        
        l2.setTeamsList(teams2);
        leagues.add(l2);
    }
}
