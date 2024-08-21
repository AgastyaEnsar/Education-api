package com.ensar.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensar.entity.Topic;
import com.ensar.repository.TopicRepository;
import com.ensar.request.dto.CreateUpdateTopicDto;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic getTopicById(String id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);

        if (!topicOptional.isPresent())
            throw new RuntimeException("Topic with " + id + " not found.");
        return topicOptional.get();
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic createOrUpdateTopic(Optional<String> topicId, CreateUpdateTopicDto createUpdateTopicDto) {
        Topic topic;
        if (topicId.isPresent()) {
            topic = topicRepository.findById(topicId.get())
                    .orElseThrow(() -> new RuntimeException("Topic with id " + topicId.get() + " not found"));
        } else {
            topic = new Topic();
        }

        topic.setName(createUpdateTopicDto.getName());
        topic.setCategory(createUpdateTopicDto.getCategory());
        topic.setLevel(Topic.Level.valueOf(createUpdateTopicDto.getLevel().toUpperCase()));

        return topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic with id " + id + " not found"));
        topicRepository.delete(topic);
    }
}

