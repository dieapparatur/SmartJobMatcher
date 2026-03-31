package com.JobSmartMatcher.Matcher.auth;

import com.JobSmartMatcher.Matcher.Entities.CompanyEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CompanyRepository;
import com.JobSmartMatcher.Matcher.auth.security.JWTAuthController;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyLoginController {


    public final CompanyRepository companyRepository;
    public final JWTAuthController jwtAuthController;

    public CompanyLoginController(CompanyRepository companyRepository, JWTAuthController jwtAuthController) {
        this.companyRepository = companyRepository;
        this.jwtAuthController = jwtAuthController;
    }

    @PostMapping(path = "/login/company")
    public String loginControl(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {

        if (email.equals("Default E-Mail") || password.equals("Default Password")) {
            return "Some credentials seem to be missing, or something went wrong with giving the data over.";
        } else if (companyRepository.existsByEmail(email)) {
            CompanyEntity company = companyRepository.findByEmail(email);
            System.out.println("Provided mail was found in database.");
            System.out.println("Checking if provided password equals the one in the DB.");
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            if (passwordEncoder.matches(password, company.getHashedPassword())) {
                return jwtAuthController.loginJWT(email, password);
            } else {
                return "Error with code 401: Authentication failed.\nRefresh page.";
            }
        } else {
            return "The provided mail address does not match with any address in out database.\nTry to registrate";
        }
    }

}








