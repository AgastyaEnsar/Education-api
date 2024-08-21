-- Dropping the status table if it exists
DROP TABLE IF EXISTS `status`;

-- Creating the status table
CREATE TABLE `status`
(
    id                      CHAR(36) NOT NULL,
    name                    VARCHAR(50) NOT NULL,
    description             VARCHAR(255),
    created_date_time       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_date_time  TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;

-- Inserting sample data into the status table
SET @STATUS_ID_1 = uuid();
SET @STATUS_ID_2 = uuid();
SET @STATUS_ID_3 = uuid();
SET @STATUS_ID_4 = uuid();

INSERT INTO `status` 
(id, name, description)
VALUES
(@STATUS_ID_1, 'active', 'Currently active status'),
(@STATUS_ID_2, 'inactive', 'No longer active'),
(@STATUS_ID_3, 'completed', 'Status has been completed'),
(@STATUS_ID_4, 'withdrawn', 'Status has been withdrawn');
