package com.pokemonreview.api;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/")
@EnableWebSecurity
public class TestController {
    @GetMapping("/")
    public String test(){
        
        final var a = SecurityContextHolder.getContext().getAuthentication();
        
        String res = "<br>" + "<h2>This page can be viewed by ANNYBUDDIEZZ without authentication</h2>"
                + "<br>" + "<a href=\"http://localhost:8080/logout\">Logout</a>";
                
        
        if(a == null) return res;
        else return res + "<br>" + "User authenticated: " + a.isAuthenticated();
    }

    @GetMapping("/for-users")
    public String forUsers(){
        return "This page should only be visible for people with <strong>USER</strong> or <strong>ADMIN</strong> role";
    }
    
    @GetMapping("/logout")
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    
}
