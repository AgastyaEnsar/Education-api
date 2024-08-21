package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.Topic;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {

    List<Topic> findByCategory(String category);

    List<Topic> findByLevel(Topic.Level level);

    List<Topic> findByCategoryAndLevel(String category, Topic.Level level);

}
