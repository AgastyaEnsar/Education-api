package com.ensar.controller;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ensar.entity.School;
import com.ensar.request.dto.CreateUpdateSchoolDto;
import com.ensar.service.SchoolService;

import org.springframework.security.access.prepost.PreAuthorize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = "School Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<School> getSchoolById(@NotBlank @PathVariable("id") String id) {
        School school = schoolService.getSchoolById(id);
        if (school == null) {
            throw new ResourceNotFoundException("School with id " + id + " not found.");
        }
        return ResponseEntity.ok(school);
    }

    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Map<String, List<School>>> getAllSchools() {
    	 List<School> schools = schoolService.getAllSchools();
        
        Map<String, List<School>> response = new HashMap<>();
        response.put("schools", schools);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<School> createSchool(@Valid @RequestBody CreateUpdateSchoolDto createUpdateSchoolDto) {
        return ResponseEntity.ok(schoolService.createOrUpdateSchool(Optional.empty(), createUpdateSchoolDto));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<School> updateSchool(@NotBlank @PathVariable("id") String id,
                                               @Valid @RequestBody CreateUpdateSchoolDto createUpdateSchoolDto) {
        return ResponseEntity.ok(schoolService.createOrUpdateSchool(Optional.of(id), createUpdateSchoolDto));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<String> deleteSchool(@NotBlank @PathVariable("id") String id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.ok("School with id " + id + " has been successfully deleted.");
    }
    
    @PostMapping("/import")
    public ResponseEntity<Void> importSchools(@RequestParam("file") MultipartFile file) throws IOException {
        schoolService.importSchools(file.getInputStream());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportSchools() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        schoolService.exportSchools(outputStream);
        byte[] bytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "schools.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }
}
