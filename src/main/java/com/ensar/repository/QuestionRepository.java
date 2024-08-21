package com.ensar.repository;

import com.ensar.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    // No references to Quiz entity, only Question-related queries
    List<Question> findByQuestionType(String questionType);
}
