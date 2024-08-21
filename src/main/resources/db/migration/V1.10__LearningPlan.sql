DROP TABLE IF EXISTS `learning_plan`;
CREATE TABLE `learning_plan`
(
    `id`                      char(36)    NOT NULL,
    `title`                   varchar(50) NOT NULL,
    `description`             text        NULL,
    `created_date_time`       timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time`  timestamp   NULL DEFAULT NULL,
    `created_by`               char(36)    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;

SET @LEARNING_PLAN_ID_1 = uuid();
SET @LEARNING_PLAN_ID_2 = uuid();
SET @LEARNING_PLAN_ID_3 = uuid();

-- Inserting into learning_plan table with new fields including created_by
INSERT INTO `learning_plan`
( id, title, description, created_by)
VALUES(@LEARNING_PLAN_ID_1, 'Plan 1', 'Description for Plan 1', 'StaticUserID1');

INSERT INTO `learning_plan`
( id, title, description, created_by)
VALUES(@LEARNING_PLAN_ID_2, 'Plan 2', 'Description for Plan 2', 'StaticUserID2');

INSERT INTO `learning_plan`
( id, title, description, created_by)
VALUES(@LEARNING_PLAN_ID_3, 'Plan 3', 'Description for Plan 3', 'StaticUserID3');
