DROP TABLE IF EXISTS timesheet;

CREATE TABLE timesheet
(
    id                  CHAR(36)    NOT NULL,
    user_id             CHAR(36)    NOT NULL,
    task_description    VARCHAR(255) NOT NULL,
    hours_spent         DECIMAL(5, 2) NOT NULL,
    task_date           DATE        NOT NULL,
    created_at          TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP   NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

SET @USER_ID_BHAKTA = (SELECT id FROM user WHERE email = 'bhakta@ensarsolutions.com');
SET @USER_ID_KALYAN = (SELECT id FROM user WHERE email = 'kalyan@ensarsolutions.com');
SET @USER_ID_SRIDHAR = (SELECT id FROM user WHERE email = 'sridharg@ensarsolutions.com');

  
-- Inserting into the `timesheet` table
INSERT INTO timesheet (id, user_id, task_description, hours_spent, task_date)
VALUES
(uuid(), @USER_ID_BHAKTA, 'Setup and configuration', 6.5, '2024-07-25'),
(uuid(), @USER_ID_BHAKTA, 'Database management', 4.0, '2024-07-26'),
(uuid(), @USER_ID_KALYAN, 'Frontend development', 5.0, '2024-07-25'),
(uuid(), @USER_ID_KALYAN, 'Backend integration', 7.0, '2024-07-26'),
(uuid(), @USER_ID_SRIDHAR, 'Project planning', 3.5, '2024-07-25'),
(uuid(), @USER_ID_SRIDHAR, 'Code optimization', 4.5, '2024-07-26');