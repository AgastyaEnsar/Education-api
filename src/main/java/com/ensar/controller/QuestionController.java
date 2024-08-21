package com.ensar.controller;

import com.ensar.entity.Question;
import com.ensar.request.dto.CreateUpdateQuestionDto;
import com.ensar.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = "Question Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ApiOperation(value = "Get question by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(
            @ApiParam(value = "ID of the question to retrieve", required = true)
            @PathVariable String id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @ApiOperation(value = "Get all questions")
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Question>>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();

        Map<String, List<Question>> response = new HashMap<>();
        response.put("questions", questions);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new question")
    @PostMapping("/")
    public ResponseEntity<Question> createQuestion(
            @Valid @RequestBody CreateUpdateQuestionDto questionDto) {
        Question savedQuestion = questionService.createOrUpdateQuestion(Optional.empty(), questionDto);
        return ResponseEntity.ok(savedQuestion);
    }

    @ApiOperation(value = "Update an existing question")
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(
            @ApiParam(value = "ID of the question to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody CreateUpdateQuestionDto questionDto) {
        Question updatedQuestion = questionService.createOrUpdateQuestion(Optional.of(id), questionDto);
        return ResponseEntity.ok(updatedQuestion);
    }

    @ApiOperation(value = "Delete a question by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(
            @ApiParam(value = "ID of the question to delete", required = true)
            @PathVariable String id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Import questions from a CSV file")
    @PostMapping("/import")
    public ResponseEntity<Void> importQuestions(@RequestParam("file") MultipartFile file) throws IOException {
        questionService.importQuestions(file.getInputStream());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Export questions to a CSV file")
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportQuestions() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        questionService.exportQuestions(outputStream);
        byte[] bytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename("questions.csv")
                .build();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }
}
