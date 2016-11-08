--tables ratachés aux visites
--DROP TABLE APP.THERAPEUTIQUES;
--DROP TABLE APP.ANAMNESE;

--tables rattachés aux patients
DROP TABLE APP.DOCUMENTS;
DROP TABLE APP.ALIMENTATION;
DROP TABLE APP.ANTECEDENTS;
DROP TABLE APP.ALLERGIES;
DROP TABLE APP.VISITE;
DROP TABLE APP.PATIENT;
DROP TABLE APP.REPERTOIRE;

--TABLE DES REPERTOIRES
--cette table permet de classer les patient selon des groupes que l'on peut
-- définir, le groupe 1 est le groupe par defaut et se nomme TOUT LE MONDE
CREATE TABLE APP.REPERTOIRE
(
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    LABEL varchar(100) NOT NULL
);
CREATE INDEX REPERTOIRE_LABEL_IDX ON APP.REPERTOIRE(LABEL);

--CREER LE GROUPE 1 TOUT LES PATIENTS = groupe par defaut.
INSERT INTO APP.REPERTOIRE VALUES (DEFAULT, 'TOUT LES PATIENTS');

--TABLE DES PATIENTS
-- contient l'dentité compléte du patient
-- elle peut être maj sauf pour l'ID et DATECREATION
-- un patient nouvellement créer se retrouve par défaut 
-- dans le groupe 1 TOUT LES PATIENTS
CREATE TABLE APP.PATIENT
(
   ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   NOM varchar(100) NOT NULL,
   PRENOM varchar(100) NOT NULL,
   NOMMAR varchar(100),
   DDN date,
   SEXE varchar(1) NOT NULL,
   NATIONALITE varchar(100),
   ADRESSE1 varchar(255),
   ADRESSE2 varchar(255),
   CP varchar(6),
   VILLE varchar(80),
   TEL1 varchar(16),
   TEL2 varchar(16),
   EMAIL1 varchar(100),
   EMAIL2 varchar(100),
   NUMSS varchar(20),
   NOMCAISSE varchar(200),
   PROFESSION varchar (255),
   COMMENTAIRES varchar (2048),
   DATECREATION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   REPERTOIRE INT DEFAULT 1 REFERENCES APP.REPERTOIRE(ID)
);
CREATE INDEX PATIENT_NOMMAR_IDX ON APP.PATIENT(NOMMAR);
CREATE UNIQUE INDEX PATIENT_ID_IDX ON APP.PATIENT(ID);
CREATE INDEX PATIENT_PRENOM_IDX ON APP.PATIENT(PRENOM);
CREATE INDEX PATIENT_NOM_IDX ON APP.PATIENT(NOM);

--TABLE VISITE, contient la liste des visites pour un patient
--a chaque visite on va y fixer un motif
-- et attacher les fiches d'examen pour chaque visite.
CREATE TABLE APP.VISITE
(   
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    DATEVISITE timestamp DEFAULT CURRENT_TIMESTAMP,
    MOTIF varchar(26000),
    --attacher à la visite l'ensemble des bilans
    IDPATIENT INT REFERENCES APP.PATIENT(ID)
);


--TABLE DES ALLERGIES
-- elle va contenir toutes les allergies du patient
-- une allergies pourra être désactivé

CREATE TABLE APP.ALLERGIES
(
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    LABEL varchar(255) NOT NULL,
    ACTIVE BOOLEAN DEFAULT TRUE,
    IDPATIENT INT REFERENCES APP.PATIENT(ID)
);

--TABLE DES ANTECEDENTS
-- reprend le poids, taille, et constantes du patient
-- elle seront ratachés au patient et modifible en fonction des visites
CREATE TABLE APP.ANTECEDENTS
(
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    POIDS INT CHECK (POIDS BETWEEN 0 AND 300),
    TAILLE INT CHECK (TAILLE BETWEEN 0 AND 300),
    TASYS INT,
    TADIA INT,
    ACIDOSE FLOAT(2) DEFAULT 7.40,
    GLYCEMIE FLOAT(2) DEFAULT 1.0,
    LANGUE varchar(100),
    NEZ varchar(100),
    DIURESE INT,
    SELLES INT,
    ONGLES varchar(100),
    PHYSIONOMIE varchar(100),
    GROUPESANGUIN varchar(100),
    SPORTS varchar(1024),
    LOISIR varchar(1024),
    SOMMEILHEURE INT,
    SOMMEILINSOMNIE INT,
    TABACNB INT,
    TABACDEPUIUS INT,
    ANTECEDENTFAMILIAUX varchar(2048),
    ANTECEDENTOPERATOIRE varchar(2048),
    ANALYSES varchar(4096),
    TRAITEMENTACTUELS varchar (4096),
    DIVERS varchar (4096),
    IDPATIENT INT REFERENCES APP.PATIENT(ID)
);

--TABLE ALIMENTION
-- liste le mode d'alimentation du patient actuellement
-- a ratacher au patient
CREATE TABLE APP.ALIMENTATION
(
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    HEURESREGULIERES varchar(100),
    GOUT varchar(100),
    BOULIMIE varchar(100),
    RESTAURANT INT,
    SUCREBLANC INT,
    POISSONS INT,
    VIANDESROUGE INT,
    VIANDESBLANCHE INT,
    EAU INT,
    ALCOOL INT,
    CAFEINE INT,
    COLA INT,
    LAIT INT,
    PETITDEJ varchar (8096),
    MATINEE varchar (8096),
    DEJ varchar (8096),
    GOUTER varchar (8096),
    DINER varchar (8096),
    DIVERS varchar (8096),
    IDPATIENT INT REFERENCES APP.PATIENT(ID)
);


--TABLE DOCUMENTS
--liste les documents externes apporté par le patient
--photos, images, pdf
-- produire des documents internes ??
CREATE TABLE APP.DOCUMENTS
(
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    TYPEDOC varchar(100),
    DESCDOC varchar(1024),
    CHEMINDOC varchar(1024),
    DATEDOC TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ACTIF BOOLEAN DEFAULT TRUE,
    IDPATIENT INT REFERENCES APP.PATIENT(ID)
);


--TABLE ANAMNESE liste des diagnostiques faites lors de visite
--du patient qui permettra d'aboutir à une thérapeutique
--CREATE TABLE APP.ANAMNESE
--(

--);


--TABLE THERAPEUTIQUE liste les thérapies proposés
--une sorte d'ordonnances.

--************************************************************************
-- test insert into 
INSERT INTO APP.PATIENT VALUES(DEFAULT,'TONDEUR','HERVE',NULL,'1971-01-12',
'M','FRA','1120 CHEMIN STRATEGIQUE',NULL,'59680','FERRIERE LA GRANDE',
'0695807044','0327140199','tondeur.herve@yahoo.fr','herve12017@live.fr',
'171XXXXXXX','CPAM VALENCIENNES','INFORMATICIEN','PATIENT ANGOISSE ET ALGIQUE...',CURRENT_TIMESTAMP,DEFAULT);

--récuperer le numro du patient
--VALUES IDENTITY_VAL_LOCAL();

--SELECT MAX(IDENTITY_VAL_LOCAL()) FROM PATIENT;

INSERT INTO APP.VISITE VALUES(DEFAULT,CURRENT_TIMESTAMP,'Stress2 +++',IDENTITY_VAL_LOCAL());