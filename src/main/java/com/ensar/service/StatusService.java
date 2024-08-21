package com.ensar.service;

import com.ensar.entity.Status;
import com.ensar.repository.StatusRepository;
import com.ensar.request.dto.CreateUpdateStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status createOrUpdateStatus(Optional<String> statusId, CreateUpdateStatusDto statusDto) {
        Status status;
        if (statusId.isEmpty() || statusId.get().isEmpty()) {
            status = new Status();
            status.setId(UUID.randomUUID().toString());
        } else {
            status = statusRepository.findById(statusId.get())
                    .orElseThrow(() -> new RuntimeException("Status with id " + statusId.get() + " not found"));
        }

        status.setName(statusDto.getName());
        status.setDescription(statusDto.getDescription());

        return statusRepository.save(status);
    }

    public Status getStatusById(String statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status with id " + statusId + " not found"));
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public void deleteStatus(String statusId) {
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status with id " + statusId + " not found"));
        statusRepository.delete(status);
    }
}
