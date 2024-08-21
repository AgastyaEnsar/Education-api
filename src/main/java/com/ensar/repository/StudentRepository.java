package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Student;

// import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}
