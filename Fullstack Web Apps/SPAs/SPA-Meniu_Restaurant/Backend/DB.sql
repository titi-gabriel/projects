IF(DB_ID('Proiect1') IS NULL)
	CREATE DATABASE Proiect1
GO

USE Proiect1
GO

IF OBJECT_ID('Mancare') IS NULL
CREATE TABLE Mancare (
    id          INT NOT NULL IDENTITY(1,1),
    nume        VARCHAR(64),
	bucatarie   VARCHAR(64),
    pret        INT,
    cantitate   INT
)
ALTER TABLE mancare ADD CONSTRAINT mancare_pret CHECK ( pret > 0 )

ALTER TABLE mancare ADD CONSTRAINT mancare_cantitate CHECK ( cantitate >= 0 )

ALTER TABLE mancare ADD CONSTRAINT mancare_pk PRIMARY KEY ( id )
GO

IF OBJECT_ID('Bautura') IS NULL
CREATE TABLE Bautura (
    id          INT NOT NULL IDENTITY(1,1),
    nume        VARCHAR(64),
	categorie   VARCHAR(64),
    pret        INT,
    cantitate   INT
)
ALTER TABLE bautura ADD CONSTRAINT bautura_pret CHECK ( pret > 0 )

ALTER TABLE bautura ADD CONSTRAINT bautura_cantitate CHECK ( cantitate >= 0 )

ALTER TABLE bautura ADD CONSTRAINT bautura_pk PRIMARY KEY ( id )
GO

GO
