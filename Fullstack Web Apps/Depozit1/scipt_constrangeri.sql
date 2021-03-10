ALTER TABLE client
    ADD CONSTRAINT nume_valid CHECK ( REGEXP_LIKE ( nume,
                                                    '^([a-zA-Z ]*)$' ) );

ALTER TABLE client
    ADD CONSTRAINT telefon_valid CHECK ( REGEXP_LIKE ( telefon,
                                                       '^([0-9]*)$' ) );

ALTER TABLE client ADD CONSTRAINT client_pk PRIMARY KEY ( id );

ALTER TABLE depozit ADD CONSTRAINT depozit_pk PRIMARY KEY ( id );

ALTER TABLE masina ADD CONSTRAINT capcacitate_valid CHECK ( capacitate > 0 );

ALTER TABLE masina ADD CONSTRAINT masina_pk PRIMARY KEY ( id );

ALTER TABLE masina ADD CONSTRAINT masina_numar_unique UNIQUE ( numar_masina );

ALTER TABLE masina ADD CONSTRAINT masina_sofer_unique UNIQUE ( id_sofer );

ALTER TABLE material ADD CHECK ( pret > 0 );

ALTER TABLE material ADD CONSTRAINT material_cantitate CHECK ( cantitate >= 0 );

ALTER TABLE material ADD CONSTRAINT material_pk PRIMARY KEY ( id );

ALTER TABLE sofer ADD CHECK ( REGEXP_LIKE ( cnp,
                                            '^([0-9]*)$' ) );

ALTER TABLE sofer ADD CONSTRAINT sofer_pk PRIMARY KEY ( id );

ALTER TABLE sofer ADD CONSTRAINT sofer_cnp_unique UNIQUE ( cnp );

ALTER TABLE sofer ADD CONSTRAINT sofer_serie_unique UNIQUE ( serie_permis );

ALTER TABLE tranzactie ADD CONSTRAINT tranzactie_pk PRIMARY KEY ( id );

ALTER TABLE material
    ADD CONSTRAINT depozit_material FOREIGN KEY ( id_depozit )
        REFERENCES depozit ( id );

ALTER TABLE masina
    ADD CONSTRAINT masina_sofer FOREIGN KEY ( id_sofer )
        REFERENCES sofer ( id );

ALTER TABLE tranzactie
    ADD CONSTRAINT tranzactie_client FOREIGN KEY ( id_client )
        REFERENCES client ( id );

ALTER TABLE tranzactie
    ADD CONSTRAINT tranzactie_masina FOREIGN KEY ( id_masina )
        REFERENCES masina ( id );

ALTER TABLE tranzactie
    ADD CONSTRAINT tranzactie_material FOREIGN KEY ( id_material )
        REFERENCES material ( id );

CREATE SEQUENCE client_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER client_id_trg BEFORE
    INSERT ON client
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := client_id_seq.nextval;
END;
/

CREATE SEQUENCE depozit_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER depozit_id_trg BEFORE
    INSERT ON depozit
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := depozit_id_seq.nextval;
END;
/

CREATE SEQUENCE masina_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER masina_id_trg BEFORE
    INSERT ON masina
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := masina_id_seq.nextval;
END;
/

CREATE SEQUENCE material_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER material_id_trg BEFORE
    INSERT ON material
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := material_id_seq.nextval;
END;
/

CREATE SEQUENCE sofer_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER sofer_id_trg BEFORE
    INSERT ON sofer
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := sofer_id_seq.nextval;
END;
/

CREATE SEQUENCE tranzactie_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tranzactie_id_trg BEFORE
    INSERT ON tranzactie
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := tranzactie_id_seq.nextval;
END;
/