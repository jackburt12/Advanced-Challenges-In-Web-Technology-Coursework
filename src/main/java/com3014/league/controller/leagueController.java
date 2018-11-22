/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com3014.league.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ahsan
 */
@Controller
@RequestMapping("/league")
public class leagueController {
    
    @RequestMapping("/all")
    public String viewProducts(ModelMap model /*@ModelAttribute Team team*/) {
//        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }
    
}
