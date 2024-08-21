package com.ensar.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ensar.entity.Project;
import com.ensar.request.dto.CreateUpdateProjectDto;
import com.ensar.service.ProjectService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Api(tags = "Project Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") String id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<Project>> getProjectsByManager(@PathVariable("managerId") String managerId) {
        List<Project> projects = projectService.getProjectsByManager(managerId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Project> createProject(
            @Valid @RequestBody CreateUpdateProjectDto createUpdateProjectDto) {
        Project project = projectService.createOrUpdateProject(Optional.empty(), createUpdateProjectDto);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Project> updateProject(@PathVariable String id,
                                                 @Valid @RequestBody CreateUpdateProjectDto createUpdateProjectDto) {
        Project project = projectService.createOrUpdateProject(Optional.of(id), createUpdateProjectDto);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}
