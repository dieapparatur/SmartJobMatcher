package com.JobSmartMatcher.Matcher.auth.company;

import com.JobSmartMatcher.Matcher.Entities.CompanyEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CompanyRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CompanyRegistration {


    public final CompanyRepository companyRepository;
    //public final CompanyEntity companyEntity;


    public CompanyRegistration(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        //this.companyEntity = companyEntity;
    };


    @PostMapping(path = "/register/company")
    public String registrationHandler(@RequestParam(value = "companyName") String companyName,
                                      @RequestParam(value = "email") String email,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value = "repeatedPassword") String repeatedPassword){



        if(!password.equals(repeatedPassword)) {
            return "Passwords do not match, please correct.";
        }

        if (emailRegexChecker(email)) {
            System.out.println("Provided E-Mail fits the Regex-Criteria. Moving on.");
            if (!companyRepository.existsByEmail(email)) {
                System.out.println("Provided E-Mail was not found in DB and should be ready for registration. Moving on.");
                System.out.println("Hashing the provided password <" + password + ">.");
                //Properly hashes provided password with salt
                PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

                String hashedPassword = passwordEncoder.encode(password);

                System.out.println("Creating new company with necessary parameters.");

                final CompanyEntity newCompany = new CompanyEntity(companyName, email, hashedPassword);

                System.out.println("Saving new Company to DB");

                companyRepository.save(newCompany);

                return "Registration accomplished.";

            } else {
                return "Provided E-Mail is already registered in DB.";
            }
        }
        return "Registration handler completed";
    }


    public boolean emailRegexChecker(String givenMail) {
        if (givenMail.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            System.out.println("Provided mail matches with Regex. Returning true.");
            return true;
        };
        System.out.println("The mail does not match with the regex. Returning false.");
        return false;
    }

}

