package com.ensar.service;

import com.ensar.entity.Student;
import com.ensar.repository.StudentRepository;
import com.ensar.request.dto.CreateUpdateStudentDto;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(String id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (!studentOptional.isPresent())
            throw new RuntimeException("Student with id " + id + " not found.");

        return studentOptional.get();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createOrUpdateStudent(Optional<String> studentId, CreateUpdateStudentDto createUpdateStudentDto) {
        Student student;
        if (studentId.isPresent()) {
            Optional<Student> studentOptional = studentRepository.findById(studentId.get());
            if (!studentOptional.isPresent())
                throw new RuntimeException("Student with id " + studentId.get() + " not found.");
            student = studentOptional.get();
        } else {
            student = new Student();
            // Assume the ID will be generated automatically by the database or another mechanism.
        }
        student.setFirstName(createUpdateStudentDto.getFirstName());
        student.setLastName(createUpdateStudentDto.getLastName());
        student.setEmail(createUpdateStudentDto.getEmail());
        student.setEnrollmentDate(createUpdateStudentDto.getEnrollmentDate());
        // student.setDisabled(createUpdateStudentDto.isDisabled());
        student.setDateOfBirth(createUpdateStudentDto.getDateOfBirth());
        student.setPhoneNumber(createUpdateStudentDto.getPhoneNumber());
        student.setAddress(createUpdateStudentDto.getAddress());
        student.setCity(createUpdateStudentDto.getCity());
        student.setZipcode(createUpdateStudentDto.getZipcode());
        return studentRepository.save(student);
    }

    public String deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student with id " + id + " not found.");
        }
        
        studentRepository.deleteById(id);
        return "Student with id " + id + " successfully deleted.";
    }

    public void exportStudents(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=students.csv");

        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            String[] header = { "ID", "FirstName", "LastName", "Email", "EnrollmentDate" };
            writer.writeNext(header);

            List<Student> students = studentRepository.findAll();
            for (Student student : students) {
                String[] data = {
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getEnrollmentDate().toString(),
                        student.getDateOfBirth().toString(),
                        student.getPhoneNumber(),
                        student.getAddress(),
                        student.getCity(),
                        student.getZipcode(),
//                        Boolean.toString(student.isDisabled())
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while exporting students", e);
        }
    }

    public void importStudents(MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> data = reader.readAll();

            // Skip the header row
            if (!data.isEmpty()) {
                data.remove(0);
            }

            for (String[] row : data) {
                if (row.length < 10) continue; // Skip incomplete rows

                CreateUpdateStudentDto dto = new CreateUpdateStudentDto();
                dto.setFirstName(row[1]);
                dto.setLastName(row[2]);
                dto.setEmail(row[3]);
                dto.setEnrollmentDate(Date.valueOf(row[4]));
                dto.setDateOfBirth(Date.valueOf(row[5]));
                dto.setPhoneNumber(row[6]);
                dto.setAddress(row[7]);
                dto.setCity(row[8]);
                dto.setZipcode(row[9]);
//                dto.setDisabled(Boolean.parseBoolean(row[10]));

                createOrUpdateStudent(Optional.empty(), dto);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error occurred while importing students", e);
        }
    }
}
