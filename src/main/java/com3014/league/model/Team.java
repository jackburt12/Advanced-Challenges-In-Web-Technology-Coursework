/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.model;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author ahsan
 * model class that encapsulates the team
 */
@Component
public class Team implements Comparable<Team>{
    private int id;
    private String Name;
    private int matchPlayed;
    private int won;
    private int draw;
    private int loss;
    private int goalDifference;
    private int points;
    private String location;
    private List<Player> players;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getMatchPlayed() {
        return matchPlayed;
    }

    public void setMatchPlayed(int matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Player getPlayerByNumber(int playerNum) {
        Player player = null;
        for (Player p: players) {
            if (p.getNumber() == playerNum) {
                player = p;
            }
        }
        return player;
    }
    
    public void deletePlayer(int playerNum) {
        players.remove(getPlayerByNumber(playerNum));
    }
    
    public void updatePlayer(int playerNum, Player player) {
        players.set(players.indexOf(getPlayerByNumber(playerNum)), player);
    }
    
    public void addPlayer(Player player) {
        players.add(player);
    }
    
    @Override
    public int compareTo(Team o)
    {
         return(o.points - points);
    }
}
