



package com.ensar.service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.LearningPlan;
import com.ensar.repository.LearningPlanRepository;

import java.io.*;
import java.util.*;

@Service
@Log4j2
@Transactional
public class LearningPlanService {

    private final LearningPlanRepository learningPlanRepository;

    @Autowired
    public LearningPlanService(LearningPlanRepository learningPlanRepository) {
        this.learningPlanRepository = learningPlanRepository;
    }

    public LearningPlan createLearningPlan(String title, String description, String createdBy) {
        LearningPlan learningPlan = new LearningPlan();
        learningPlan.setId(UUID.randomUUID().toString());
        learningPlan.setTitle(title);
        learningPlan.setDescription(description);
        learningPlan.setCreatedBy(createdBy);
        learningPlanRepository.save(learningPlan);
        log.info("Created new Learning Plan with title: {}", title);
        return learningPlan;
    }

    public LearningPlan updateLearningPlan(String id, String title, String description) {
        Optional<LearningPlan> optionalLearningPlan = learningPlanRepository.findById(id);
        if (!optionalLearningPlan.isPresent()) {
            throw new RuntimeException("Learning Plan with id " + id + " not found.");
        }

        LearningPlan learningPlan = optionalLearningPlan.get();
        learningPlan.setTitle(title);
        learningPlan.setDescription(description);
        learningPlanRepository.save(learningPlan);
        log.info("Updated Learning Plan with id: {}", id);
        return learningPlan;
    }

    public LearningPlan getLearningPlanById(String id) {
        Optional<LearningPlan> optionalLearningPlan = learningPlanRepository.findById(id);
        if (!optionalLearningPlan.isPresent()) {
            throw new RuntimeException("Learning Plan with id " + id + " not found.");
        }

        return optionalLearningPlan.get();
    }

    public List<LearningPlan> getAllLearningPlans() {
        return learningPlanRepository.findAll();
    }

    public void deleteLearningPlanById(String id) {
        if (!learningPlanRepository.existsById(id)) {
            throw new RuntimeException("Learning Plan with id " + id + " not found.");
        }
        learningPlanRepository.deleteById(id);
        log.info("Deleted Learning Plan with id: {}", id);
    }

    public void importLearningPlans(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<LearningPlan> learningPlans = new ArrayList<>();
        Map<String, Integer> headerMap = new HashMap<>();

        if ((line = reader.readLine()) != null) {
            String[] headers = line.split(",");
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i].trim().toLowerCase(), i);
            }
        } else {
            throw new IOException("Empty CSV file");
        }
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");

            if (fields.length != headerMap.size()) {
                throw new IOException("Invalid CSV format. Each line must have the same number of fields as the header.");
            }

            LearningPlan learningPlan = new LearningPlan();
            try {
                String title = fields[headerMap.get("title")];
                String description = fields[headerMap.get("description")];
                String createdBy = fields[headerMap.get("createdby")];

                learningPlan.setId(UUID.randomUUID().toString());
                learningPlan.setTitle(title);
                learningPlan.setDescription(description);
                learningPlan.setCreatedBy(createdBy);
            } catch (Exception e) {
                throw new IOException("Error processing line: " + line, e);
            }

            learningPlans.add(learningPlan);
        }
        learningPlanRepository.saveAll(learningPlans);
    }

    public void exportLearningPlans(OutputStream outputStream) throws IOException {
        List<LearningPlan> learningPlans = learningPlanRepository.findAll();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("Title,Description,CreatedBy\n");
        for (LearningPlan learningPlan : learningPlans) {
            writer.write(String.format("%s,%s,%s\n", learningPlan.getTitle(), learningPlan.getDescription(), learningPlan.getCreatedBy()));
        }
        writer.flush();
    }
}

