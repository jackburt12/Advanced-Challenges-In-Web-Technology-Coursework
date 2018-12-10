package com3014.league.controller;

import com3014.league.service.fixtureService;
import com3014.league.model.Fixture;
import com3014.league.model.League;
import com3014.league.model.Player;
import com3014.league.model.Team;
import com3014.league.service.leagueService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * @param model
     * @param league
     * @return all the leagues present in the dao
     */
    @RequestMapping(value = {"/all", "/"}, method = RequestMethod.GET)
    public String viewLeagues(ModelMap model, @ModelAttribute League league) {
        model.addAttribute("leagues", leagueService.getAllLeagues());
        
        return "league";
    }
    
    /**
     * Makes a new accessible league
     * @param model
     * @param league
     * @param leagueName
     * @param leagueMaxTeams
     * @return redirects back to the url to show posted league
     */
    @RequestMapping(value = {"/all", "/"}, method = RequestMethod.POST)
    public String submitLeague(ModelMap model, @ModelAttribute League league, @RequestParam("leagueName") String leagueName, @RequestParam("leagueMaxTeams") int leagueMaxTeams) {
        League newLeague = new League();
        newLeague.setName(leagueName);
        newLeague.setMaxTeams(leagueMaxTeams);
        int highestId = 0;
        // Create a new unique ID for the new league
        for (League l: leagueService.getAllLeagues()) {
            if (l.getId() > highestId) {
                highestId = l.getId();
            }
        }
        newLeague.setId(highestId + 1);
        List<Team> teamsList = new ArrayList<>();
        newLeague.setTeamsList(teamsList);
        
        leagueService.addLeague(newLeague);
        
        return "redirect:/league/all";
    }
    
    /**
     * @param leagueId - id of a league
     * @param model
     * @param team
     * @return the specific chosen league by its ID
     */
    @RequestMapping(value = "/{leagueId}",  method = RequestMethod.GET)
    public String viewLeagues(@PathVariable int leagueId, ModelMap model, @ModelAttribute Team team) {
        League league = leagueService.getLeagueByID(leagueId);
        List<Team> teams = leagueService.getAllTeams(leagueId);
        List<Fixture> fixtures = fixtureService.getallFixtures();
        model.addAttribute("league", league) ;
        model.addAttribute("teams", teams) ;
        model.addAttribute("fixtures", fixtures) ;
        return "viewLeague";
    }
    
    /**
     * 
     * @param leagueId
     * @return the url with the league deleted
     */
    @RequestMapping(value = "/{leagueId}/delete", method = RequestMethod.GET)
    public String deleteLeague(@PathVariable int leagueId) {
        leagueService.deleteLeague(leagueId);
        return "redirect:/league/all";
    }
    
    /**
     * Adds a fixture to the list of fixtures in a league and updates team results
     * @param leagueId - id of league in which the fixture will be updated on
     * @param model
     * @param home - in int, so its unique
     * @param away - in int, so its unique
     * @param homeScore - goal scored by home team
     * @param awayScore - goal scored by away team
     * @return redirects back to the all league page
     */
    @RequestMapping(value = "/{leagueId}", method = RequestMethod.POST)
    public String submitFixture(@PathVariable int leagueId, ModelMap model, @RequestParam("home") int home,@RequestParam("away") int away,@RequestParam("homeScore") int homeScore,@RequestParam("awayScore") int awayScore) {
        Team homeTeam = leagueService.getTeamByID(home,leagueId);
        Team awayTeam = leagueService.getTeamByID(away,leagueId);
        
        // using the home goals and away goals, it determines if home or away won or both drew and updates the 2 teams stats
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
        
        // creates a new fixture in the league
        Fixture fixture = new Fixture();
        fixture.setHome(homeTeam.getName());
        fixture.setAway(awayTeam.getName());
        fixture.setHomeScore(homeScore);
        fixture.setAwayScore(awayScore);
        fixture.setLocation(leagueService.getAllTeams(leagueId).get(home).getLocation());
        fixtureService.fixtureAdd(fixture);
        return "redirect:/league/{leagueId}";
    }
    
    //@RequestMapping(value = "/{leagueId}", method = RequestMethod.POST)
    //public String addTeam(@PathVariable int leagueId, ModelMap model /*TODO: add request params and a form to send those*/) {
        
    //    return "redirect:/league/{leagueId}";
    //}
    
    /**
     * 
     * @param leagueId
     * @param teamId
     * @param model
     * @param team
     * @return a specific league page displaying tables and fixtures
     */
    @RequestMapping(value = "/{leagueId}/team/{teamId}",  method = RequestMethod.GET)
    public String viewTeams(
            @PathVariable int leagueId, @PathVariable int teamId, ModelMap model, @ModelAttribute Team team
    ) {
        Team thisTeam = leagueService.getTeamByID(teamId,leagueId);
        List<Player> thisPlayers = thisTeam.getPlayers();
        model.addAttribute("team", thisTeam);
        model.addAttribute("players", thisPlayers);
        return "viewTeam";
    }
    
    /**
     * 
     * @param leagueId
     * @param teamId
     * @param model
     * @param team
     * @return the same url with the teams deleted
     */
    @RequestMapping(value = "/{leagueId}/team/{teamId}/delete",  method = RequestMethod.GET)
    public String deleteTeam(
            @PathVariable int leagueId, @PathVariable int teamId, ModelMap model, @ModelAttribute Team team
    ) {
        leagueService.deleteTeam(teamId, leagueId);
        return "redirect:/league/{leagueId}";
    }
    
    @ModelAttribute("league")
    public League populateLeague() {
        return new League();
    }
    
    @ModelAttribute("team")
    public Team populateTeam() {
        return new Team();
    }

    @RequestMapping(value = {"/{leagueId}/team/add_team"}, method = RequestMethod.POST)
    public String submitTeam(ModelMap model, @ModelAttribute League league, @RequestParam("teamName") String teamName, @RequestParam("teamLocation") String teamLocation, @RequestParam("leagueId") int leagueId) {
        Team team = new Team(0, teamName, teamLocation);
        int highestId = 0;
        // Create a new unique ID for the new league
        for (Team t : leagueService.getAllTeams(leagueId)) {
            if (t.getId() > highestId) {
                highestId = t.getId();
            }
        }
        team.setId(highestId + 1);

        leagueService.addTeam(team, leagueId);

        return "redirect:/league/" + Integer.toString(leagueId);
    }
}
