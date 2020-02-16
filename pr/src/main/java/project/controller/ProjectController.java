/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class ProjectController {
    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @RequestMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        return "forgotPassword";
   }
    
    @RequestMapping("/homePage")
    public String homePage(Model model) {
        return "homePage";
    }
    
    @RequestMapping("/signUp")
    public String signUp(Model model) {
       return "signUp";
    }
}
