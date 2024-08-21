-- Drop existing category table if it exists
DROP TABLE IF EXISTS `category`;

-- Create category table
CREATE TABLE `category`
(
    `id` CHAR(36) NOT NULL,
    `category_name` VARCHAR(255) NOT NULL,
    `description` TEXT NOT NULL,
    `created_date_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARSET = utf8;

-- Insert sample data into the category table
SET @UUID_1 = UUID();
SET @UUID_2 = UUID();
SET @UUID_3 = UUID();

INSERT INTO `category`
(`id`, `category_name`, `description`)
VALUES
(@UUID_1, 'Mathematics', 'This category includes all mathematics related courses.'),
(@UUID_2, 'Physics', 'This category includes all physics related courses.'),
(@UUID_3, 'Chemistry', 'This category includes all chemistry related courses.');
