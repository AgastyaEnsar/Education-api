package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.TopicsAudit;

import java.util.List;

@Repository
public interface TopicsAuditRepository extends JpaRepository<TopicsAudit, String> {

    List<TopicsAudit> findByUserId(String userId);

    List<TopicsAudit> findByTopicId(String topicId);

    List<TopicsAudit> findByResult(TopicsAudit.Result result);

} 
