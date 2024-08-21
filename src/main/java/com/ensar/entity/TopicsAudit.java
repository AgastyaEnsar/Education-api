package com.ensar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity(name = "topicsAudit")
@Data
@EqualsAndHashCode(callSuper = true)
public class TopicsAudit extends BaseEntity {

    public enum Result { HIGH, MEDIUM, BASIC, NA, NO }

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "topic_id", nullable = false)
    private String topicId;

    @Column(name = "result", nullable = false)
    @Enumerated(EnumType.STRING)
    private Result result;

    @Column(name = "evidence")
    private String evidence;

    @Column(name = "created_date_time", nullable = false)
    private Timestamp createdDateTime;

    @Column(name = "last_updated_date_time")
    private Timestamp lastUpdatedDateTime;

    // Assuming there are relationships with User and Topic entities
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Topic topic;
}
