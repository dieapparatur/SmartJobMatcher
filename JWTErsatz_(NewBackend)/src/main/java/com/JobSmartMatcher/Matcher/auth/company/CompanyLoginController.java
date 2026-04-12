package com.JobSmartMatcher.Matcher.auth.company;

import com.JobSmartMatcher.Matcher.Entities.CompanyEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CompanyRepository;
import com.JobSmartMatcher.Matcher.auth.security.JWTAuthController;
import com.JobSmartMatcher.Matcher.auth.security.LoginRequest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String loginControl(@RequestBody LoginRequest request) {

       if (companyRepository.existsByEmail(request.getEmail())) {
            CompanyEntity company = companyRepository.findByEmail(request.getEmail());
            System.out.println("Provided mail was found in database.");
            System.out.println("Checking if provided password equals the one in the DB.");
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            if (passwordEncoder.matches(request.getPassword(), company.getHashedPassword())) {
                return jwtAuthController.companyLoginJWT(request.getEmail(), request.getPassword());
            } else {
                return "Error with code 401: Authentication failed.\nRefresh page.";
            }
        } else {
            return "The provided mail address does not match with any address in out database.\nTry to registrate";
        }
    }

}








