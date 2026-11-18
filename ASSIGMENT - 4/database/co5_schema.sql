CREATE DATABASE IF NOT EXISTS bus_pass_db;
USE bus_pass_db;

CREATE TABLE IF NOT EXISTS student (
    student_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    reg_no VARCHAR(30) NOT NULL UNIQUE,
    course VARCHAR(80),
    email VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS wallet (
    wallet_id INT PRIMARY KEY,
    balance DECIMAL(12,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(80) PRIMARY KEY,
    pwd VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS bus_pass (
    pass_id VARCHAR(30) PRIMARY KEY,
    reg_no VARCHAR(30) NOT NULL,
    name VARCHAR(100) NOT NULL,
    route_no INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL
);
