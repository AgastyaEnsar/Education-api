package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "projects")
@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

   
}

