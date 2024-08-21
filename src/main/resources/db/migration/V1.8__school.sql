DROP TABLE IF EXISTS `school`;

CREATE TABLE `school`
(
    `id`                      CHAR(36)    NOT NULL,
    `name`                    VARCHAR(30) NOT NULL,
    `city`                    VARCHAR(30) NOT NULL,
    `address`                 VARCHAR(100) NOT NULL,
    `zipcode`                 VARCHAR(10)  NOT NULL,
    `phone_number`            VARCHAR(15)  NOT NULL,    
    `created_date_time`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time`  TIMESTAMP    NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;

SET @SCHOOL_ID_1 = uuid();
SET @SCHOOL_ID_2 = uuid();
SET @SCHOOL_ID_3 = uuid();

INSERT INTO `school` (id, name, city, address, zipcode, phone_number)
VALUES
(@SCHOOL_ID_1, 'Greenwood High',  'Springfield', '123 Elm St', '12345', '123-456-7890'),
(@SCHOOL_ID_2, 'Sunset Academy',  'Sunnyvale', '456 Oak St', '67890', '234-567-8901'),
(@SCHOOL_ID_3, 'Riverside School', 'Rivertown', '789 Pine St', '10112', '345-678-9012');
