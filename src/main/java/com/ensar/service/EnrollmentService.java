package com.ensar.service;

import com.ensar.entity.Enrollment;
import com.ensar.repository.EnrollmentRepository;
import com.ensar.request.dto.CreateUpdateEnrollmentDto;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment getEnrollmentById(String id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment with id " + id + " not found."));
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment createOrUpdateEnrollment(Optional<String> enrollmentId, CreateUpdateEnrollmentDto createUpdateEnrollmentDto) {
        Enrollment enrollment = enrollmentId
                .flatMap(enrollmentRepository::findById)
                .orElse(new Enrollment());
        enrollment.setEnrollmentName(createUpdateEnrollmentDto.getEnrollmentName());
        enrollment.setRemarks(createUpdateEnrollmentDto.getRemarks());
        enrollment.setEnrollmentDate(createUpdateEnrollmentDto.getEnrollmentDate());
        enrollment.setLastModifiedDate(createUpdateEnrollmentDto.getLastModifiedDate());
        enrollment.setStatus(Enrollment.EnrollmentStatus.valueOf(createUpdateEnrollmentDto.getStatus().name()));

        return enrollmentRepository.save(enrollment);
    }

    public String deleteEnrollment(String id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("Enrollment with id " + id + " not found.");
        }

        enrollmentRepository.deleteById(id);
        return "Enrollment with id " + id + " successfully deleted.";
    }

    public void importEnrollments(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<Enrollment> enrollments = new ArrayList<>();
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

            Enrollment enrollment = new Enrollment();
            try {
                String enrollmentName = fields[headerMap.get("enrollmentname")].trim();
                String remarks = fields[headerMap.get("remarks")].trim();
                String status = fields[headerMap.get("status")].trim();
                String enrollmentDateStr = fields[headerMap.get("enrollmentdate")].trim();

                // Validate field sizes
                if (enrollmentName.length() > 50) {
                    throw new IllegalArgumentException("Enrollment Name exceeds maximum length of 50 characters");
                }
                if (remarks.length() > 50) {
                    throw new IllegalArgumentException("Remarks exceed maximum length of 50 characters");
                }

                enrollment.setEnrollmentName(enrollmentName);
                enrollment.setRemarks(remarks);
                enrollment.setStatus(Enrollment.EnrollmentStatus.valueOf(status));

                // Set enrollment date
                if (!enrollmentDateStr.isEmpty()) {
                    enrollment.setEnrollmentDate(Date.valueOf(enrollmentDateStr));
                }

            } catch (IllegalArgumentException e) {
                throw new IOException("Error processing line: " + line + " - " + e.getMessage(), e);
            } catch (Exception e) {
                throw new IOException("Unexpected error processing line: " + line, e);
            }

            enrollments.add(enrollment);
        }
        enrollmentRepository.saveAll(enrollments);
    }

    public void exportEnrollments(OutputStream outputStream) throws IOException {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("EnrollmentName,Remarks,Status,EnrollmentDate\n");

        // Define the date format you want
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Enrollment enrollment : enrollments) {
            String enrollmentDateStr = enrollment.getEnrollmentDate() != null
                    ? dateFormat.format(enrollment.getEnrollmentDate())
                    : "";

            writer.write(String.format("%s,%s,%s,%s\n",
                    enrollment.getEnrollmentName(),
                    enrollment.getRemarks(),
                    enrollment.getStatus().name(),
                    enrollmentDateStr));
        }
        writer.flush();
    }
}