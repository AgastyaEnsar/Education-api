package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@Entity(name = "syllabus")
@Data
@EqualsAndHashCode(callSuper = true)
public class Syllabus extends BaseEntity {

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "resource_link", nullable = false, length = 255)
    private String resourceLink;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;  // Relationship with Course entity

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "semester", nullable = false, length = 20)
    private String semester;

    @Column(name = "academic_year", nullable = false, length = 9)
    private String academicYear;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;  // Relationship with Trainer entity
}
