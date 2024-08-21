package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.LearningPlan;

import java.util.List;

@Repository
public interface LearningPlanRepository extends JpaRepository<LearningPlan, String> {


}
