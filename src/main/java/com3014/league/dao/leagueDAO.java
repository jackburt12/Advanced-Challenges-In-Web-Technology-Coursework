/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.dao;

import com3014.league.model.League;
import com3014.league.model.Team;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ahsan
 */
@Repository
public class leagueDAO {
    
    public List<League> getAllLeagues() {
        League l1 = new League();
        l1.setName("Barclays Sunday league");
        l1.setMaxTeam(4);
        List<Team> teams = new ArrayList<Team>();
        Team t1 = new Team();
        t1.setName("Mans United");
        t1.setMatchPlayed(0);
        t1.setWon(0);
        t1.setDraw(0);
        t1.setLoss(0);
        t1.setGoalDifference(0);
        t1.setPoints(0);
        teams.add(t1);
        
        Team t2 = new Team();
        t2.setName("Chill Sea");
        t2.setMatchPlayed(0);
        t2.setWon(0);
        t2.setDraw(0);
        t2.setLoss(0);
        t2.setGoalDifference(0);
        t2.setPoints(0);
        teams.add(t2);
        
        Team t3 = new Team();
        t3.setName("Livers pools");
        t3.setMatchPlayed(0);
        t3.setWon(0);
        t3.setDraw(0);
        t3.setLoss(0);
        t3.setGoalDifference(0);
        t3.setPoints(0);
        teams.add(t3);
        
        Team t4 = new Team();
        t4.setName("Arse Anal");
        t4.setMatchPlayed(0);
        t4.setWon(0);
        t4.setDraw(0);
        t4.setLoss(0);
        t4.setGoalDifference(0);
        t4.setPoints(0);
        teams.add(t4);
        l1.setTeams(teams);
        
        List<League> leagues = new ArrayList<League>();
        leagues.add(l1);
        
        return leagues;
    }
    
}
