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
    
    @RequestMapping("/all")
    public String viewProducts(ModelMap model, @ModelAttribute League league) {
        model.addAttribute("leagues", leagueService.getAllLeagues());
        
        return "league";
    }
    
    @RequestMapping("/test")
    public String viewProducts(ModelMap model /*@ModelAttribute Team team*/) {
        //model.addAttribute("leagues", productService.getAllProducts());
        return "index";
    }
    
    @RequestMapping(value = "/{id}",  method = RequestMethod.GET)
    public String viewProducts(@PathVariable int id, ModelMap model, @ModelAttribute Team team) {
        List<Team> teams = leagueService.getAllTeams(id);
        List<Fixture> fixtures = fixtureService.getallFixtures();
        model.addAttribute("leagueid", id) ;
        model.addAttribute("teams", teams) ;
        model.addAttribute("fixtures", fixtures) ;
        return "viewleague";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String submitFixture(@PathVariable int id, ModelMap model, @RequestParam("home") int home,@RequestParam("away") int away,@RequestParam("homeScore") int homeScore,@RequestParam("awayScore") int awayScore) {
        /*List<Team> teams = leagueService.getAllTeams(id);
        List<Fixture> fixtures = fixtureService.getallFixtures();*/
        Fixture fixture = new Fixture();
        fixture.setHome(leagueService.getAllTeams(id).get(home).getName());
        fixture.setAway(leagueService.getAllTeams(id).get(away).getName());
        fixture.setHomeScore(homeScore);
        fixture.setAwayScore(awayScore);
        fixture.setLocation(leagueService.getAllTeams(id).get(home).getLocation());
        fixtureService.fixtureAdd(fixture);
        /*model.addAttribute("leagueid", id) ;
        model.addAttribute("teams", teams) ;
        model.addAttribute("fixtures", fixtures) ;*/
        return "redirect:/league/{id}";
    }
    
    @ModelAttribute("league")
    public League populateLeague() {
        return new League();
    }
    
    @ModelAttribute("team")
    public Team populateTeam() {
        return new Team();
    }

}
