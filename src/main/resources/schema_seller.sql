CREATE TABLE IF NOT EXISTS SELLER (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(100),
    EMAIL VARCHAR(100),
    BIRTHDATE DATE,
    BASESALARY DOUBLE,
    DEPARTMENTID INT
);