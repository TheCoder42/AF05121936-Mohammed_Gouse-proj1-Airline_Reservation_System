CREATE DATABASE airline_db;
USE airline_db;

CREATE TABLE flights (
    flight_id INT PRIMARY KEY AUTO_INCREMENT,
    flight_name VARCHAR(50),
    source VARCHAR(50),
    destination VARCHAR(50),
    seats_available INT,
    price DOUBLE
);

CREATE TABLE passengers (
    passenger_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    age INT,
    gender VARCHAR(10)
);

CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    passenger_id INT,
    flight_id INT,
    booking_date DATE,
    FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);


INSERT INTO flights (flight_name, source, destination, seats_available, price) VALUES
('Air India AI202', 'Mumbai', 'Bangalore', 40, 4500),
('SpiceJet SG303', 'Delhi', 'Hyderabad', 30, 4000),
('Vistara UK404', 'Chennai', 'Mumbai', 35, 5000),
('GoAir G505', 'Bangalore', 'Delhi', 45, 4800);


INSERT INTO passengers (name, age, gender) VALUES
('Rahul Sharma', 25, 'Male'),
('Priya Singh', 22, 'Female'),
('Arjun Kumar', 30, 'Male'),
('Sneha Reddy', 27, 'Female');


INSERT INTO bookings (passenger_id, flight_id, booking_date) VALUES
(1, 1, '2025-12-20'),
(2, 2, '2026-01-15'),
(3, 3, '2026-01-24'),
(4, 4, '2026-03-29');
