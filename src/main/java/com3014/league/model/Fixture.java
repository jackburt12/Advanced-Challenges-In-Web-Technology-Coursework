/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author ahsan
 * model class that encapsulates the fixtures
 */
@Component
public class Fixture {
    private static final Team temp = new Team();
    private int id;
    private Team home;
    private Team away;
    private int homeScore;
    private int awayScore;

    public Fixture() {
        this(0,temp,temp,0 ,0 );
    }

    public Fixture(int id, Team home, Team away, int homeScore, int awayScore) {
        this.id = id;
        this.home = home;
        this.away = away;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getLocation() {
        return home.getLocation();
    }

}
