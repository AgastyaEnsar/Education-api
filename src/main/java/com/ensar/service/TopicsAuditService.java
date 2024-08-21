package com.ensar.service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ensar.entity.TopicsAudit;
import com.ensar.entity.User;
import com.ensar.repository.TopicsAuditRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateTopicsAuditDto;
import com.ensar.security.VizenUserDetails;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class TopicsAuditService {

    private final TopicsAuditRepository topicsAuditRepository;
    private final UserRepository userRepository;

    @Autowired
    public TopicsAuditService(TopicsAuditRepository topicsAuditRepository, UserRepository userRepository) {
        this.topicsAuditRepository = topicsAuditRepository;
        this.userRepository = userRepository;
    }

    public TopicsAudit getAuditById(String auditId) {
        return topicsAuditRepository.findById(auditId)
                .orElseThrow(() -> new RuntimeException("TopicsAudit with id " + auditId + " not found."));
    }

    public List<TopicsAudit> getAllAudits() {
        return topicsAuditRepository.findAll();
    }

    public List<TopicsAudit> getAuditsByUserId(String userId) {
        return topicsAuditRepository.findByUserId(userId);
    }

    public List<TopicsAudit> getAuditsByTopicId(String topicId) {
        return topicsAuditRepository.findByTopicId(topicId);
    }

    public TopicsAudit createOrUpdateAudit(Optional<String> auditId, CreateUpdateTopicsAuditDto createUpdateTopicsAuditDto) {
        TopicsAudit topicsAudit;
        if (auditId.isPresent()) {
            topicsAudit = topicsAuditRepository.findById(auditId.get())
                    .orElseThrow(() -> new RuntimeException("TopicsAudit with id " + auditId.get() + " not found."));
        } else {
            topicsAudit = new TopicsAudit();
        }

        topicsAudit.setUserId(createUpdateTopicsAuditDto.getUserId());
        topicsAudit.setTopicId(createUpdateTopicsAuditDto.getTopicId());
       // topicsAudit.setResult(createUpdateTopicsAuditDto.getResult());
        topicsAudit.setEvidence(createUpdateTopicsAuditDto.getEvidence());

        return topicsAuditRepository.save(topicsAudit);
    }

    public void deleteAudit(String auditId) {
        if (!topicsAuditRepository.existsById(auditId)) {
            throw new RuntimeException("TopicsAudit with id " + auditId + " not found.");
        }
        topicsAuditRepository.deleteById(auditId);
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
              //  .orElseThrow(() -> new RuntimeException("Logged-in user not found."));
    }
}
