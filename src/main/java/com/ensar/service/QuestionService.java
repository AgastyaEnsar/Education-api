package com.ensar.service;

import com.ensar.entity.Question;
import com.ensar.repository.QuestionRepository;
import com.ensar.request.dto.CreateUpdateQuestionDto;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@Log4j2
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getQuestionById(String id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question with id " + id + " not found."));
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question createOrUpdateQuestion(Optional<String> questionId, CreateUpdateQuestionDto createUpdateQuestionDto) {
        Question question = questionId.map(id -> questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question with id " + id + " not found.")))
                .orElse(new Question());

        question.setQuestionText(createUpdateQuestionDto.getQuestionText());
        question.setQuestionType(createUpdateQuestionDto.getQuestionType());
        question.setMarks(createUpdateQuestionDto.getMarks());
        question.setDifficultyLevel(createUpdateQuestionDto.getDifficultyLevel());
        question.setCategoryName(createUpdateQuestionDto.getCategoryName());

        if (!questionId.isPresent()) {
            question.setId(UUID.randomUUID().toString());
        }

        return questionRepository.save(question);
    }

    public void deleteQuestion(String id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question with id " + id + " not found.");
        }
        questionRepository.deleteById(id);
    }

    @SuppressWarnings("removal")
    public void importQuestions(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<Question> questions = new ArrayList<>();
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

            Question question = new Question();
            try {
                String questionText = fields[headerMap.get("questiontext")];
                String questionType = fields[headerMap.get("questiontype")];
                String marks = fields[headerMap.get("marks")];
                String difficultyLevel = fields[headerMap.get("difficultylevel")];
                String categoryName = fields[headerMap.get("categoryname")];

                question.setQuestionText(questionText);
                question.setQuestionType(questionType);
                question.setMarks(Integer.parseInt(marks));
                question.setDifficultyLevel(difficultyLevel);
                question.setCategoryName(categoryName);
                question.setId(UUID.randomUUID().toString());
            } catch (Exception e) {
                throw new IOException("Error processing line: " + line, e);
            }

            questions.add(question);
        }
        questionRepository.saveAll(questions);
    }

    public void exportQuestions(OutputStream outputStream) throws IOException {
        List<Question> questions = questionRepository.findAll();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("QuestionText,QuestionType,Marks,DifficultyLevel,CategoryName\n");
        for (Question question : questions) {
            writer.write(String.format("%s,%s,%d,%s,%d\n", question.getQuestionText(), question.getQuestionType(), question.getMarks(), question.getDifficultyLevel(), question.getCategoryName()));
        }
        writer.flush();
    }
}
