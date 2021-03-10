CREATE TABLE client (
    id       NUMBER NOT NULL,
    nume     VARCHAR2(64) NOT NULL,
    telefon  VARCHAR2(16),
    adresa   VARCHAR2(128)
        CONSTRAINT client_adresa_not_null NOT NULL
);

CREATE TABLE depozit (
    id    NUMBER NOT NULL,
    nume  VARCHAR2(64)
);

CREATE TABLE masina (
    id            NUMBER NOT NULL,
    numar_masina  VARCHAR2(16),
    capacitate    NUMBER(10, 3),
    id_sofer      NUMBER NOT NULL
);

CREATE TABLE material (
    id          NUMBER NOT NULL,
    id_depozit  NUMBER NOT NULL,
    nume        VARCHAR2(64),
    pret        NUMBER(10, 2),
    cantitate   NUMBER
);

CREATE TABLE sofer (
    id            NUMBER NOT NULL,
    nume          VARCHAR2(64),
    cnp           VARCHAR2(64) NOT NULL,
    serie_permis  VARCHAR2(16) NOT NULL
);

CREATE TABLE tranzactie (
    id           NUMBER NOT NULL,
    id_client    NUMBER NOT NULL,
    id_masina    NUMBER NOT NULL,
    id_material  NUMBER NOT NULL,
    cantitate    NUMBER
        CONSTRAINT cantitate_not_null NOT NULL,
    data         DATE
        CONSTRAINT date_not_null NOT NULL
);