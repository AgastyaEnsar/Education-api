package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "timesheet")
@Data
@EqualsAndHashCode(callSuper = true)
public class Timesheet extends BaseEntity {

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "hours_spent")
    private BigDecimal hoursSpent;

    @Column(name = "task_date")
    private Date taskDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Additional fields, getters, setters, and other methods can be added as needed.
}
