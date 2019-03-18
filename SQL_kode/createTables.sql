DROP TABLES IF EXISTS OvelserITreningsokt, OvelseIGruppe, Treningsokt, Ovelse, Apparat;

CREATE TABLE Treningsokt(
	TreningsoktID INTEGER NOT NULL AUTO_INCREMENT,
    Tidspunkt DATETIME,
    Varighet INTEGER,
    Form INTEGER,
    Prestasjon INTEGER,
    Notat VARCHAR(255),
    CONSTRAINT Treningsokt_PK PRIMARY KEY (TreningsoktID),
    CONSTRAINT FormSjekk CHECK (Form >= 0 AND Form <=10),
    CONSTRAINT PrestasjonSjekk CHECK (Prestasjon >= 0 AND Form <=10));
  
  
CREATE TABLE Apparat(
	ApparatNavn VARCHAR(50) NOT NULL,
    Beskrivelse VARCHAR(255),
    CONSTRAINT Apparat_PK PRIMARY KEY (ApparatNavn));
    
                                                        
CREATE TABLE Ovelse(
	OvelseNavn VARCHAR(50) NOT NULL,
    Beskrivelse VARCHAR(255),
    ApparatNavn VARCHAR(50),
    CONSTRAINT Ovelse_PK PRIMARY KEY (OvelseNavn),
	CONSTRAINT Ovelse_FK FOREIGN KEY (ApparatNavn) 	REFERENCES Apparat(ApparatNavn)
													ON DELETE NO ACTION
													ON UPDATE NO ACTION);
                                                            
CREATE TABLE OvelserITreningsokt(
	TreningsoktID INTEGER NOT NULL,
    OvelseNavn VARCHAR(50) NOT NULL,
    Vekt INTEGER,
    AntallSett INTEGER,
    Beskrivelse VARCHAR(255),
    CONSTRAINT OvelserITreningsokt_PK1 PRIMARY KEY (TreningsoktID, OvelseNavn),	
	CONSTRAINT OvelserITreningsokt_FK1 FOREIGN KEY (TreningsoktID) 	REFERENCES Treningsokt(TreningsoktID)
																	ON UPDATE CASCADE
																	ON DELETE CASCADE,
	CONSTRAINT OvelserITreningsokt_FK2 FOREIGN KEY (OvelseNavn) 	REFERENCES Ovelse(OvelseNavn)
																	ON UPDATE CASCADE
																	ON DELETE NO ACTION);

CREATE TABLE OvelseIGruppe(
	OvelseNavn VARCHAR(50) NOT NULL,
    GruppeNavn VARCHAR(50) NOT NULL,
    CONSTRAINT OvelseIGruppe_PK PRIMARY KEY (OvelseNavn, GruppeNavn),
    CONSTRAINT OvelseIGruppe_FK1 FOREIGN KEY (OvelseNavn)	REFERENCES Ovelse(OvelseNavn)
															ON UPDATE CASCADE
                                                            ON DELETE NO ACTION);
	