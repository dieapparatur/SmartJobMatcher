package com.JobSmartMatcher.Matcher.auth.security;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CandidateRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoHandler implements UserDetailsService {

    private final CandidateRepository repo;

    public UserInfoHandler (CandidateRepository repo) {
        this.repo = repo;
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
        CandidateEntity candidate = repo.findByEmail(email);
        if (candidate == null) throw new UsernameNotFoundException("Email not found");
        return org.springframework.security.core.userdetails.User
                .withUsername(candidate.getEmail())
                .password(candidate.getHashedPassword())
                .authorities("ROLE_" + candidate.getRole()) // or map properly
                .build();
    }
}
