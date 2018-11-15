DROP DATABASE ASSIGNMENT2DATABASE;
CREATE DATABASE ASSIGNMENT2DATABASE;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'admin'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON ASSIGNMENT2DATABASE.* TO 'admin'@'localhost' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON ASSIGNMENT2DATABASE.* TO 'admin'@'%' WITH GRANT OPTION;

USE ASSIGNMENT2DATABASE;


DROP TABLE IF EXISTS Users;
CREATE TABLE Users(
 empNumber int NOT NULL AUTO_INCREMENT, 
 username VARCHAR(255), 
 empName VARCHAR(255), 
 password VARCHAR(255), 
 administrator BIT,
 PRIMARY KEY (empNumber)
 );

INSERT INTO Users(username, empName, password, administrator) VALUES("andy", "Andy Tang", "password", 1);
INSERT INTO Users(username, empName, password, administrator) VALUES("tommy", "Tommy Yeh", "password", 0);
INSERT INTO Users(username, empName, password, administrator) VALUES("test", "Test Account", "password", 0);
INSERT INTO Users(username, empName, password, administrator) VALUES("bruce", "Bruce", "password", 0);

DROP TABLE IF EXISTS Timesheet;
CREATE TABLE Timesheet(
 timesheetID int NOT NULL AUTO_INCREMENT, 
 empNumber int,
 weekNumber int, 
 weekEnding DATE,
 PRIMARY KEY (timesheetID),
 FOREIGN KEY (empNumber) REFERENCES Users(empNumber)
);


INSERT INTO Timesheet(empNumber, weekNumber, weekEnding) VALUES (1, 1, DATE '1999-01-01');
INSERT INTO Timesheet(empNumber, weekNumber, weekEnding) VALUES (1, 2, DATE '1999-10-10');
INSERT INTO Timesheet(empNumber, weekNumber, weekEnding) VALUES (2, 1, DATE '1999-01-01');
INSERT INTO Timesheet(empNumber, weekNumber, weekEnding) VALUES (3, 1, DATE '1999-01-01');
INSERT INTO Timesheet(empNumber, weekNumber, weekEnding) VALUES (4, 1, DATE '1999-01-01');
INSERT INTO Timesheet(empNumber, weekNumber, weekEnding) VALUES (5, 1, DATE '1999-01-01');

CREATE TABLE TimesheetRow(
 timesheetID int,
 projectID int, 
 workPackage VARCHAR(10), 
 sat DECIMAL(2,1),
 sun DECIMAL(2,1),
 mon DECIMAL(2,1),
 tue DECIMAL(2,1),
 wed DECIMAL(2,1),
 thu DECIMAL(2,1),
 fri DECIMAL(2,1),
 notes TEXT,
 FOREIGN KEY (timesheetID) REFERENCES timesheet(timesheetID),
 PRIMARY KEY (timesheetID, projectID, workPackage)
);

INSERT INTO TimesheetRow(timesheetID, projectID, workPackage, sat, sun, mon, tue, wed, thu, fri, notes) VALUES (1, 1, "WP1", 6.0, 7.0, 1.0, 2.0, 3.0, 4.0, 5.0, "notes1");
INSERT INTO TimesheetRow(timesheetID, projectID, workPackage, sat, sun, mon, tue, wed, thu, fri, notes) VALUES (1, 2, "WP2", 6.0, 7.0, 1.0, 2.0, 3.0, 4.0, 5.0, "notes2");
INSERT INTO TimesheetRow(timesheetID, projectID, workPackage, sat, sun, mon, tue, wed, thu, fri, notes) VALUES (1, 3, "WP3", 6.0, 7.0, 1.0, 2.0, 3.0, 4.0, 5.0, "notes3");
INSERT INTO TimesheetRow(timesheetID, projectID, workPackage, sat, sun, mon, tue, wed, thu, fri, notes) VALUES (1, 3, "WP33", 6.0, 7.0, 1.0, 2.0, 3.0, 4.0, 5.0, "notes33");