package com.ensar.controller;

import com.ensar.entity.Enrollment;
import com.ensar.request.dto.CreateUpdateEnrollmentDto;
import com.ensar.service.EnrollmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = "Enrollment Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @ApiOperation(value = "Get enrollment by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(
            @ApiParam(value = "ID of the enrollment to retrieve", required = true)
            @PathVariable String id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(enrollment);
    }

    @ApiOperation(value = "Get all enrollments")
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Enrollment>>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        Map<String, List<Enrollment>> response = new HashMap<>();
        response.put("enrollments", enrollments);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new enrollment")
    @PostMapping("/")
    public ResponseEntity<Enrollment> createEnrollment(
            @Valid @RequestBody CreateUpdateEnrollmentDto enrollmentDto) {
        Enrollment savedEnrollment = enrollmentService.createOrUpdateEnrollment(Optional.empty(), enrollmentDto);
        return ResponseEntity.ok(savedEnrollment);
    }

    @ApiOperation(value = "Update an existing enrollment")
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(
            @ApiParam(value = "ID of the enrollment to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody CreateUpdateEnrollmentDto enrollmentDto) {
        Enrollment updatedEnrollment = enrollmentService.createOrUpdateEnrollment(Optional.of(id), enrollmentDto);
        return ResponseEntity.ok(updatedEnrollment);
    }

    @ApiOperation(value = "Delete an enrollment by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @ApiParam(value = "ID of the enrollment to delete", required = true)
            @PathVariable String id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Import enrollments from a file")
    @PostMapping("/import")
    public ResponseEntity<Void> importEnrollments(@RequestParam("file") MultipartFile file) throws IOException {
        enrollmentService.importEnrollments(file.getInputStream());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Export enrollments to a file")
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportEnrollments() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        enrollmentService.exportEnrollments(outputStream);
        byte[] bytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename("enrollments.csv")
                .build();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }
}