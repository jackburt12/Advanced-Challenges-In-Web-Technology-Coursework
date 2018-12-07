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
        List<Team> teams = leagues.get(id).getTeamsList();
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

    public void deleteLeague(League league) {
        leagues.remove(league);
    }
    
    public void addTestData() {
        League l1 = new League();
        l1.setName("English Premier League");
        l1.setMaxTeams(20);
        l1.setId(0);

        List<Team> teams = new ArrayList<>();
        
        Team t1 = new Team();
        t1.setName("Manchester United F.C.");
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
        p1.setName("Anthony Martial");
        p1.setNumber(11);
        p1.setPosition(Player.Position.FORWARD);
        pL1.add(p1);
        Player p2 = new Player();
        p2.setName("Luke Shaw");
        p2.setNumber(3);
        p2.setPosition(Player.Position.DEFENDER);
        pL1.add(p2);
        t1.setPlayers(pL1);
        teams.add(t1);
        
        Team t2 = new Team();
        t2.setName("Chelsea F.C.");
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
        p3.setName("Eden Hazard");
        p3.setNumber(10);
        p3.setPosition(Player.Position.FORWARD);
        pL2.add(p3);
        Player p4 = new Player();
        p4.setName("N'golo Kante");
        p4.setNumber(7);
        p4.setPosition(Player.Position.MIDFIELDER);
        pL2.add(p4);
        t2.setPlayers(pL2);
        teams.add(t2);
        
        Team t3 = new Team();
        t3.setName("Liverpool F.C.");
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
        t4.setName("Tottenham Hotspur F.C.");
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
    }
}
