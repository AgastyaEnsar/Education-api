package com.ensar.repository;

import com.ensar.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String> {

    List<Trainer> findByDepartment(String department);

    List<Trainer> findBySpecialization(String specialization);

    List<Trainer> findByGender(Trainer.Gender gender);

    List<Trainer> findByExperienceGreaterThanEqual(Integer experience);

    List<Trainer> findByCompany(String company);

}
