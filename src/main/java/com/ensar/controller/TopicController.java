package com.ensar.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ensar.entity.Topic;
import com.ensar.request.dto.CreateUpdateTopicDto;
import com.ensar.service.TopicService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Api(tags = "Topic Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable("id") String id) {
        Topic topic = topicService.getTopicById(id);
        return ResponseEntity.ok(topic);
    }

    @GetMapping("/")
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Topic> createTopic(
            @Valid @RequestBody CreateUpdateTopicDto createUpdateTopicDto) {
        Topic topic = topicService.createOrUpdateTopic(Optional.empty(), createUpdateTopicDto);
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Topic> updateTopic(@PathVariable String id,
                                             @Valid @RequestBody CreateUpdateTopicDto createUpdateTopicDto) {
        Topic topic = topicService.createOrUpdateTopic(Optional.of(id), createUpdateTopicDto);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteTopic(@PathVariable("id") String id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok().build();
    }
}
