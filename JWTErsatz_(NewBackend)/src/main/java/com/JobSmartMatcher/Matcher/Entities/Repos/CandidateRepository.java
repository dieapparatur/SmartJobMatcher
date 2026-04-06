package com.JobSmartMatcher.Matcher.Entities.Repos;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    @Bean
    public boolean existsByEmail(String email);

    @Bean
    public CandidateEntity findByEmail(String email);

}
