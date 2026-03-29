package com.JobSmartMatcher.Matcher.auth.company;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JobRepository extends JpaRepository<jobEntity, Long> {

    @Bean
    public boolean existsByField(String field);

    @Bean
    public jobEntity findByField(String field);

    //in case that there is some filter to only select jobs from certain companies
    @Bean
    public boolean existsByCompany(String company);

    @Bean
    public jobEntity findByCompany(String company);

}





//THOUGHTS: THERE SHOULD BE A OWN TABLE ONLY FOR JOB LISTINGS; THEN IT GETS FILTERED BY CATEGORY (MEANING TYPE OF FIELD (IT, DESIGN, MARKETING, GASTRO, ETC)
//IF THE USER WANTED TO WORK ON SIGHT OR IN A SPECIFIC CITY, IT GETS ORDERED ACCORDINGLY TO LOCATION, IF THOSE ARE FINISHED, THEN REMOTE.
//THOSE ARE PACKED INTO A QUEUE

//so then think about it; what is it that we search by? how does the array come along? I guess it's select all where... or something like that

//they are collected by fields, when the candidate selected multiple fields, the arrays get randomly mixed

//THEY GET THROWN INTO AN ARRAYLIST, THEN PULLED AND DISPLAYED