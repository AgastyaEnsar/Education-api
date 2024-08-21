

package com.ensar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ensar.entity.LearningPlan;
import com.ensar.service.LearningPlanService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "Learning Plan Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/learningplan")
public class LearningPlanController {

    private final LearningPlanService learningPlanService;

    @Autowired
    public LearningPlanController(LearningPlanService learningPlanService) {
        this.learningPlanService = learningPlanService;
    }

    @PostMapping("/")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created learning plan"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<LearningPlan> createLearningPlan(
            @Valid @RequestBody LearningPlan learningPlanRequest
    ) {
        LearningPlan learningPlan = learningPlanService.createLearningPlan(
                learningPlanRequest.getTitle(),
                learningPlanRequest.getDescription(),
                learningPlanRequest.getCreatedBy()
        );
        return ResponseEntity.ok(learningPlan);
    }

    @PutMapping("/{id}")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated learning plan"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 404, message = "Learning plan not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<LearningPlan> updateLearningPlan(
            @PathVariable String id,
            @Valid @RequestBody LearningPlan learningPlanRequest
    ) {
        LearningPlan updatedLearningPlan = learningPlanService.updateLearningPlan(
                id,
                learningPlanRequest.getTitle(),
                learningPlanRequest.getDescription()
        );
        return ResponseEntity.ok(updatedLearningPlan);
    }

    @GetMapping("/{id}")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved learning plan"),
            @ApiResponse(code = 404, message = "Learning plan not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<LearningPlan> getLearningPlanById(@PathVariable String id) {
        LearningPlan learningPlan = learningPlanService.getLearningPlanById(id);
        return ResponseEntity.ok(learningPlan);
    }

    @GetMapping("/")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of learning plans"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Map<String, List<LearningPlan>>> getAllLearningPlans() {
        List<LearningPlan> learningPlansList = learningPlanService.getAllLearningPlans();
        Map<String, List<LearningPlan>> response = new HashMap<>();
        response.put("learningPlans", learningPlansList);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted learning plan"),
            @ApiResponse(code = 404, message = "Learning plan not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Void> deleteLearningPlanById(@PathVariable String id) {
        learningPlanService.deleteLearningPlanById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importLearningPlans(@RequestParam("file") MultipartFile file) throws IOException {
        learningPlanService.importLearningPlans(file.getInputStream());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportLearningPlans() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        learningPlanService.exportLearningPlans(outputStream);
        byte[] bytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename("learningplans.csv")
                .build();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }
}

