package com.JobSmartMatcher.Matcher.UserInfo;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CandidateRepository;
import com.JobSmartMatcher.Matcher.auth.security.JWTHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeEndpoint {

    public final JWTHandler jwtHandler;
    public final CandidateRepository repo;

    public MeEndpoint(JWTHandler jwtHandler, CandidateRepository repo) {
        this.jwtHandler = jwtHandler;
        this.repo = repo;
    }



    //always returns candidate; there has to be an if statement that checks for the role of the user
    @GetMapping("/me")
    public String userInformation(@org.springframework.web.bind.annotation.RequestHeader("Authorization") String authHeader) {
        if (authHeader == null) {
            return "Missing Authorization header.";
        }

        String token = authHeader.substring("Bearer ".length());

        String email = jwtHandler.extractEmail(token);
        if (!jwtHandler.validateToken(token, email)) {
            return "It seems that the JWT was invalid or expired. Information denied.";
        }

        CandidateEntity candidate = repo.findByEmail(email);
        return "Name: " + candidate.getFirstName() + " " + candidate.getLastName()
                + "\nEmail: " + candidate.getEmail()
                + "\nRole: " + candidate.getRole()
                + "\nPreferred employment type: " + candidate.getPreferredEmploymentType();
    }
}
