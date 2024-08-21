package com.ensar.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "topics")
@Data
@EqualsAndHashCode(callSuper = true)
public class Topic extends BaseEntity {

    public enum Level { low, medium, high }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    
    @Column(name="level", nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;
}
