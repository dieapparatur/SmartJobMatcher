package com.JobSmartMatcher.Matcher.auth.security;

import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class JWTAuthController {

    //private final AuthenticationManager authenticationManager;
    private final UserInfoHandler userInfoHandler;
    private final JWTHandler jwtHandler;

    public JWTAuthController(/*AuthenticationManager authenticationManager,*/
                          UserInfoHandler userInfoHandler,
                          JWTHandler jwtHandler) {
        //this.authenticationManager = authenticationManager;
        this.userInfoHandler = userInfoHandler;
        this.jwtHandler = jwtHandler;
    }


    public String candidateLoginJWT(@RequestParam String email, @RequestParam String password) {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails candidate = userInfoHandler.loadCandidateUserByUsername(email);
        return jwtHandler.generateToken(candidate.getUsername(), "candidate");
    }

    public String companyLoginJWT(@RequestParam String email, @RequestParam String password) {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails candidate = userInfoHandler.loadCompanyUserByUsername(email);
        return jwtHandler.generateToken(candidate.getUsername(), "company");
    }
}
