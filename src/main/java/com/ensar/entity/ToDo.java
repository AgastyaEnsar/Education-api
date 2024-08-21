package com.ensar.entity;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "todo")
@EqualsAndHashCode(callSuper = true)
public class ToDo extends BaseEntity {

	@Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;    

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ToDoStatus status;

     // Enums for status and priority
    public enum ToDoStatus {
        not_started,
        in_progress,
        completed
    }

 }
