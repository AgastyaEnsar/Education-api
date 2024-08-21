DROP TABLE IF EXISTS `Enrollment`;

CREATE TABLE `Enrollment`
(
    `id`                    CHAR(36)    NOT NULL,
    `enrollment_name`       VARCHAR(50) NULL,
    `remarks`               VARCHAR(50) NULL,
    `enrollment_date`       DATE        NOT NULL,
    `last_modified_date`    DATE        NOT NULL,
    `status`                ENUM('enrolled', 'withdrawn', 'completed') NOT NULL DEFAULT 'enrolled',
    `created_date_time`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` TIMESTAMP  NULL DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;
SET @ENROLMENT_ID_1 = uuid();
SET @ENROLMENT_ID_2 = uuid();
SET @ENROLMENT_ID_3 = uuid();

-- Inserting sample enrollments into the Enrollment table
INSERT INTO `Enrollment` (id,  enrollment_name, remarks,enrollment_date, last_modified_date, status)
VALUES
(@ENROLMENT_ID_1, 'John', 'Excellent performance', '2024-07-20', NULL,'enrolled'),
(@ENROLMENT_ID_2,  'Jane', 'Good performance', '2024-07-23', NULL, 'completed'),
(@ENROLMENT_ID_3, 'Alice', 'Personal reasons', '2024-07-12', NULL, 'withdrawn');




