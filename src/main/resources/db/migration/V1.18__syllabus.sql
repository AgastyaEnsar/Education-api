DROP TABLE IF EXISTS `syllabus`;

-- Create the updated syllabus table
CREATE TABLE `syllabus` (
    id                     CHAR(36)    NOT NULL,
    content                TEXT        NOT NULL,
    resource_link          VARCHAR(255) NOT NULL,
    course_id              CHAR(36)    NOT NULL,
    course_name            VARCHAR(100) NOT NULL,  -- Added course_name field
    description            TEXT        NOT NULL,
    semester               VARCHAR(20) NOT NULL,
    academic_year          VARCHAR(9)  NOT NULL,
    trainer_id             CHAR(36)    NOT NULL,  -- Added trainer_id field
    created_date_time      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_date_time TIMESTAMP   NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (trainer_id) REFERENCES trainer(id) -- Foreign key reference to trainer
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;

--
-- Set UUIDs for Syllabus records
SET @COURSE_ID_1 = (SELECT id FROM course WHERE name = 'Python');
SET @COURSE_ID_2 = (SELECT id FROM course WHERE name = 'Amazon Web Services');
SET @COURSE_ID_3 = (SELECT id FROM course WHERE name = 'Database Management Systems');

SET @TRAINER_ID_1 = (SELECT id FROM trainer WHERE name = 'Agastya');  -- Example trainer
SET @TRAINER_ID_2 = (SELECT id FROM trainer WHERE name = 'Vineeth');  -- Example trainer
SET @TRAINER_ID_3 = (SELECT id FROM trainer WHERE name = 'Pavan');  -- Example trainer

-- Inserting into the `syllabus` table
INSERT INTO syllabus (id, content, resource_link, course_id, course_name, description, semester, academic_year, trainer_id, created_date_time, last_updated_date_time)
VALUES
(uuid(), 'Basic Algebra', 'www.math101resources.com', @COURSE_ID_1, 'Mathematics 101', 'An introductory course in Algebra covering fundamentals.', 'FALL 2024', '2023-2024', @TRAINER_ID_1, CURRENT_TIMESTAMP, NULL),
(uuid(), 'Classical Mechanics', 'www.physics201resources.com', @COURSE_ID_2, 'Physics 201', 'An intermediate course in Classical Mechanics.', 'SPRING 2024', '2023-2024', @TRAINER_ID_2, CURRENT_TIMESTAMP, NULL),
(uuid(), 'World History', 'www.history301resources.com', @COURSE_ID_3, 'History 301', 'A comprehensive study of world history.', 'FALL 2024', '2023-2024', @TRAINER_ID_3, CURRENT_TIMESTAMP, NULL);
