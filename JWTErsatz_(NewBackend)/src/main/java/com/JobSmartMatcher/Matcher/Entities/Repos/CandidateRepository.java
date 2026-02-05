package com.JobSmartMatcher.Matcher.Entities.Repos;


import com.JobSmartMatcher.Matcher.Entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    public boolean existsByEmail(String email);


}
