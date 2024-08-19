create database User_db;

use User_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(25) NOT NULL,
    password VARCHAR(20) NOT NULL
);
