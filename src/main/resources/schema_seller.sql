CREATE TABLE IF NOT EXISTS Seller (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    birthDate DATE,
    baseSalary DOUBLE
);