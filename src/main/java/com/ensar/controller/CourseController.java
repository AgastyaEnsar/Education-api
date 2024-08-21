package com.ensar.controller;

import com.ensar.entity.Course;
import com.ensar.request.dto.CreateUpdateCourseDto;
import com.ensar.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = "Course Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "Get course by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(
            @ApiParam(value = "ID of the course to retrieve", required = true)
            @PathVariable String id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @ApiOperation(value = "Get all courses")
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Course>>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        
        Map<String, List<Course>> response = new HashMap<>();
        response.put("courses", courses);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new course")
    @PostMapping("/")
    public ResponseEntity<Course> createCourse(
            @Valid @RequestBody CreateUpdateCourseDto courseDto) {
        Course savedCourse = courseService.createOrUpdateCourse(Optional.empty(), courseDto);
        return ResponseEntity.ok(savedCourse);
    }

    @ApiOperation(value = "Update an existing course")
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @ApiParam(value = "ID of the course to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody CreateUpdateCourseDto courseDto) {
        Course updatedCourse = courseService.createOrUpdateCourse(Optional.of(id), courseDto);
        return ResponseEntity.ok(updatedCourse);
    }

    @ApiOperation(value = "Delete a course by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @ApiParam(value = "ID of the course to delete", required = true)
            @PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
