package com.JobSmartMatcher.Matcher.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Entity
@Getter
@Setter
@Table (name = "candidate")
public class CandidateEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(name = "profile_image_url")
    private String picture;

    @Column (name = "bio")
    private String bio;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(nullable = false, name = "preferred_employment_type")
    private String preferredEmploymentType;

    //only two characters are allowed
    @Column(name = "preferred_work_location")
    private String preferredWorkLocation;

    @Column(name = "highest_degree")
    private String highestDegree;

    @Column(name="profile_created_at",insertable=false, updatable=false/*, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP"*/)
    private LocalDateTime profileCreatedAt;

    @Column(name="profile_updated_at",insertable=false, updatable=false/*, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP"*/)
    private LocalDateTime profileUpdatedAt;

    @Column(name = "candidate_mail", nullable = false)
    private String email;

    @Column(nullable = false, name = "candidate_password_hash")
    private String hashedPassword;

    @Column(nullable = false, name = "role")
    private String role;


    public CandidateEntity() {}

    public CandidateEntity(String firstName, String lastName, String preferredEmploymentType, String email, String hashedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferredEmploymentType = preferredEmploymentType;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = "candidate";
    }


    public CandidateEntity(String firstName, String lastName, String picture, String bio, Integer yearsOfExperience, String preferredEmploymentType, String preferredWorkLocation, String highestDegree, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.bio = bio;
        this.yearsOfExperience = yearsOfExperience;
        this.preferredEmploymentType = preferredEmploymentType;
        this.preferredWorkLocation = preferredWorkLocation;
        this.highestDegree = highestDegree;
        this.email = email;
        this.role = "candidate";
    }

    public CandidateEntity(String firstName, String lastName, String picture, String bio, Integer yOE, String location, String degree, String preferredEmploymentType) {
    }
}