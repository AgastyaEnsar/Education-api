package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    // Custom query method to find projects by name
    List<Project> findByName(String name);

    // Custom query method to find projects by manager ID
    List<Project> findByManagerId(String managerId);

    // Additional custom methods can be added here

}
