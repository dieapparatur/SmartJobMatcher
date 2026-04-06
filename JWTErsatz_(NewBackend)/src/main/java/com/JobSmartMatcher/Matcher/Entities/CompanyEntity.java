package com.JobSmartMatcher.Matcher.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "company")
public class CompanyEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name", unique = true)
    private String name;

    @OneToMany(mappedBy = "company")
    private List<JobEntity> jobs;

    @Column(name = "company_profile_image_url")
    private String image;

    @Column(name = "about")
    private String about;

    @Column(name = "established_in")
    private int established;

    @Column(name = "number_of_employees")
    private int employeeCount;

    @Column(name = "company_website_url")
    private String websiteUrl;

    @Column(name = "company_mail", unique = true)
    private String email;

    @Column(name = "company_password_hash")
    private String hash;

    @Column(name = "role")
    private String role;

    public CompanyEntity(){}

    public CompanyEntity(String name, String email, String hash) {
        this.name = name;
        this.email = email;
        this.hash = hash;
        role = "company";
    }


    public String getHashedPassword() {
        return hash;
    }

}
