package com.JobSmartMatcher.Matcher.Entities.Repos;

import com.JobSmartMatcher.Matcher.Entities.CountryEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    @Bean
    public boolean existsByCode(String code);

    @Bean
    public CountryEntity findByCode(String code);
}
