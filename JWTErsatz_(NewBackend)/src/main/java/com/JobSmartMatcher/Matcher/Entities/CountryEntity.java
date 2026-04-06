package com.JobSmartMatcher.Matcher.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "country")
@Setter
@Getter
public class CountryEntity {


    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "iso3")
    private String iso3;

    @Column(name = "number")
    private String number;

    @Column(name = "continent_code")
    private String continentCode;

}
