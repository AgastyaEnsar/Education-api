DROP TABLE IF EXISTS trainer;

CREATE TABLE `trainer` (
  `id` char(36) NOT NULL,
  `name` varchar(45) NOT NULL,
  `company` varchar(45) NOT NULL,
  `department` varchar(45) DEFAULT NULL,
  `specialization` varchar(100) DEFAULT NULL,
  `email` varchar(50) UNIQUE DEFAULT NULL,
  `phone_number` varchar(15) UNIQUE DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` enum('Male','Female','Other') DEFAULT NULL,
  `experience` int DEFAULT NULL,
  `bio` text,
  `created_date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated_date_time` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET @TRAINER_ID_1 = uuid();
SET @TRAINER_ID_2 = uuid();
SET @TRAINER_ID_3 = uuid();

-- Sample Data 1
INSERT INTO `trainer` (`id`, `name`, `company`, `department`, `specialization`, `email`, `phone_number`, `address`, `date_of_birth`, `gender`, `experience`, `bio`, `created_date_time`, `last_updated_date_time`)
VALUES (@TRAINER_ID_1, 'Agastya', 'Tech Solutions', 'Engineering', 'Software Development', 'john.doe@techsolutions.com', '1234567890', '1234 Elm Street, Springfield', '1985-05-15', 'Male', 10, 'Experienced software developer specializing in backend development.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Sample Data 2
INSERT INTO `trainer` (`id`, `name`, `company`, `department`, `specialization`, `email`, `phone_number`, `address`, `date_of_birth`, `gender`, `experience`, `bio`, `created_date_time`, `last_updated_date_time`)
VALUES (@TRAINER_ID_2, 'Vineeth', 'Innovatech', 'Research and Development', 'AI and Machine Learning', 'jane.smith@innovatech.com', '0987654321', '5678 Oak Avenue, Metropolis', '1990-07-25', 'Female', 8, 'AI researcher with a focus on machine learning algorithms.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Sample Data 3
INSERT INTO `trainer` (`id`, `name`, `company`, `department`, `specialization`, `email`, `phone_number`, `address`, `date_of_birth`, `gender`, `experience`, `bio`, `created_date_time`, `last_updated_date_time`)
VALUES (@TRAINER_ID_3, 'Pavan', 'Data Insights', 'Analytics', 'Data Science', 'alex.johnson@datainsights.com', '1122334455', '91011 Birch Lane, Gotham', '1988-03-30', 'Other', 12, 'Data scientist with extensive experience in data analysis and visualization.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
