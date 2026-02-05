package com.JobSmartMatcher.Matcher.Entities;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDateTime;


@Entity
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
    private String profilePictureURL;

    @Column (name = "bio")
    private String bio;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;

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

    @Column(name = "candidate_mail")
    private String email;

    @Column(nullable = false, name = "candidate_password_hash")
    private String hashedPassword;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getPreferredEmploymentType() {
        return preferredEmploymentType;
    }

    public void setPreferredEmploymentType(String preferredEmploymentType) {
        this.preferredEmploymentType = preferredEmploymentType;
    }

    public String getPreferredWorkLocation() {
        return preferredWorkLocation;
    }

    public void setPreferredWorkLocation(String preferredWorkLocation) {
        this.preferredWorkLocation = preferredWorkLocation;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public LocalDateTime getProfileCreatedAt() {
        return profileCreatedAt;
    }

    public void setProfileCreatedAt(LocalDateTime profileCreatedAt) {
        this.profileCreatedAt = profileCreatedAt;
    }

    public LocalDateTime getProfileUpdatedAt() {
        return profileUpdatedAt;
    }

    public void setProfileUpdatedAt(LocalDateTime profileUpdatedAt) {
        this.profileUpdatedAt = profileUpdatedAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public CandidateEntity() {}

    public CandidateEntity(String firstName, String lastName, String preferredEmploymentType, String email, String hashedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferredEmploymentType = preferredEmploymentType;
        /*this.profileCreatedAt = profileCreatedAt;
        this.profileUpdatedAt = profileUpdatedAt;*/
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

}