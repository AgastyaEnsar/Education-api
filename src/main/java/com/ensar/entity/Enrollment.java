package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import jakarta.persistence.*;


@Entity(name = "Enrollment")
@Data
@EqualsAndHashCode(callSuper = true)
public class Enrollment extends BaseEntity {


    @Column(name = "enrollment_name")
    private String enrollmentName;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "enrollment_date")
    private Date enrollmentDate;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnrollmentStatus status;

    public enum EnrollmentStatus {
        enrolled,
        withdrawn,
        completed
    }


}