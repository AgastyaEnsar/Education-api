package com.ensar.repository;

import com.ensar.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findByName(String name);

    List<Course> findByDuration(String duration);

    List<Course> findByStartDate(Date startDate);

    List<Course> findByEndDate(Date endDate);

    List<Course> findByStartDateBefore(Date endDate);

    List<Course> findByStartDateAfter(Date startDate);

    List<Course> findByEndDateAfter(Date endDate);
}
