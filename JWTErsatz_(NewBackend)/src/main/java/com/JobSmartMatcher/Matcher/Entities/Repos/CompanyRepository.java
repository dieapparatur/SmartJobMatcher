package com.JobSmartMatcher.Matcher.Entities.Repos;

import com.JobSmartMatcher.Matcher.Entities.CompanyEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    @Bean
    public boolean existsByEmail(String email);

    @Bean
    public CompanyEntity findByEmail(String email);

    @Bean
    public CompanyEntity findById(long id);
}
