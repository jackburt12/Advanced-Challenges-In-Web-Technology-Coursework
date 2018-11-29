/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping("/{id}")
    public String viewProducts(@PathVariable int id, ModelMap model, @ModelAttribute Team team) {
        List<Team> teams = leagueService.getAllTeams(id);
        model.addAttribute("leagueid", id) ;
        model.addAttribute("teams", teams) ;
        return "viewleague";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody
    String getForm() {
 
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        return result;
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
