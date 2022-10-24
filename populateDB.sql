use CatenaNegozi;

INSERT INTO puntovendita(via, civico, citta, provincia, numerodipendenti)
VALUES ("Via Michelangelo Buonarroti", "15", "Angri", "SA", 3), 
("Corso Vittorio Emanuele II", "65", "Milano", "MI", 5),
("Corso Imperatore", "42", "Roma", "RO", 7),
("Via Lemani Dalnaso", "1", "L'Aquila", "AQ", 1),
("Via Mariano Tuccella", "51", "Bologna", "BO", 3);

INSERT INTO dipendente(employeenumber, nome, cognome, datadinascita, sesso, oresettimanali, puntovendita)
VALUES (12345678, "Franco", "Franco", "1971-02-04", "M", 40, 2),
(12345679, "Giorgia", "Meloni", "1981-03-04", "F", 40, 2),
(12345670, "Franco", "Franchino", "1977-02-05", "M", 20, 2),
(12345671, "Immanuel", "Casto", "1971-02-04", "M", 40, 1),
(12345672, "Franco", "Battiato", "1971-02-04", "M", 40, 1);

INSERT INTO titolaresocio(codicefiscale, nome, cognome, datadinascita, sesso, quota, puntovendita)
VALUES ("PVHNFZ72T65F250E", "Paolo", "Bonolis", "1972-05-28", "M", 50, 2),
("PCSBHV64P23H631I", "Eleonora", "Buono", "1998-05-18", "F", 50, 1),
("TLAWKP35P58F456I", "Drako", "Laura", "1969-02-18", "F", 100, 3),
("YPYRBE41M58B031L", "Elena", "Gervasoni", "1989-07-21", "M", 70, 4),
("GGMDPD36T53A704J", "Bobbe", "Malle", "1969-02-18", "M", 100, 5);

INSERT INTO magazzino(via, civico, citta, provincia)
VALUES ("Corso Vittorio Emanuele II", "9", "Milano", "MI"),
("Corso Imperatore", "42", "Roma", "9"),
("Via Lemani Dalnaso", "9", "L'Aquila", "AQ"),
("Via Mariano Tuccella", "9", "Bologna", "BO");

INSERT INTO prodotto(barcode, nome, prezzo)
VALUES ("1234567890123", "The Legend of Zelda: Tears of the Kingdom", 60.00),
("1234567890124", "Action Figure Solid Snake", 180.00),
("1234567890125", "PlayStation 5", 550.00),
("1234567890126", "Nintendo Switch OLED", 350.00),
("1234567890127", "Peluche Super Mario", 18.00),
("1234567890128", "Red Dead Redemption 2", 60.00),
("1234567890129", "Risk of Rain 2", 20.00),
("1234567890130", "Minecraft", 20.00),
("1234567890131", "Red Dead Redemption 2", 40.00);

INSERT INTO console(prodotto, famiglia, annorilascio)
VALUES ("1234567890125", "PlayStation 5", 2020),
("1234567890126", "Nintendo Switch", 2021);

INSERT INTO gadget(prodotto, produttore, serie)
VALUES ("1234567890124", "Konami", "Metal Gear Solid"),
("1234567890127", "Nintendo", "Super Mario");

INSERT INTO videogioco(prodotto, piattaforma, descrizione, datarilascio, condizioni, numerogiocatori, etapegi)
VALUES ("1234567890123", "Nintendo Switch", "Sequel di The Legend of Zelda: Breath of the Wild", "2017-03-03", "Nuovo", "1-1", 12),
("1234567890128", "Xbox One", "Sequel di Red Dead Redempion", "2018-10-26", "Nuovo", "1-4", 18),
("1234567890131", "PlayStation 4", "Sequel di Red Dead Redempion", "2018-10-26", "Usato", "1-4", 18),
("1234567890129", "Steam", "Roguelike in terza persona con difficoltà che sale col passare del tempo", "2019-03-28", "Nuovo", "1-4", 3),
("1234567890130", "Proprietaria", "Pioniere del genere Sandbox, Minecraft è il videogioco più venduto al mondo con oltre 220 milioni di copie vendute", "2011-11-18", "Nuovo", "1-9", 3);

INSERT INTO categoria(nome, videogioco)
VALUES ("Azione", "1234567890123"),
("Avventura", "1234567890123"),
("Open World", "1234567890123"),
("Azione", "1234567890128"),
("Open World", "1234567890128"),
("Sparatutto in terza persona", "1234567890128"),
("Azione", "1234567890131"),
("Open World", "1234567890131"),
("Sparatutto in terza persona", "1234567890131"),
("Roguelike", "1234567890129"),
("Sandbox", "1234567890130");


INSERT INTO contenuto(tipo, videogioco)
VALUES ("Violenza", "1234567890123"),
("Violenza", "1234567890131"),
("Linguaggio Scurrile", "1234567890131"),
("Gioco online", "1234567890131"),
("Linguaggio Scurrile", "1234567890128"),
("Gioco online", "1234567890128"),
("Gioco online", "1234567890129"),
("Gioco online", "1234567890130");

INSERT INTO acquisto(numeroordine, dataacquisto, metodopagamento, importototale, acconto, stato, indirizzospedizione)
VALUES ("123-456-7890", "2020-07-25", "MasterCard", 100.00, 0.00, "Consegnato", "Via Raffaele Libroia 2"),
("123-456-7891", "2022-02-02", "Contanti", 27.50, null, null, null),
("123-456-7892", "2019-02-02", "Contanti", 50.00, null, null, null),
("123-456-7893", "2020-05-22", "PayPal", 60.00, 5.00, "Prenotato", "Via Michelangelo Buonarroti 2"),
("123-456-7894", "2021-05-12", "PayPal", 60.00, 5.00, "Prenotato", "Via Michelangelo Buonarroti 7"),
("123-456-7895", "2021-07-12", "PayPal", 60.00, 5.00, "In Consegna", "Via Michelangelo Buonarroti 7");

INSERT INTO cliente(codicefiscale, nome, cognome, datadinascita, sesso)
VALUES ("TLAWKP35P68F456I", "Silvio", "Berlusconi", "1965-05-21", "M"),
("RLAWKP37P68F456F", "Silvia", "Berluscona", "1965-06-21", "F"),
("TLARKT05P69F456I", "Gesualda", "Bianchi", "1957-05-21", "F"),
("RFARKTF55P6F456I", "Sebastiano", "Bilecco", "2000-01-21", "M");

INSERT INTO cartafedelta(numerotessera,livello,cliente)
VALUES ("123456789", 3, "TLAWKP35P68F456I"),
("1234567855", 5, "RFARKTF55P6F456I"),
("1234567850", 5, "TLARKT05P69F456I");


INSERT INTO fa_rifornimento(puntovendita, magazzino)
VALUES (1,1), (1,2), (2,1), (3,3);

INSERT INTO vende(puntovendita, prodotto, quantita, sconto)
VALUES (3, "1234567890123", 70, 0),
(4, "1234567890123", 50, 20),
(1, "1234567890123", 0, 0),
(3, "1234567890126", 100, 0),
(1,"1234567890125", 0, 0),
(2,"1234567890125", 0, 0),
(3, "1234567890125", 0, 0),
(5, "1234567890130", 150, 50),
(4,"1234567890130", 200, 0);

INSERT INTO depositato(prodotto, magazzino, quantita)
VALUES ("1234567890123", 4, 20),
("1234567890123", 3, 100),
("1234567890126", 4, 0),
("1234567890125", 1, 250),
("1234567890125", 3, 0),
("1234567890125", 2, 0),
("1234567890130", 1, 50),
("1234567890130", 2, 100);

INSERT INTO contenuto_in(prodotto, acquisto, quantita)
VALUES ("1234567890123", "123-456-7890", 1),
("1234567890123", "123-456-7891", 1),
("1234567890126", "123-456-7894", 1),
("1234567890125", "123-456-7891", 2),
("1234567890125", "123-456-7894", 1),
("1234567890125", "123-456-7890", 3),
("1234567890130", "123-456-7893", 50),
("1234567890130", "123-456-7894", 1);

INSERT INTO effettuato_da(acquisto, cliente)
VALUES ("123-456-7890", "TLAWKP35P68F456I"),
("123-456-7891", "RLAWKP37P68F456F"),
("123-456-7894", "RLAWKP37P68F456F"),
("123-456-7893", "TLARKT05P69F456I");





