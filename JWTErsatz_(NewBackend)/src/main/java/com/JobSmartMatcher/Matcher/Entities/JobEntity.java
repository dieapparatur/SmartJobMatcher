package com.JobSmartMatcher.Matcher.auth.company;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Entity
@Table(name = "job_posting")
public class jobEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long input) {
        id = input;
    }

    @Column(name = "field", nullable = false)
    private String field;
    public String getField() {
        return field;
    }
    public void setField(String input) {
        field = input;
    }

    @Column(name = "related_company")
    private String company;
    public String getCompany() {
        return company;
    }
    public void setCompany(String input) {
        company = input;
    }

    @Column(name = "job_name", nullable = false)
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String input) {
        title = input;
    }

    @Column(name = "job_description", nullable = false)
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String input) {
        description = input;
    }

    @Column(name = "salary")
    private int salary;
    public int getSalary() {
        return salary;
    }
    public void setSalary(int input) {
        salary = input;
    }

    @Column(name = "location")
    private String location;
    public String getLocation() {
        return location;
    }
    public void setLocation(String input) {
        location = input;
    }

    @Column(name = "interested_count")
    private int interested;
    public int getInterested() {
        return interested;
    }
    public void setInterested(int input) {
        interested = input;
    }

    @Column(name = "picture")
    private String picture;
    public String getPicture() {
        return picture;
    }
    public void setPicture(String input) {
        picture = input;
    }

    //gets handled by the DB itself
    /*@Column(name = "created_at", insertable=false, updatable=false)
    private LocalDateTime createdAt;
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime input) {
        createdAt = input;
    }*/


    public jobEntity() {}

    public jobEntity(String title, String description, String field, String company, int salary, String location) {
        this.title = title;
        this.description = description;
        this.field = field;
        this.company = company;
        this.salary = salary;
        this.location = location;
    }

}
