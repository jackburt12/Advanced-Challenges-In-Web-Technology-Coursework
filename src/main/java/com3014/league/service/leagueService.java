/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.service;

import com3014.league.model.League;
import com3014.league.model.Team;
import java.util.List;

/**
 *
 * @author ahsan
 */
public interface leagueService {
    public void removeTeam(Team team);
    public void addTeam(Team team);
    public void changeName(String name);
    public void changeMaxTeam(int maxTeam);
    public void deleteLeague();
    public List<League> getAllLeagues();
}
