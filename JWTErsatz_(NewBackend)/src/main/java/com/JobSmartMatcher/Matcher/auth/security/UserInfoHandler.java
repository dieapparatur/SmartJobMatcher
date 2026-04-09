package com.JobSmartMatcher.Matcher.auth.security;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import com.JobSmartMatcher.Matcher.Entities.CompanyEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CandidateRepository;
import com.JobSmartMatcher.Matcher.Entities.Repos.CompanyRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoHandler implements UserDetailsService {

    private final CandidateRepository candidateRepository;

    private final CompanyRepository companyRepository;

    public UserInfoHandler (CandidateRepository repo, CompanyRepository companyRepository) {
        this.candidateRepository = repo;
        this.companyRepository = companyRepository;
    }


    /*
    public CandidateEntity loadUserByEmail(String email) {
        CandidateEntity candidate = repo.findByEmail(email);
        if (candidate == null) throw new UsernameNotFoundException("Email not found");
        return candidate;
    }
    */


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CandidateEntity candidate = candidateRepository.findByEmail(email);
        if (candidate == null) throw new UsernameNotFoundException("Email not found");
        return org.springframework.security.core.userdetails.User
                .withUsername(candidate.getEmail())
                .password(candidate.getHashedPassword())
                //.authorities("ROLE_" + candidate.getRole())
                .authorities(candidate.getRole())
                .build();
    }

    public UserDetails loadCandidateUserByUsername(String email) throws UsernameNotFoundException {
        CandidateEntity candidate = candidateRepository.findByEmail(email);
        if (candidate == null) throw new UsernameNotFoundException("Email not found");
        return org.springframework.security.core.userdetails.User
                .withUsername(candidate.getEmail())
                .password(candidate.getHashedPassword())
                //.authorities("ROLE_" + candidate.getRole())
                .authorities(candidate.getRole())
                .build();
    }

    public UserDetails loadCompanyUserByUsername(String email) throws UsernameNotFoundException {
        CompanyEntity company = companyRepository.findByEmail(email);
        if (company == null) throw new UsernameNotFoundException("Email not found");
        return org.springframework.security.core.userdetails.User
                .withUsername(company.getEmail())
                .password(company.getHashedPassword())
                //.authorities("ROLE_" + candidate.getRole())
                .authorities(company.getRole())
                .build();
    }
}
