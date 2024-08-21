package com.ensar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensar.entity.Project;
import com.ensar.entity.User;
import com.ensar.repository.ProjectRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateProjectDto;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project createOrUpdateProject(Optional<String> projectId, CreateUpdateProjectDto projectDto) {
        Project project;
        if (projectId.isPresent()) {
            project = projectRepository.findById(projectId.get())
                .orElseThrow(() -> new RuntimeException("Project with id " + projectId.get() + " not found"));
        } else {
            project = new Project();
        }

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());

        User manager = userRepository.findById(projectDto.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager with id " + projectDto.getManagerId() + " not found"));
        project.setManager(manager);

        return projectRepository.save(project);
    }

    public Project getProjectById(String projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project with id " + projectId + " not found"));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByManager(String managerId) {
        return projectRepository.findByManagerId(managerId);
    }

    public void deleteProject(String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project with id " + projectId + " not found"));
        projectRepository.delete(project);
    }
}
