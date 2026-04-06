package com.JobSmartMatcher.Matcher.auth.company;


import com.JobSmartMatcher.Matcher.Entities.CompanyEntity;
import com.JobSmartMatcher.Matcher.Entities.Repos.CompanyRepository;
import com.JobSmartMatcher.Matcher.Entities.Repos.CountryRepository;
import com.JobSmartMatcher.Matcher.Entities.Repos.JobRepository;
import com.JobSmartMatcher.Matcher.Entities.JobEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostJob {

    public final JobRepository jobRepository;
    public final CompanyRepository companyRepository;
    public final CountryRepository countryRepository;

    public PostJob(JobRepository jobRepository, CompanyRepository companyRepository, CountryRepository countryRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    @PostMapping(path = "/postJob")
    public String addJobToDB (@RequestParam(value = "title") String title,
                              @RequestParam(value = "description") String description,
                              //make location also related with location table!!!!
                              @RequestParam(value = "location") String location,
                              @RequestParam(value = "employmentType") String employmentType,
                              @RequestParam(value = "company") long companyId,
                              @RequestParam(value = "field") String field,
                              @RequestParam(value = "salary") Long salary){


        try {
            if (!jobRepository.existsByTitleAndCompany(title, companyRepository.findById(companyId))) {
                System.out.println("JobTitle paired with Company does not exist yet. Ready to post the job into the DB.");

                JobEntity newJob = new JobEntity(title, description, field, companyRepository.findById(companyId), employmentType, salary, countryRepository.findByCode(location));

                jobRepository.save(newJob);

                System.out.println("Saved the new job in DB.");
            } else {
                System.out.println("JobTitle paired with Company already exists.");
                return "JobTitle paired with Company already exists.";
            }


            return "Added Job Posting to DB.";
        } catch (Exception e) {
            System.out.println("Something happened while trying to post a job; Throwing Error: " + e);
            throw new RuntimeException(e);
        }
    }

}
