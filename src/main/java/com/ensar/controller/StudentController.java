package com.ensar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.ensar.entity.Student;
import com.ensar.request.dto.CreateUpdateStudentDto;
import com.ensar.service.StudentService;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = "Student Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "Get student by ID")
    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Student> getStudentById(
            @ApiParam(value = "ID of the student to retrieve", required = true)
            @PathVariable String id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            throw new ResourceNotFoundException("Student with id " + id + " not found.");
        }
        return ResponseEntity.ok(student);
    }

    @ApiOperation(value = "Get all students")
    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Map<String, List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        Map<String, List<Student>> response = new HashMap<>();
        response.put("students", students);
        return ResponseEntity.ok(response);

    }

    @ApiOperation(value = "Create a new student")
    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody CreateUpdateStudentDto studentDto) {
        Student savedStudent = studentService.createOrUpdateStudent(Optional.empty(), studentDto);
        return ResponseEntity.ok(savedStudent);
    }

    @ApiOperation(value = "Update an existing student")
    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Student> updateStudent(
            @ApiParam(value = "ID of the student to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody CreateUpdateStudentDto studentDto) {
        Student updatedStudent = studentService.createOrUpdateStudent(Optional.of(id), studentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @ApiOperation(value = "Delete a student by ID")
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteStudent(
            @ApiParam(value = "ID of the student to delete", required = true)
            @PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    
    @ApiOperation(value = "Export all students to CSV")
    @GetMapping("/export")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public void exportStudents(HttpServletResponse response) {
        studentService.exportStudents(response);
    }
    
    @ApiOperation(value = "Import students from CSV")
    @PostMapping("/import")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Void> importStudents(@RequestParam("file") MultipartFile file) {
        studentService.importStudents(file);
        return ResponseEntity.ok().build();
    }
}
