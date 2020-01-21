DROP TABLE IF EXISTS MONEY_TRANSACTIONS;
DROP TABLE IF EXISTS USER_DATA;
DROP TABLE IF EXISTS LOYALTY_POINTS;

CREATE TABLE MONEY_TRANSACTIONS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  value DECIMAL(20, 2) NOT NULL,
  transaction_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
  operation_type VARCHAR(50),
  user_id INT
);

INSERT INTO MONEY_TRANSACTIONS (value, transaction_date_time, operation_type, user_id) VALUES
  (500.00, '2020-01-12 10:00:00+03', 'ADD', 1),
  (1000.00, '2020-02-13 10:00:00+03', 'ADD', 1),
  (1500.20, '2020-03-15 10:00:00+03', 'ADD', 1),
  (100.39, '2020-01-12 10:00:00+03', 'REMOVE', 1),
  (100.00, '2020-01-13 10:00:00+03', 'REMOVE', 1),
  (100.00, '2020-01-14 10:00:00+03', 'REMOVE', 1),
  (200.00, '2020-01-15 10:00:00+03', 'REMOVE', 1),
  (50.00, '2020-01-16 10:00:00+03', 'REMOVE', 1),
  (50.00, '2020-01-17 10:00:00+03', 'REMOVE', 1),
  (100.00, '2020-01-18 10:00:00+03', 'REMOVE', 1),
  (100.00, '2020-01-19 10:00:00+03', 'REMOVE', 1),
  (10.00, '2020-01-20 10:00:00+03', 'REMOVE', 1),
  (10.00, '2020-01-21 10:00:00+03', 'REMOVE', 1),
  (10.00, '2020-01-22 10:00:00+03', 'REMOVE', 1),
  (50.00, '2020-01-23 10:00:00+03', 'REMOVE', 1),
  (100.00, '2019-11-15 10:00:00+03', 'ADD', 2),
  (450.00, '2019-11-15 10:00:00+03', 'ADD', 2),
  (50.00, '2019-11-16 10:00:00+03', 'REMOVE', 2);

CREATE TABLE USER_DATA (
  id INT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  current_points INT DEFAULT NULL
);

INSERT INTO USER_DATA (id, first_name, last_name, current_points) VALUES
  (1, 'John', 'Lennon', 2200),
  (2, 'Kurt', 'Cobain', 500);

CREATE TABLE LOYALTY_POINTS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  value INT NOT NULL,
  operation_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
  operation_type VARCHAR(50),
  user_id INT
);

INSERT INTO LOYALTY_POINTS (value, operation_date_time, operation_type, user_id) VALUES
  (500, '2020-01-12 10:00:00+03', 'ADD', 1),
  (1000, '2020-02-13 10:00:00+03', 'ADD', 1),
  (1500, '2020-03-15 10:00:00+03', 'ADD', 1),
  (800, '2019-12-11 10:00:00+03', 'REMOVE', 1),
  (100, '2019-11-15 10:00:00+03', 'ADD', 2),
  (450, '2019-11-15 10:00:00+03', 'ADD', 2),
  (50, '2019-11-07 10:00:00+03', 'REMOVE', 2);