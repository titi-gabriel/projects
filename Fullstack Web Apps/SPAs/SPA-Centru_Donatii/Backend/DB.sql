IF(DB_ID('Centru') IS NULL)
	CREATE DATABASE Centru
GO

USE Centru
GO

IF OBJECT_ID('Donator') IS NULL
CREATE TABLE Donator (
	id       INT NOT NULL IDENTITY(1,1),
	nume     VARCHAR(64) NOT NULL,
    prenume  VARCHAR(64) NOT NULL,
	cnp      VARCHAR(64) NOT NULL UNIQUE,
	grupa_sg VARCHAR(64) NOT NULL,
	rh		 VARCHAR(64) NOT NULL,
	telefon  VARCHAR(16) ,
)
ALTER TABLE donator 
	ADD CONSTRAINT donator_pk PRIMARY KEY ( id )
GO