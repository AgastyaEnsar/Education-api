package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Entity(name = "learning_plan")
@Data
@EqualsAndHashCode(callSuper = true)
public class LearningPlan extends BaseEntity {

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "CreatedBy", nullable = false, length = 36)
    private String createdBy;
}
