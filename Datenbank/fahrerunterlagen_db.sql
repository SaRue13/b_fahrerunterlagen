-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-05-29 18:54:01.466


-- Database: fahrerunterlagen

-- DROP DATABASE fahrerunterlagen;

CREATE DATABASE fahrerunterlagen
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- tables




-- Table: fahrerunterlagen
CREATE TABLE fahrerunterlagen (
    fahrerunterlage_id int  NOT NULL,
    typ_id int  NOT NULL,
    titel char(20)  NOT NULL,
    spreicherzeit time  NOT NULL,
    aenderungszeit time  NOT NULL,
    einreichungszeit time,
    status char(20)  NOT NULL,
    fahrer_pers_nr char  NOT NULL,
    CONSTRAINT fahrerunterlagen_pk PRIMARY KEY (fahrerunterlage_id)
);


-- Table: status
CREATE TABLE status (
    statusname char(20)  NOT NULL,
    CONSTRAINT status_pk PRIMARY KEY (statusname)
);


-- Table: unterlagentypen
CREATE TABLE unterlagentypen (
    typ_id int  NOT NULL,
    typname char(50)  NOT NULL,
    tabellename char(50)  NOT NULL,
    CONSTRAINT typeid PRIMARY KEY (typ_id)
);


-- Table: fundzettel
CREATE TABLE fundzettel (
    fahrerunterlage_id int  NOT NULL,
    linie char(20)  NOT NULL,
    strecke char(20)  NOT NULL,
    fundort char(20)  NOT NULL,
    wg_nr char(20)  NOT NULL,
    datum date  NOT NULL,
    uhrzeit time  NOT NULL,
    fundsache char(100)  NOT NULL,
    vom_fahrgast_gefunden boolean,
    ueber_fuenfzig_euro boolean,
    fahrer_name char(50),
    stamm_nr char(20),
    b char(10),
    finder_name char(50),
    strasse char(50),
    hausnr char(5),
    plz char(6),
    wohnort char(50),
    wert int  NOT NULL,
    bemerkung char(100),
    sofortige_rueckgabe boolean,
    CONSTRAINT fundzettel_pk PRIMARY KEY (fahrerunterlage_id)
);

-- Table: urlaubsantrag
CREATE TABLE urlaubsantrag (
    fahrerunterlage_id int  NOT NULL,
    b char(20),
    gl char(20),
    schulpflicht boolean,
    osterferien boolean,
    herbstferien boolean,
    urlaubsanspruch int,
    CONSTRAINT urlaubsantrag_pk PRIMARY KEY (fahrerunterlage_id)
);

-- Table: document_urlaubstage
CREATE TABLE urlaubstage (
    tag_id int  NOT NULL,
    urlaubsantrag_id int  NOT NULL,
    typ char(1)  NOT NULL,
    datum date  NOT NULL,
    wichtigkeit int,
    CONSTRAINT urlaubstage_pk PRIMARY KEY (tag_id)
);


-- Table: urlaubstyp
CREATE TABLE urlaubstyp (
    typname char(1)  NOT NULL,
    CONSTRAINT urlaubstyp_pk PRIMARY KEY (typname)
);


-- foreign keys


-- Reference: form_findings_forms (table: fundzettel)
ALTER TABLE fundzettel ADD CONSTRAINT fundzettel_fahrerunterlagen
    FOREIGN KEY (fahrerunterlage_id)
    REFERENCES fahrerunterlagen (fahrerunterlage_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: form_urlaubstage_form_vacation (table: document_urlaubstage)
ALTER TABLE urlaubstage ADD CONSTRAINT urlaubstage_urlaubsantrag
    FOREIGN KEY (urlaubsantrag_id)
    REFERENCES urlaubsantrag (fahrerunterlage_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: form_vacation_forms (table: urlaubsantrag)
ALTER TABLE urlaubsantrag ADD CONSTRAINT urlaubsantrag_fahrerunterlagen
    FOREIGN KEY (fahrerunterlage_id)
    REFERENCES fahrerunterlagen (fahrerunterlage_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: forms_form_types (table: fahrerunterlagen)
ALTER TABLE fahrerunterlagen ADD CONSTRAINT fahrerunterlagen_unterlagentypen
    FOREIGN KEY (typ_id)
    REFERENCES unterlagentypen (typ_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: status_fahrerunterlagen (table: fahrerunterlagen)
ALTER TABLE fahrerunterlagen ADD CONSTRAINT fahrerunterlagen_status
    FOREIGN KEY (status)
    REFERENCES status (statusname)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: urlaubstyp_document_urlaubstage (table: document_urlaubstage)
ALTER TABLE urlaubstage ADD CONSTRAINT urlaubstage_urlaubstyp
    FOREIGN KEY (typ)
    REFERENCES urlaubstyp (typname)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

