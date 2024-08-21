DROP TABLE IF EXISTS course;

CREATE TABLE course
(
    id                   CHAR(36) NOT NULL,
    name                 VARCHAR(255) NOT NULL,
    description          TEXT,
    duration             VARCHAR(50),
    start_date           DATE NOT NULL,
    end_date             DATE NOT NULL,
    created_date_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_date_time TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;


-- Generate UUIDs for the cours entries
SET @COURSE_ID_1 = uuid();
SET @COURSE_ID_2 = uuid();
SET @COURSE_ID_3 = uuid();

-- Insert sample data into the courses table
INSERT INTO course 
(id, name, description, duration, start_date, end_date)
VALUES
(@COURSE_ID_1, 'Python', 'Learn the basics of programming.', '10 weeks', '2024-09-01', '2024-11-10'),
(@COURSE_ID_2, 'Amazon Web Services', 'Understand data structures and algorithms.', '8 weeks', '2024-09-15', '2024-11-10'),
(@COURSE_ID_3, 'Database Management Systems', 'Learn about relational databases.', '12 weeks', '2024-10-01', '2024-12-20');
