package com.JobSmartMatcher.Matcher.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Entity
@Table(name = "job_posting")
public class JobEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field", nullable = false)
    private String field;

    @ManyToOne(fetch = FetchType.LAZY) //apparently usually set to EAGER, which could hinder performance
    @JoinColumn(name = "related_company", nullable = false)
    private CompanyEntity company;

    @Column(name = "employment_type", nullable = false)
    private String employmentType;

    @Column(name = "job_name", nullable = false)
    private String title;

    @Column(name = "job_description", nullable = false)
    private String description;

    @Column(name = "salary")
    private Long salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location")
    private CountryEntity location;

    @Column(name = "interested_count")
    private int interested;

    @Column(name = "picture")
    private String picture;


    public JobEntity() {}

    public JobEntity(String title, String description, String field, CompanyEntity company, String employmentType, Long salary, CountryEntity location) {
        this.title = title;
        this.description = description;
        this.field = field;
        this.company = company;
        this.employmentType = employmentType;
        this.salary = salary;
        this.location = location;
    }

}
