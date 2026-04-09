package com.JobSmartMatcher.Matcher.auth.company;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import com.JobSmartMatcher.Matcher.Entities.CompanyEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CandidateRepository;
import com.JobSmartMatcher.Matcher.Entities.Repos.CompanyRepository;
import com.JobSmartMatcher.Matcher.auth.security.JWTHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeEndpointComp {

    public final JWTHandler jwtHandler;
    public final CompanyRepository repo;

    public MeEndpointComp(JWTHandler jwtHandler, CompanyRepository repo) {
        this.jwtHandler = jwtHandler;
        this.repo = repo;
    }



    //always returns candidate; there has to be an if statement that checks for the role of the user
    @GetMapping("/company/me")
    public String userInformation(@org.springframework.web.bind.annotation.RequestHeader("Authorization") String authHeader) {
        if (authHeader == null) {
            return "Missing Authorization header.";
        }

        String token = authHeader.substring("Bearer ".length());

        String email = jwtHandler.extractEmail(token);
        if (!jwtHandler.validateToken(token, email)) {
            return "It seems that the JWT was invalid or expired. Information denied.";
        }

        CompanyEntity company = repo.findByEmail(email);

        return "Name: " + company.getName()
                + "\nEmail: " + company.getEmail()
                + "\nAbout your company: " + company.getAbout()
                + "\nEstablished in: " + company.getEstablished()
                + "\nNumber of employees: " + company.getEmployeeCount()
                + "\nWebsite URL: " + company.getWebsiteUrl()
                + "\nHERE SHOULD BE LINK TO THE JOBS OF THE COMPANY"; //JUST USE THE ID AND SEARCH FOR THEM IN THE JOBS TABLE
    }

}
