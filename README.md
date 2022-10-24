# Progetto per l'esame di Basi di Dati del Corso di Laurea in Informatica presso l'Università degli Studi di Salerno

L'obiettivo di questo progetto è quello di creare una base di dati per una catena di negozi specializzata in vendita di videogiochi, gadget e console.


## Breve descrizione della realtà considerata (tratta dalla [specifica completa](https://github.com/Gianpyy/ProgettoBD/blob/main/Documento%20completo%20progetto%20BD%20.pdf))

Si vuole progettare una base di dati per una catena di negozi specializzata in vendita di videogiochi, gadget e console.

I vari punti vendita, di cui teniamo traccia del numero di dipendenti che ci lavorano, sono identificati dal loro indirizzo, comprensivo di città e provincia.
In ogni punto vendita sono disponibili per l’acquisto diversi prodotti che rientrano principalmente in 3 categorie: videogiochi, gadget e console. 

Ogni prodotto è identificato dal proprio codice a barre. Di ogni prodotto ci interessa il nome, il prezzo di listino, la quantità disponibile ed un eventuale sconto; per i gadget salviamo anche il produttore e la serie a cui appartengono.

Per i videogiochi ci interessa sapere la piattaforma, la categoria, la data di rilascio, il numero di giocatori, le condizioni e la valutazione PEGI. Inoltre è disponibile una breve descrizione del prodotto. Per le console vogliamo conservare la famiglia di console e l’anno di rilascio.

Per ogni acquisto effettuato da un cliente vogliamo tener traccia del numero acquisto, che identifica un acquisto, della data in cui è stato effettuato, dell’importo totale.

Vogliamo inoltre tener traccia dei magazzini presso cui i negozi fanno rifornimento. Ci interessa sapere il loro indirizzo, i prodotti al loro interno e la quantità in magazzino.

Per ogni dipendente, identificato dal proprio employee number, ci interessa conservare i dati anagrafici, le ore settimanali ed il punto vendita in cui lavorano.
Per titolari soci, di cui ci interessano i dati anagrafici, il punto vendita di cui sono i titolari e la quota percentuale.

Ai clienti è data la possibilità di richiedere una carta fedeltà. 

Ogni carta fedeltà è legata univocamente ad un cliente, di cui ci interessa conservare i dati anagrafici; di ogni carta ci interessa conservare il livello ed il numero di tessera (che la identifica univocamente)


## Come installare e usare la demo per l'applicazione

Prerequisi: 
- MySQL
- Java

1. Tramite la shell di MySQL oppure MySQL Workbench eseguire, in ordine, gli script `createDB.sql` e `populateDB.sql`
2. Importare `CatenaDiNegozi` in un IDE che supporti Java
3. Eseguire il Run sulla classe `src/catenadinegozi/DBApplication.java` 
4. Seguire le istruzioni a schermo


## Author
Gianpio Silvestri - [Gianpyy](https://github.com/Gianpyy)
