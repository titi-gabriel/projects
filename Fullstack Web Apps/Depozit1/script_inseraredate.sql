-- Adaugare soferi
INSERT INTO sofer(nume, cnp, serie_permis) VALUES('Mihai Andrei',     '125635256963', 'XV1803');
INSERT INTO sofer(nume, cnp, serie_permis) VALUES('Constantin Petcu', '124565735354', 'NT6952');
INSERT INTO sofer(nume, cnp, serie_permis) VALUES('Ionut Romascu',    '154341343453', 'IS8563');
INSERT INTO sofer(nume, cnp, serie_permis) VALUES('Tudorel Radacanu', '156798315708', 'ZZ1253');
INSERT INTO sofer(nume, cnp, serie_permis) VALUES('Alexandru Bucur',  '135484387358', 'CL0152');

-- Adaugare masini
INSERT INTO masina(id_sofer, numar_masina, capacitate) VALUES((SELECT id FROM sofer WHERE nume='Mihai Andrei'    ), 'IS-01-CRB', 3.75);
INSERT INTO masina(id_sofer, numar_masina, capacitate) VALUES((SELECT id FROM sofer WHERE nume='Constantin Petcu'), 'IS-03-CRN', 8.75);
INSERT INTO masina(id_sofer, numar_masina, capacitate) VALUES((SELECT id FROM sofer WHERE nume='Ionut Romascu'   ), 'IS-15-CRM', 9.00);
INSERT INTO masina(id_sofer, numar_masina, capacitate) VALUES((SELECT id FROM sofer WHERE nume='Tudorel Radacanu'), 'IS-83-CRL', 3.00);
INSERT INTO masina(id_sofer, numar_masina, capacitate) VALUES((SELECT id FROM sofer WHERE nume='Alexandru Bucur' ), 'IS-92-CRO', 3.50);

-- Adaugare depozite
INSERT INTO depozit(nume) VALUES('Gara1');
INSERT INTO depozit(nume) VALUES('Gara2');
INSERT INTO depozit(nume) VALUES('Sarariei');

-- Adaugare materiale
INSERT INTO material(id_depozit, nume, pret, cantitate) VALUES((SELECT id FROM depozit WHERE nume='Gara1'), 'Caramida 2x5', 65.85, 2250);
INSERT INTO material(id_depozit, nume, pret, cantitate) VALUES((SELECT id FROM depozit WHERE nume='Gara1'), 'BCA 886', 33.23, 786);

INSERT INTO material(id_depozit, nume, pret, cantitate) VALUES((SELECT id FROM depozit WHERE nume='Gara2'), 'Teava inox 80mm', 834.99, 53);
INSERT INTO material(id_depozit, nume, pret, cantitate) VALUES((SELECT id FROM depozit WHERE nume='Gara2'), 'Cornier Aluminiu 45mm', 32.20, 786);

INSERT INTO material(id_depozit, nume, pret, cantitate) VALUES((SELECT id FROM depozit WHERE nume='Sarariei'), 'Tabla zincata 1.5mm', 72.30, 25);

-- Adaugare client
INSERT INTO client(nume, telefon, adresa) VALUES('Catalin Radoi',     '0745986555', 'Strada Garii, nr. 3');
INSERT INTO client(nume, telefon, adresa) VALUES('Petrica Lungu',      '0754854476', 'Mal Bahlui stang, sub pod');
INSERT INTO client(nume, telefon, adresa) VALUES('Sandu Chirica', '0792658712', 'Cladirea abandonata de langa Moara de Foc');
INSERT INTO client(nume, telefon, adresa) VALUES('Andrei Diaconu', '0792743741', 'Camin T19');
INSERT INTO client(nume, telefon, adresa) VALUES('George Paduraru', '0792749023', 'Aleea Florilor , nr.4');

-- Adaugare tranzactii de test
INSERT INTO tranzactie(id_client, id_material, id_masina, cantitate, data) VALUES(
	(SELECT id FROM client WHERE nume='Petrica Lungu'),
	(SELECT id FROM material WHERE nume='Teava inox 80mm'),
	(SELECT id FROM masina WHERE id_sofer=(SELECT id FROM sofer WHERE nume='Mihai Andrei')),
	10,
	'20-JAN-2020'
);

INSERT INTO tranzactie(id_client, id_material, id_masina, cantitate, data) VALUES(
	(SELECT id FROM client WHERE nume='Catalin Radoi'),
	(SELECT id FROM material WHERE nume='BCA 886'),
	(SELECT id FROM masina WHERE id_sofer=(SELECT id FROM sofer WHERE nume='Ionut Romascu')),
	250,
	'04-JAN-2020'
);

