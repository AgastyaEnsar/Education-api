package com.ensar.service;

import com.ensar.entity.Course;
import com.ensar.repository.CourseRepository;
import com.ensar.request.dto.CreateUpdateCourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createOrUpdateCourse(Optional<String> courseId, CreateUpdateCourseDto courseDto) {
        Course course;
        if (courseId.isEmpty() || courseId.get().isEmpty()) {
            course = new Course();
            course.setId(UUID.randomUUID().toString());
        } else {
            course = courseRepository.findById(courseId.get())
                    .orElseThrow(() -> new RuntimeException("Course with id " + courseId.get() + " not found"));
        }

        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setDuration(courseDto.getDuration());
        course.setStartDate(courseDto.getStartDate());
        course.setEndDate(courseDto.getEndDate());

        return courseRepository.save(course);
    }

    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void deleteCourse(String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        courseRepository.delete(course);
    }
}
