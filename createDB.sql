drop database if exists CatenaNegozi;
create database CatenaNegozi;
use CatenaNegozi;
drop user 'negozio'@'%';
create user 'negozio'@'%' identified by 'negozio';
grant all on catenanegozi.* to 'negozio'@'%';

drop table if exists PuntoVendita;
create table PuntoVendita
(
	codice int PRIMARY KEY NOT NULL auto_increment,
    via varchar(60) NOT NULL,
    civico varchar(3) NOT NULL,
    citta varchar(30) NOT NULL,
    provincia char(2) NOT NULL,
    numeroDipendenti int NOT NULL
);

drop table if exists Dipendente;
create table Dipendente
(
	employeeNumber char(8) PRIMARY KEY NOT NULL,
    nome varchar(20) NOT NULL,
    cognome varchar(20) NOT NULL,
    dataDiNascita date NOT NULL, 
    sesso char(1) NOT NULL,
    oreSettimanali int NOT NULL,
    puntoVendita int NOT NULL,
    FOREIGN KEY (puntoVendita) REFERENCES PuntoVendita(codice) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (2022 - YEAR(dataDiNascita) >= 18) -- vincolo sull'età dei dipendenti -- 
);

drop table if exists TitolareSocio;
create table TitolareSocio
(
	codiceFiscale char(16) PRIMARY KEY NOT NULL,
    nome varchar(20) NOT NULL,
    cognome varchar(20) NOT NULL,
    dataDiNascita date NOT NULL, 
    sesso char(1) NOT NULL, 
    quota int NOT NULL,
	puntoVendita int NOT NULL,
    FOREIGN KEY (puntoVendita) REFERENCES PuntoVendita(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists Magazzino;
create table Magazzino
(
	codice int PRIMARY KEY NOT NULL auto_increment,
    via varchar(50) NOT NULL,
    civico varchar(3) NOT NULL,
    citta varchar(30) NOT NULL,
    provincia char(2) NOT NULL
);

drop table if exists Prodotto;
create table Prodotto
(
	barcode char(13) PRIMARY KEY NOT NULL,
    nome varchar(80) NOT NULL,
    prezzo float NOT NULL
);

drop table if exists Console;
create table Console
(
	prodotto char(13) PRIMARY KEY NOT NULL,
    famiglia varchar(30) NOT NULL,
    annoRilascio int NOT NULL,
    FOREIGN KEY (prodotto) REFERENCES Prodotto(barcode) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists Gadget;
create table Gadget
(
	prodotto char(13) PRIMARY KEY NOT NULL,
    produttore varchar(30) NOT NULL,
    serie varchar(50) NOT NULL,
    FOREIGN KEY (prodotto) REFERENCES Prodotto(barcode) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists Videogioco;
create table Videogioco
(
	prodotto char(13) NOT NULL PRIMARY KEY,
    piattaforma varchar(20) NOT NULL,
    descrizione varchar(250) NOT NULL,
    dataRilascio date NOT NULL,
    condizioni varchar(10) NOT NULL, 
    numeroGiocatori char(3) NOT NULL,
    etaPEGI int NOT NULL,
    FOREIGN KEY (prodotto) REFERENCES Prodotto(barcode) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists Categoria;
create table Categoria 
(
	nome varchar(30) NOT NULL,
    videogioco char(13) NOT NULL,
    PRIMARY KEY(nome, videogioco),
    FOREIGN KEY (videogioco) REFERENCES Videogioco(prodotto) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists Contenuto;
create table Contenuto
(
	tipo varchar(20) NOT NULL,
    videogioco char(13) NOT NULL,
    PRIMARY KEY(tipo, videogioco),
    FOREIGN KEY (videogioco) REFERENCES Videogioco(prodotto) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists Acquisto;
create table Acquisto
(
	numeroOrdine char(13) PRIMARY KEY NOT NULL,
    dataAcquisto date NOT NULL,
    metodoPagamento varchar(15) NOT NULL,
    importoTotale float NOT NULL,
    acconto float,
    stato varchar(30),
    indirizzoSpedizione varchar(150)
);

drop table if exists Cliente;
create table Cliente
(
	codiceFiscale char(16) PRIMARY KEY NOT NULL,
    nome varchar(20) NOT NULL,
    cognome varchar(30) NOT NULL,
	sesso char(1) NOT NULL,
    dataDiNascita date NOT NULL
    CHECK (2022 - YEAR(dataDiNascita) >= 16) -- vincolo sull'età dei clienti -- 
);

drop table if exists CartaFedelta;
create table CartaFedelta
(
	numeroTessera char(12) PRIMARY KEY NOT NULL,
    livello int NOT NULL,
    cliente char(16) NOT NULL,
    FOREIGN KEY (cliente) REFERENCES Cliente(codiceFiscale) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists FA_RIFORNIMENTO;
create table FA_RIFORNIMENTO
(
	puntoVendita int NOT NULL,
    magazzino int NOT NULL,
    PRIMARY KEY(puntoVendita, magazzino),
    FOREIGN KEY (puntoVendita) REFERENCES PuntoVendita(codice) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (magazzino) REFERENCES Magazzino(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists VENDE;
create table VENDE
(
	puntoVendita int NOT NULL,
    prodotto char(13) NOT NULL,
    quantita int NOT NULL,
    sconto int NOT NULL,
    PRIMARY KEY(puntoVendita, prodotto),
    FOREIGN KEY (puntoVendita) REFERENCES PuntoVendita(codice) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (prodotto) REFERENCES Prodotto(barcode) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists DEPOSITATO;
create table DEPOSITATO
(
	prodotto char(13) NOT NULL,
    magazzino int NOT NULL,
    quantita int NOT NULL,
    PRIMARY KEY(prodotto, magazzino),
    FOREIGN KEY (prodotto) REFERENCES Prodotto(barcode) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (magazzino) REFERENCES Magazzino(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists CONTENUTO_IN;
create table CONTENUTO_IN
(
	prodotto char(13) NOT NULL,
    acquisto char(13) NOT NULL,
    quantita int not null,
    PRIMARY KEY (prodotto, acquisto),
    FOREIGN KEY (prodotto) REFERENCES Prodotto(barcode) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (acquisto) REFERENCES Acquisto(numeroOrdine) ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists EFFETTUATO_DA;
create table EFFETTUATO_DA
(
	acquisto char(13) NOT NULL,
    cliente char(16) NOT NULL,
    PRIMARY KEY(acquisto, cliente),
    FOREIGN KEY (acquisto) REFERENCES Acquisto(numeroOrdine) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cliente) REFERENCES Cliente(codiceFiscale) ON DELETE CASCADE ON UPDATE CASCADE
);
