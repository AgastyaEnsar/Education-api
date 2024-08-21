package com.ensar.controller;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ensar.entity.TopicsAudit;
import com.ensar.request.dto.CreateUpdateTopicsAuditDto;
import com.ensar.service.TopicsAuditService;

import java.util.List;
import java.util.Optional;

@Api(tags = "Topics Audit Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/topics-audit")
public class TopicsAuditController {

    private final TopicsAuditService topicsAuditService;

    @Autowired
    public TopicsAuditController(TopicsAuditService topicsAuditService) {
        this.topicsAuditService = topicsAuditService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TopicsAudit> getAuditById(@PathVariable("id") String auditId) {
        return ResponseEntity.ok(topicsAuditService.getAuditById(auditId));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<List<TopicsAudit>> getAllAudits() {
        return ResponseEntity.ok(topicsAuditService.getAllAudits());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<List<TopicsAudit>> getAuditsByUserId(@NotBlank @PathVariable("userId") String userId) {
        return ResponseEntity.ok(topicsAuditService.getAuditsByUserId(userId));
    }

    @GetMapping("/topic/{topicId}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<List<TopicsAudit>> getAuditsByTopicId(@NotBlank @PathVariable("topicId") String topicId) {
        return ResponseEntity.ok(topicsAuditService.getAuditsByTopicId(topicId));
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<TopicsAudit> createAudit(
            @Valid @RequestBody CreateUpdateTopicsAuditDto createUpdateTopicsAuditDto) {
        return ResponseEntity.ok(topicsAuditService.createOrUpdateAudit(Optional.empty(), createUpdateTopicsAuditDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<TopicsAudit> updateAudit(@PathVariable String id,
                                                   @Valid @RequestBody CreateUpdateTopicsAuditDto createUpdateTopicsAuditDto) {
        return ResponseEntity.ok(topicsAuditService.createOrUpdateAudit(Optional.of(id), createUpdateTopicsAuditDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAudit(@PathVariable String id) {
        topicsAuditService.deleteAudit(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<TopicsAudit>> getMyAudits() {
        String userId = topicsAuditService.getLoggedInUser().getId();
        return ResponseEntity.ok(topicsAuditService.getAuditsByUserId(userId));
    }
}
