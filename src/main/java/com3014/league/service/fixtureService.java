/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.service;

import com3014.league.dao.fixtureDAO;
import com3014.league.dao.leagueDAO;
import com3014.league.model.Fixture;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ahsan
 */
@Service
public class fixtureService {
    
    @Autowired
    fixtureDAO fixtureDAO;
    
    /**
     * 
     * @param fixture 
     * adds a fixture to the database
     */
    public void fixtureAdd(Fixture fixture){
        fixtureDAO.fixtureAdd(fixture);
    }
    
    /**
     * 
     * @return all the fixture
     */
    public List<Fixture> getallFixtures(){
        return fixtureDAO.getFixtures();
    }
    
}
