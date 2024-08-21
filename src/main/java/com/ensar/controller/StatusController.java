package com.ensar.controller;

import com.ensar.entity.Status;
import com.ensar.request.dto.CreateUpdateStatusDto;
import com.ensar.service.StatusService;
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

@Api(tags = "Status Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/statuses")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @ApiOperation(value = "Get status by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(
            @ApiParam(value = "ID of the status to retrieve", required = true)
            @PathVariable String id) {
        Status status = statusService.getStatusById(id);
        return ResponseEntity.ok(status);
    }

    @ApiOperation(value = "Get all statuses")
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Status>>> getAllStatuses() {
        List<Status> statuses = statusService.getAllStatuses();

        Map<String, List<Status>> response = new HashMap<>();
        response.put("statuses", statuses);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new status")
    @PostMapping("/")
    public ResponseEntity<Status> createStatus(
            @Valid @RequestBody CreateUpdateStatusDto statusDto) {
        Status savedStatus = statusService.createOrUpdateStatus(Optional.empty(), statusDto);
        return ResponseEntity.ok(savedStatus);
    }

    @ApiOperation(value = "Update an existing status")
    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(
            @ApiParam(value = "ID of the status to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody CreateUpdateStatusDto statusDto) {
        Status updatedStatus = statusService.createOrUpdateStatus(Optional.of(id), statusDto);
        return ResponseEntity.ok(updatedStatus);
    }

    @ApiOperation(value = "Delete a status by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(
            @ApiParam(value = "ID of the status to delete", required = true)
            @PathVariable String id) {
        statusService.deleteStatus(id);
        return ResponseEntity.ok().build();
    }
}
