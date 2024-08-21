package com.ensar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "category")
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {

    @Column(name = "category_name", nullable = false, length = 255, unique = true)
    private String categoryName;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // The createdDateTime and lastUpdatedDateTime fields are inherited from BaseEntity
}
