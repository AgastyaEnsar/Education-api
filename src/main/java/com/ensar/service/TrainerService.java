package com.ensar.service;

import com.ensar.entity.Trainer;
import com.ensar.repository.TrainerRepository;
import com.ensar.request.dto.CreateUpdateTrainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer createOrUpdateTrainer(Optional<String> trainerId, CreateUpdateTrainerDto trainerDto) {
        Trainer trainer;
        if (trainerId.isEmpty() || trainerId.get().isEmpty()) {
            trainer = new Trainer();
            trainer.setId(UUID.randomUUID().toString());
        } else {
            trainer = trainerRepository.findById(trainerId.get())
                    .orElseThrow(() -> new RuntimeException("Trainer with id " + trainerId.get() + " not found"));
        }

        trainer.setName(trainerDto.getName());
        trainer.setCompany(trainerDto.getCompany());
        trainer.setDepartment(trainerDto.getDepartment());
        trainer.setSpecialization(trainerDto.getSpecialization());
        trainer.setEmail(trainerDto.getEmail());
        trainer.setPhoneNumber(trainerDto.getPhoneNumber());
        trainer.setAddress(trainerDto.getAddress());
        trainer.setDateOfBirth(trainerDto.getDateOfBirth());
        trainer.setGender(Trainer.Gender.valueOf(trainerDto.getGender()));
        trainer.setExperience(trainerDto.getExperience());
        trainer.setBio(trainerDto.getBio());

        return trainerRepository.save(trainer);
    }

    public Trainer getTrainerById(String trainerId) {
        return trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer with id " + trainerId + " not found"));
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public void deleteTrainer(String trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer with id " + trainerId + " not found"));
        trainerRepository.delete(trainer);
    }
}
