package com.JobSmartMatcher.Matcher.auth.candidate;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CandidateRepository;
import com.JobSmartMatcher.Matcher.auth.security.JWTAuthController;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React
@RestController
public class CandidateLoginController {


    public final CandidateRepository candidateRepository;
    public final JWTAuthController jwtAuthController;

    public CandidateLoginController(CandidateRepository candidateRepository, JWTAuthController jwtAuthController) {
        this.candidateRepository = candidateRepository;
        this.jwtAuthController = jwtAuthController;
    }

    @PostMapping(path = "/login/candidate")
    public String loginControl(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {


        if (email.equals("Default E-Mail") || password.equals("Default Password")) {
            return "Some credentials seem to be missing, or something went wrong with giving the data over.";
        } else if (candidateRepository.existsByEmail(email)) {
            CandidateEntity customer = candidateRepository.findByEmail(email);
            System.out.println("Provided mail was found in database.");
            System.out.println("Checking if provided password equals the one in the DB.");
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            if (passwordEncoder.matches(password, customer.getHashedPassword())) {
                return jwtAuthController.candidateLoginJWT(email, password);
            } else {
                return "Error with code 401: Authentication failed.\nRefresh page.";
            }
        } else {
            return "The provided mail address does not match with any address in out database.\nTry to registrate";
        }
    }
}
