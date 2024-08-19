create database LifeCoach_db;

use LifeCoach_db;

CREATE TABLE life_coaches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    specialty VARCHAR(50) NOT NULL,
    email VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL
);
