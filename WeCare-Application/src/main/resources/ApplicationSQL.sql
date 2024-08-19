create database Application_db;

use Application_db;

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    life_coach_id INT,
    appointment_time DATETIME,
    duration INT
);
