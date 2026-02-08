package com.JobSmartMatcher.Matcher.auth;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CandidateRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.rmi.server.LogStream.log;

@RestController
public class LoginController {


    public final CandidateRepository candidateRepository;

    public LoginController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping(path = "/login")
    public String loginControl(@RequestParam(value = "email", defaultValue = "Default E-Mail") String email,
                             @RequestParam(value = "password", defaultValue = "Default Password") String password)
    {

        if (email.equals("Default E-Mail") || password.equals("Default Password")) {
            return "Some credentials seem to be missing, or something went wrong with giving the data over";
        } else if (candidateRepository.existsByEmail(email)) {
            CandidateEntity customer = candidateRepository.findByEmail(email);
            System.out.println("Provided mail was found in database.");
            System.out.println("Checking if provided password equals the one in the DB.");
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            if (passwordEncoder.matches(password, customer.getHashedPassword())) {
                return "User was found with first name " + customer.getFirstName();
            } else {
                return "Error with code 401: Authentication failed.\nRefresh page.";
            }
        } else {
            return "The provided mail address does not match with any address in out database.\nTry to registrate";
        }
    }
}
