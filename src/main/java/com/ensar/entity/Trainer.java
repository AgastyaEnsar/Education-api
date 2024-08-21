package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.Date;

@Entity(name = "trainer")
@Data
@EqualsAndHashCode(callSuper = true)
public class Trainer extends BaseEntity {

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "company", nullable = false, length = 45)
    private String company;

    @Column(name = "department", length = 45)
    private String department;

    @Column(name = "specialization", length = 100)
    private String specialization;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", unique = true, length = 15)
    private String phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "gender", columnDefinition = "ENUM('Male', 'Female', 'Other')")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    public enum Gender {
        Male, Female, Other
    }

    public void setDisabled(Object disabled) {
        // TODO Auto-generated method stub
    }
}

