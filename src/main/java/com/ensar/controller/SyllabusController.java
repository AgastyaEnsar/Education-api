package com.ensar.controller;

import com.ensar.entity.Syllabus;
import com.ensar.request.dto.CreateUpdateSyllabusDto;
import com.ensar.service.SyllabusService;
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

@Api(tags = "Syllabus Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/syllabi")
public class SyllabusController {

    private final SyllabusService syllabusService;

    @Autowired
    public SyllabusController(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    @ApiOperation(value = "Get syllabus by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Syllabus> getSyllabusById(
            @ApiParam(value = "ID of the syllabus to retrieve", required = true)
            @PathVariable String id) {
        Syllabus syllabus = syllabusService.getSyllabusById(id);
        return ResponseEntity.ok(syllabus);
    }

    @ApiOperation(value = "Get all syllabi")
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Syllabus>>> getAllSyllabi() {
        List<Syllabus> syllabi = syllabusService.getAllSyllabi();

        Map<String, List<Syllabus>> response = new HashMap<>();
        response.put("syllabi", syllabi);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new syllabus")
    @PostMapping("/")
    public ResponseEntity<Syllabus> createSyllabus(
            @Valid @RequestBody CreateUpdateSyllabusDto syllabusDto) {
        Syllabus savedSyllabus = syllabusService.createOrUpdateSyllabus(Optional.empty(), syllabusDto);
        return ResponseEntity.ok(savedSyllabus);
    }

    @ApiOperation(value = "Update an existing syllabus")
    @PutMapping("/{id}")
    public ResponseEntity<Syllabus> updateSyllabus(
            @ApiParam(value = "ID of the syllabus to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody CreateUpdateSyllabusDto syllabusDto) {
        Syllabus updatedSyllabus = syllabusService.createOrUpdateSyllabus(Optional.of(id), syllabusDto);
        return ResponseEntity.ok(updatedSyllabus);
    }

    @ApiOperation(value = "Delete a syllabus by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSyllabus(
            @ApiParam(value = "ID of the syllabus to delete", required = true)
            @PathVariable String id) {
        syllabusService.deleteSyllabus(id);
        return ResponseEntity.ok().build();
    }
}
