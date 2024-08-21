package com.ensar.service;

import com.ensar.entity.Syllabus;
import com.ensar.repository.CourseRepository;
import com.ensar.repository.SyllabusRepository;
import com.ensar.repository.TrainerRepository;
import com.ensar.request.dto.CreateUpdateSyllabusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SyllabusService {

    private final SyllabusRepository syllabusRepository;
    
    @Autowired
    public SyllabusService(SyllabusRepository syllabusRepository, CourseRepository courseRepository, TrainerRepository trainerRepository) {
        this.syllabusRepository = syllabusRepository;
    }

    public Syllabus createOrUpdateSyllabus(Optional<String> syllabusId, CreateUpdateSyllabusDto syllabusDto) {
        Syllabus syllabus;
        if (syllabusId.isEmpty() || syllabusId.get().isEmpty()) {
            syllabus = new Syllabus();
            syllabus.setId(UUID.randomUUID().toString());
        } else {
            syllabus = syllabusRepository.findById(syllabusId.get())
                    .orElseThrow(() -> new RuntimeException("Syllabus with id " + syllabusId.get() + " not found"));
        }

        syllabus.setContent(syllabusDto.getContent());
        syllabus.setResourceLink(syllabusDto.getResourceLink());
        syllabus.setCourse(syllabusDto.getCourse());
        syllabus.setCourseName(syllabusDto.getCourseName());
        syllabus.setDescription(syllabusDto.getDescription());
        syllabus.setSemester(syllabusDto.getSemester());
        syllabus.setAcademicYear(syllabusDto.getAcademicYear());
        syllabus.setTrainer(syllabusDto.getTrainer());

        return syllabusRepository.save(syllabus);
    }

    public Syllabus getSyllabusById(String syllabusId) {
        return syllabusRepository.findById(syllabusId)
                .orElseThrow(() -> new RuntimeException("Syllabus with id " + syllabusId + " not found"));
    }

    public List<Syllabus> getAllSyllabi() {
        return syllabusRepository.findAll();
    }

    public void deleteSyllabus(String syllabusId) {
        Syllabus syllabus = syllabusRepository.findById(syllabusId)
                .orElseThrow(() -> new RuntimeException("Syllabus with id " + syllabusId + " not found"));
        syllabusRepository.delete(syllabus);
    }
}
