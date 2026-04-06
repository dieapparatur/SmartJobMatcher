package com.JobSmartMatcher.Matcher.auth.candidate;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CandidateRepository;
import com.JobSmartMatcher.Matcher.auth.security.JWTHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateUpdateProfile {

    public final JWTHandler jwtHandler;
    public final CandidateRepository repo;

    public CandidateUpdateProfile(JWTHandler jwtHandler, CandidateRepository repo) {
        this.jwtHandler = jwtHandler;
        this.repo = repo;
    }


    @PutMapping(path = "/me/candidate/update")
    public String updateProfile(@org.springframework.web.bind.annotation.RequestHeader("Authorization") String authHeader,
                                @RequestParam(value = "firstName") String firstName,
                                @RequestParam(value = "lastName") String lastName,
                                @RequestParam(value = "picture", required = false) String picture,
                                @RequestParam(value = "bio", required = false) String bio,
                                @RequestParam(value = "preferredEmploymentType", required = false) String preferredEmploymentType,
                                @RequestParam(value = "yearsOfExperience", required = false) Integer yOE,
                                @RequestParam(value = "location", required = false) String location,
                                @RequestParam(value = "degree", required = false) String degree
                                ){

        if (authHeader == null) {
            return "Missing Authorization header.";
        }

        String token = authHeader.substring("Bearer ".length());

        String email = jwtHandler.extractEmail(token);

        if (!jwtHandler.validateToken(token, email)) {
            return "It seems that the JWT was invalid or expired. Access denied.";
        }


        CandidateEntity updatedCandidate = repo.findByEmail(email);

        updatedCandidate.setFirstName(firstName);
        updatedCandidate.setLastName(lastName);
        if (picture != null && !picture.trim().isEmpty()) {
        updatedCandidate.setPicture(picture);
        }
        if (!(bio == null) && !bio.trim().isEmpty()) {
            updatedCandidate.setBio(bio);
        }
        if (!(preferredEmploymentType == null) && !preferredEmploymentType.trim().isEmpty()) {
            updatedCandidate.setPreferredEmploymentType(preferredEmploymentType);
        }
        if (!(yOE == null)) {
            updatedCandidate.setYearsOfExperience(yOE);
        }
        if (!(location == null) && !location.trim().isEmpty()) {
            updatedCandidate.setPreferredWorkLocation(location);
        }
        if (!(degree == null) && !degree.trim().isEmpty()) {
            updatedCandidate.setHighestDegree(degree);
        }

        repo.save(updatedCandidate);


        return "Updated profile " + updatedCandidate.getFirstName();
    }

}
