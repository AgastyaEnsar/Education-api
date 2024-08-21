package com.ensar.controller;

import com.ensar.entity.Trainer;
import com.ensar.request.dto.CreateUpdateTrainerDto;
import com.ensar.service.TrainerService;
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

@Api(tags = "Trainer Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @ApiOperation(value = "Get trainer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(
            @ApiParam(value = "ID of the trainer to retrieve", required = true)
            @PathVariable String id) {
        Trainer trainer = trainerService.getTrainerById(id);
        return ResponseEntity.ok(trainer);
    }

    @ApiOperation(value = "Get all trainers")
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Trainer>>> getAllTrainers() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        
        Map<String, List<Trainer>> response = new HashMap<>();
        response.put("trainers", trainers);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new trainer")
    @PostMapping("/")
    public ResponseEntity<Trainer> createTrainer(
            @Valid @RequestBody CreateUpdateTrainerDto trainerDto) {
        Trainer savedTrainer = trainerService.createOrUpdateTrainer(Optional.empty(), trainerDto);
        return ResponseEntity.ok(savedTrainer);
    }

    @ApiOperation(value = "Update an existing trainer")
    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(
            @ApiParam(value = "ID of the trainer to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody CreateUpdateTrainerDto trainerDto) {
        Trainer updatedTrainer = trainerService.createOrUpdateTrainer(Optional.of(id), trainerDto);
        return ResponseEntity.ok(updatedTrainer);
    }

    @ApiOperation(value = "Delete a trainer by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(
            @ApiParam(value = "ID of the trainer to delete", required = true)
            @PathVariable String id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.ok().build();
    }
}
