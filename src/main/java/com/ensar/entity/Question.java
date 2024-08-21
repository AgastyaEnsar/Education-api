package com.ensar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity(name = "question")
@Data
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseEntity {

    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @Column(name = "question_type", nullable = false, length = 50)
    private String questionType;

    @Column(name = "marks", nullable = false)
    private Integer marks;

    @Column(name = "difficulty_level", nullable = false, length = 50)
    private String difficultyLevel;

    @Column(name = "category_name", nullable = false, length = 50)
    private String categoryName;

    @OneToOne
    @JoinColumn(name = "category_name", referencedColumnName = "category_name", insertable = false, updatable = false)
    private Category category;
}
