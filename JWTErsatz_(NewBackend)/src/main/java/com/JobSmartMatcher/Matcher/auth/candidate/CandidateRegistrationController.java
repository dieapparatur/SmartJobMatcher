package com.JobSmartMatcher.Matcher.auth.candidate;

import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CandidateRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateRegistrationController {



    public final CandidateRepository candidateRepository;
    public final CandidateEntity candidateEntity;


    public CandidateRegistrationController(CandidateRepository candidateRepository, CandidateEntity candidateEntity) {
        this.candidateRepository = candidateRepository;
        this.candidateEntity = candidateEntity;
    };


    @PostMapping(path = "/register/candidate")
    public String registrationHandler(@RequestParam(value = "firstName", defaultValue = "Default First Name") String firstName,
                                      @RequestParam(value = "lastName", defaultValue = "Default Last Name") String lastName,
                                      @RequestParam(value = "email", defaultValue = "Default E-Mail") String email,
                                      @RequestParam(value = "password", defaultValue = "Default Password") String password,
                                      @RequestParam(value = "repeatedPassword", defaultValue = "Default repeated Password") String repeatedPassword,
                                      @RequestParam(value = "preferredEmploymentType", defaultValue = "Preferred Employment Type") String preferredEmploymentType,
                                      @RequestParam(value = "role", defaultValue = "Default Role") String role)
    {
        //Sollte checken ob da vielleicht NULL-Errors oder so reingekommen sind (EVTL ÜBERARBEITEN)
        if (firstName.equals("Default First Name") || lastName.equals("Default Last Name") || email.equals("Default E-Mail") || password.equals("Default Password") || repeatedPassword.equals("Default repeated Password") || role.equals("Default Role")) {
            return "Something went wrong. Some registration data may got lost or are NULL. Please try again or contact support.";
        }

        if(!password.equals(repeatedPassword)) {
            //SPÄTER ERROR THROWEN -> ÜBERARBEITEN
            return "Passwords do not match, please correct.";
        }

        if (emailRegexChecker(email)) {
            System.out.println("Provided E-Mail fits the Regex-Criteria. Moving on.");
            if (!candidateRepository.existsByEmail(email)) {
                System.out.println("Provided E-Mail was not found in DB and should be ready for registration. Moving on.");
                System.out.println("Hashing the provided password <" + password + ">.");
                //Properly hashes provided password with salt
                PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

                String hashedPassword = passwordEncoder.encode(password);

                System.out.println("Creating new Candidate with necessary parameters.");
                CandidateEntity candidateEntity = new CandidateEntity(firstName, lastName, preferredEmploymentType, email, hashedPassword);

                System.out.println("Saving new Candidate to DB");

                candidateRepository.save(candidateEntity);

                return "Registration accomplished.";

            } else {
                return "Provided E-Mail is already registered in DB.";
            }
        }
        return "Registration handler completed";
    }


    public boolean emailRegexChecker(String givenMail) {
        /*
        E-Mail Regex:
        ^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$

        The following restrictions are imposed in the email address’ local part by using this regex:

        It allows numeric values from 0 to 9.
        Both uppercase and lowercase letters from a to z are allowed.
        Allowed are underscore “_”, hyphen “-“, and dot “.”
        Dot isn’t allowed at the start and end of the local part.
        Consecutive dots aren’t allowed.
        For the local part, a maximum of 64 characters are allowed.
        Restrictions for the domain part in this regular expression include:

        For the domain part:

        It allows numeric values from 0 to 9.
        We allow both uppercase and lowercase letters from a to z.
        Hyphen “-” and dot “.” aren’t allowed at the start and end of the domain part.
        No consecutive dots.
        */
        if (givenMail.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            System.out.println("Provided mail matches with Regex. Returning true.");
            return true;
        };
        System.out.println("The mail does not match with the regex. Returning false.");
        return false;
    }

}
