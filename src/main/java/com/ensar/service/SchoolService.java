package com.ensar.service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.School;
import com.ensar.repository.SchoolRepository;
import com.ensar.request.dto.CreateUpdateSchoolDto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School getSchoolById(String id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);
        if (!schoolOptional.isPresent()) {
            throw new RuntimeException("School with id " + id + " not found.");
        }
        return schoolOptional.get();
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School createOrUpdateSchool(Optional<String> schoolId, CreateUpdateSchoolDto createUpdateSchoolDto) {
        School school;
        if (schoolId.isPresent()) {
            Optional<School> schoolOptional = schoolRepository.findById(schoolId.get());
            if (!schoolOptional.isPresent()) {
                throw new RuntimeException("School with id " + schoolId.get() + " not found.");
            }
            school = schoolOptional.get();
        } else {
            school = new School();
        }

        school.setName(createUpdateSchoolDto.getName());
        school.setCity(createUpdateSchoolDto.getCity());
        school.setAddress(createUpdateSchoolDto.getAddress());
        school.setZipcode(createUpdateSchoolDto.getZipcode());
        school.setPhoneNumber(createUpdateSchoolDto.getPhoneNumber());

        return schoolRepository.save(school);
    }

    public String deleteSchool(String id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);
        if (!schoolOptional.isPresent()) {
            throw new RuntimeException("School with id " + id + " not found.");
        }
        schoolRepository.delete(schoolOptional.get());
        return "School with id " + id + " successfully deleted.";
    }

    public void importSchools(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<School> schools = new ArrayList<>();
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

            School school = new School();
            try {
                String name = fields[headerMap.get("name")];
                String city = fields[headerMap.get("city")];
                String address = fields[headerMap.get("address")];
                String zipcode = fields[headerMap.get("zipcode")];
                String phoneNumber = fields[headerMap.get("phone_number")];

                school.setName(name);
                school.setCity(city);
                school.setAddress(address);
                school.setZipcode(zipcode);
                school.setPhoneNumber(phoneNumber);
            } catch (Exception e) {
                throw new IOException("Error processing line: " + line, e);
            }

            schools.add(school);
        }
        schoolRepository.saveAll(schools);
    }

    public void exportSchools(OutputStream outputStream) throws IOException {
        List<School> schools = schoolRepository.findAll();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("Name,City,Address,Zipcode,PhoneNumber\n");
        for (School school : schools) {
            writer.write(String.format("%s,%s,%s,%s,%s\n", school.getName(), school.getCity(), school.getAddress(), school.getZipcode(), school.getPhoneNumber()));
        }
        writer.flush();
    }
}
