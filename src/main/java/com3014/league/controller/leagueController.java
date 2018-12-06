/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.controller;

import com3014.league.service.fixtureService;
import com3014.league.model.Fixture;
import com3014.league.model.League;
import com3014.league.model.Team;
import com3014.league.service.leagueService;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author ahsan
 */
@Controller
@SessionAttributes("league")
@RequestMapping("/league")
public class leagueController {
    
    @Autowired
    leagueService leagueService;
    
    @Autowired
    fixtureService fixtureService;
    
    @RequestMapping(value = {"/all", "/"}, method = RequestMethod.GET)
    public String viewLeagues(ModelMap model, @ModelAttribute League league) {
        model.addAttribute("leagues", leagueService.getAllLeagues());
        
        return "league";
    }
    
    @RequestMapping(value = "/{leagueId}",  method = RequestMethod.GET)
    public String viewLeagues(@PathVariable int leagueId, ModelMap model, @ModelAttribute Team team) {
        League league = leagueService.getAllLeagues().get(leagueId);
        List<Team> teams = leagueService.getAllTeams(leagueId);
        List<Fixture> fixtures = fixtureService.getallFixtures();
        model.addAttribute("league", league) ;
        model.addAttribute("teams", teams) ;
        model.addAttribute("fixtures", fixtures) ;
        return "viewLeague";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String submitFixture(@PathVariable int id, ModelMap model, @RequestParam("home") int home,@RequestParam("away") int away,@RequestParam("homeScore") int homeScore,@RequestParam("awayScore") int awayScore) {
        Team homeTeam = leagueService.getTeamByID(id,home);
        Team awayTeam = leagueService.getTeamByID(id,away);
        if(homeScore > awayScore ) {
            homeTeam.setMatchPlayed(homeTeam.getMatchPlayed()+1);
            homeTeam.setWon(homeTeam.getWon()+1);
            homeTeam.setGoalDifference(homeTeam.getGoalDifference() + (homeScore-awayScore));
            homeTeam.setPoints(homeTeam.getPoints()+3);
            
            awayTeam.setMatchPlayed(awayTeam.getMatchPlayed()+1);
            awayTeam.setLoss(awayTeam.getLoss()+1);
            awayTeam.setGoalDifference(awayTeam.getGoalDifference() - (homeScore-awayScore));
        } 
        else if (awayScore > homeScore) {
            awayTeam.setMatchPlayed(awayTeam.getMatchPlayed()+1);
            awayTeam.setWon(awayTeam.getWon()+1);
            awayTeam.setGoalDifference(awayTeam.getGoalDifference() + (awayScore-homeScore));
            awayTeam.setPoints(awayTeam.getPoints()+3);
            
            homeTeam.setMatchPlayed(homeTeam.getMatchPlayed()+1);
            homeTeam.setLoss(homeTeam.getLoss()+1);
            homeTeam.setGoalDifference(homeTeam.getGoalDifference() - (awayScore-homeScore));
        } else {
            awayTeam.setMatchPlayed(awayTeam.getMatchPlayed()+1);
            awayTeam.setDraw(awayTeam.getDraw()+1);
            awayTeam.setPoints(awayTeam.getPoints()+1);
            
            homeTeam.setMatchPlayed(homeTeam.getMatchPlayed()+1);
            homeTeam.setDraw(homeTeam.getDraw()+1);
            homeTeam.setPoints(homeTeam.getPoints()+1);
        }
        Fixture fixture = new Fixture();
        fixture.setHome(homeTeam.getName());
        fixture.setAway(awayTeam.getName());
        fixture.setHomeScore(homeScore);
        fixture.setAwayScore(awayScore);
        fixture.setLocation(leagueService.getAllTeams(id).get(home).getLocation());
        fixtureService.fixtureAdd(fixture);
        return "redirect:/league/{id}";
    }
    
    @RequestMapping(value = "/{leagueId}/team/{teamId}",  method = RequestMethod.GET)
    public String viewTeams(
            @PathVariable int leagueId, @PathVariable int teamId, ModelMap model, @ModelAttribute Team team
    ) {
        Team thisTeam = leagueService.getTeamByID(leagueId, teamId);
        model.addAttribute("team", thisTeam);
        return "viewTeam";
    }
    
    @ModelAttribute("league")
    public League populateLeague() {
        return new League();
    }
    
    @ModelAttribute("team")
    public Team populateTeam() {
        return new Team();
    }
    
    public static List<Team> sortByPoints(List<Team> teams) {
        Collections.sort(teams);
        return teams;
    }
}
