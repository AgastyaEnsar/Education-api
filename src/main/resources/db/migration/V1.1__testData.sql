
set @ORG_ID = uuid();
INSERT INTO organization
(id, name, description, domain)
VALUES(@ORG_ID, 'Demo Org', 'Demo desc', 'demo-domain');

INSERT INTO forecast_dash_board
(id, organization_id, dash_board_id)
VALUES(uuid(), @ORG_ID, '');

SET @USER_ID_BHAKTA = uuid();
SET @USER_ID_KALYAN = uuid();
SET @USER_ID_SRIDHAR = uuid();


INSERT INTO `user`
( id, first_name, last_name, email, password, role_name, organization_id)
VALUES(@USER_ID_BHAKTA, 'Bhakta', 'Reddy', 'bhakta@ensarsolutions.com', '$2a$10$xVRF.RqfSJcJzExzhVqFl.geliI3URbG4ZLmyz5amMwIXU2f.uG6a', 'ROLE_SUPER_ADMIN', @ORG_ID);

INSERT INTO `user`
( id, first_name, last_name, email, role_name, organization_id)
VALUES(@USER_ID_KALYAN, 'Kalyan', 'K', 'kalyan@ensarsolutions.com', 'ROLE_SUPER_ADMIN', @ORG_ID);

INSERT INTO `user`
( id, first_name, last_name, email, role_name, organization_id)
VALUES(@USER_ID_SRIDHAR, 'Sridhar', 'P', 'sridharg@ensarsolutions.com', 'ROLE_SUPER_ADMIN', @ORG_ID);


SET @PROJECT_ID_1 = uuid();
SET @PROJECT_ID_2 = uuid();
SET @PROJECT_ID_3 = uuid();

INSERT INTO `projects`
( id, name, description, start_date, end_date, manager_id)
VALUES
(@PROJECT_ID_1, 'Project Alpha', 'A project to develop a new product line.', '2024-01-01', '2024-06-30', @USER_ID_BHAKTA),
(@PROJECT_ID_2, 'Project Beta', 'A project focused on market research and expansion.', '2024-02-15', NULL, @USER_ID_BHAKTA),
(@PROJECT_ID_3, 'Project Gamma', 'An internal project for infrastructure upgrades.', '2024-03-01', '2024-12-31', @USER_ID_BHAKTA);

SET @TOPIC_ID_1 = uuid();
SET @TOPIC_ID_2 = uuid();
SET @TOPIC_ID_3 = uuid();
SET @TOPIC_ID_4 = uuid();
SET @TOPIC_ID_5 = uuid();
SET @TOPIC_ID_6 = uuid();

INSERT INTO `topics` (`id`, `name`, `category`, `level`) 
VALUES 
( @TOPIC_ID_1, 'Data Analysis', 'Data Science', 'high'),
( @TOPIC_ID_2, 'Machine Learning', 'Artificial Intelligence', 'high'),
( @TOPIC_ID_3, 'Database Management', 'Information Technology', 'medium'),
( @TOPIC_ID_4, 'Cybersecurity', 'Information Security', 'high'),
( @TOPIC_ID_5, 'Web Development', 'Software Engineering', 'medium'),
( @TOPIC_ID_6, 'Digital Marketing', 'Marketing', 'low');

INSERT INTO topicsAudit (audit_id, user_id, topic_id, result, evidence) 
VALUES 
(uuid(), @USER_ID_BHAKTA,  @TOPIC_ID_1, 'high', 'Evidence supporting high rating'),
(uuid(), @USER_ID_BHAKTA,  @TOPIC_ID_2, 'medium', 'Moderate evidence supporting medium rating'),
(uuid(), @USER_ID_BHAKTA,  @TOPIC_ID_3, 'basic', 'Basic evidence available'),
(uuid(), @USER_ID_BHAKTA,  @TOPIC_ID_4, 'na', 'No applicable evidence found'),
(uuid(), @USER_ID_BHAKTA,  @TOPIC_ID_5, 'no', 'No evidence supporting the topic'); 

INSERT INTO `todo` (`id`, `title`, `description`, `due_date`, `status`, `priority`, `user_id`,`topic_id`)
VALUES (UUID(), 'Data Analysis', 'Basics of Data Analysis', '2024-08-01 10:00:00', 'not_started', 'medium', @USER_ID_SRIDHAR,@TOPIC_ID_1);


