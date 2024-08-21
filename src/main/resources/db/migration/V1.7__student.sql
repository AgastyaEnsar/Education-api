DROP TABLE IF EXISTS students;
 
CREATE TABLE students
(
    id                   CHAR(36) NOT NULL,
    first_name           VARCHAR(50) NOT NULL,
    last_name            VARCHAR(50) NOT NULL,
    email                VARCHAR(100) NOT NULL,
    enrollment_date      DATE NOT NULL,
    disabled             BOOLEAN NOT NULL DEFAULT false,
    date_of_birth        DATE NOT NULL,
    phone_number         VARCHAR(15) NOT NULL,
    address              VARCHAR(255) NOT NULL,
    city                 VARCHAR(50) NOT NULL,
    zipcode              VARCHAR(10) NOT NULL,
    created_date_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_date_time TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY student_email_unique (email)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;


SET @STUDENT_ID_1 = uuid();
SET @STUDENT_ID_2 = uuid();
SET @STUDENT_ID_3 = uuid();

INSERT INTO `students` 
(id, first_name, last_name, email, enrollment_date, disabled, date_of_birth, phone_number, address, city, zipcode)
VALUES
(@STUDENT_ID_1, 'John', 'Doe', 'john.doe@gmail.com', '2024-07-30', false, '2002-05-15', '123-456-7890', '123 Elm St', 'Springfield', '62701'),
(@STUDENT_ID_2, 'Jane', 'Smith', 'jane.smith@gmail.com', '2024-07-29', false, '2001-08-22', '234-567-8901', '456 Oak St', 'Springfield', '62702'),
(@STUDENT_ID_3, 'Alice', 'Johnson', 'alice.johnson@gmail.com', '2024-07-28', false, '2000-12-15', '345-678-9012', '789 Pine St', 'Springfield', '62703');
