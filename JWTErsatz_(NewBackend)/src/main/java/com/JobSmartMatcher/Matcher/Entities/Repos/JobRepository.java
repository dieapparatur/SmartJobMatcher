package com.JobSmartMatcher.Matcher.Entities.Repos;

import com.JobSmartMatcher.Matcher.Entities.CompanyEntity;
import com.JobSmartMatcher.Matcher.Entities.JobEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {


    public boolean existsByField(String field);

    public List<JobEntity> findAllByField(String field);

    public boolean existsByCompany(CompanyEntity company);

    public List<JobEntity> findAllByCompany(CompanyEntity company);

    public boolean existsByTitleAndCompany(String name, CompanyEntity company);

}





//THOUGHTS: THERE SHOULD BE A OWN TABLE ONLY FOR JOB LISTINGS; THEN IT GETS FILTERED BY CATEGORY (MEANING TYPE OF FIELD (IT, DESIGN, MARKETING, GASTRO, ETC)
//IF THE USER WANTED TO WORK ON SIGHT OR IN A SPECIFIC CITY, IT GETS ORDERED ACCORDINGLY TO LOCATION, IF THOSE ARE FINISHED, THEN REMOTE.
//THOSE ARE PACKED INTO A QUEUE

//so then think about it; what is it that we search by? how does the array come along? I guess it's select all where... or something like that

//they are collected by fields, when the candidate selected multiple fields, the arrays get randomly mixed

//THEY GET THROWN INTO AN ARRAYLIST, THEN PULLED AND DISPLAYED