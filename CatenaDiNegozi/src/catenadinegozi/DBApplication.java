package catenadinegozi;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.time.*;


public class DBApplication {
	
	public DBApplication() {}
	
	private Connection getConnection() throws SQLException { 
		String url = "jdbc:mysql://localhost:3306/catenanegozi?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

		Connection connection = DriverManager.getConnection(url, "negozio", "negozio");
		System.out.println("Connessione OK \n");

		return connection;
	}

	private void releaseConnection(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}

	public void testConnection() {
		try {
			Connection connection = getConnection();
			releaseConnection(connection);
		} catch (Exception e) {
			System.err.println("Connessione Fallita \n");
			System.err.println(e);
		}
	}

	public void release() {
		try {
			DBConnectionPool.releaseAllConnection();
		} catch (SQLException s) {
			System.err.println(s.getMessage());
			Utility.printSQLException(s);
		}
	}
	
	private void insertVideogame(Videogioco v) {
		//operazione 1
		Connection con = null;
		PreparedStatement ps = null;
		String datiProdotto = "INSERT INTO prodotto(barcode,nome,prezzo) VALUES (?,?,?)";
		String datiVideogioco = "INSERT INTO videogioco(prodotto, piattaforma, descrizione, dataRilascio, condizioni, numeroGiocatori, etaPEGI)"
		 		+ " VALUES (?,?,?,?,?,?,?)";
		String datiCategoria = "INSERT INTO categoria(nome, videogioco) VALUES (?,?)";
		String datiContenuto = "INSERT INTO contenuto(tipo, videogioco) VALUES (?,?)";
		
		 try {
			 con = DBConnectionPool.getConnection();
			 
			 
			 //inserimento nella tabella prodotto
			 ps = con.prepareStatement(datiProdotto);
			 ps.setString(1, v.getBarcode());
			 ps.setString(2, v.getNome());
			 ps.setFloat(3, v.getPrezzo());
			 int result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in prodotto OK");
			 }
			 else {
				 System.out.println("Inserimento in prodotto NON RIUSCITO");
			 }
			 ps.close();
			 
			 //inserimento nella tabella videogioco
			 ps = con.prepareStatement(datiVideogioco);
			 ps.setString(1, v.getBarcode());
			 ps.setString(2, v.getPiattaforma());
			 ps.setString(3, v.getDescrizione());
			 ps.setDate(4, Utility.toSqlDate(v.getDataRilascio()));
			 ps.setString(5, v.getCondizioni());
			 ps.setString(6, v.getNumeroGiocatori());
			 ps.setInt(7, v.getEtaPEGI());
			 result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in videogioco OK");
			 }
			 else {
				 System.out.println("Inserimento in videogioco NON RIUSCITO");
			 }
			 
			 //inserimento nella tabella contenuto
			 for (String item : v.getContenutoPEGI()) {
				 ps = con.prepareStatement(datiContenuto);
				 ps.setString(1, item);
				 ps.setString(2, v.getBarcode());
				 result = ps.executeUpdate();
				 if(result > 0) {
					 System.out.println("Inserimento in contenuto OK");
				 }
				 else {
					 System.out.println("Inserimento in contenuto NON RIUSCITO");
				 }
			 }

			 
			//inserimento nella tabella categoria
			 for (String item : v.getCategorie()) {
				 ps = con.prepareStatement(datiCategoria);
				 ps.setString(1, item);
				 ps.setString(2, v.getBarcode());
				 result = ps.executeUpdate();
				 if(result > 0) {
					 System.out.println("Inserimento in categoria OK");
				 }
				 else {
					 System.out.println("Inserimento in categoria NON RIUSCITO");
				 }
			 }
			 
			 //commit di tutte le operazioni
			 con.commit();
		 }
		 catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	
	private void insertGadget(Gadget g) {
		//operazione 2
		Connection con = null;
		PreparedStatement ps = null;
		String datiProdotto = "INSERT INTO prodotto(barcode,nome,prezzo) VALUES (?,?,?)";
		String datiGadget = "INSERT INTO gadget(prodotto,produttore,serie) VALUES (?,?,?)";
		
		 try {
			 con = DBConnectionPool.getConnection();
			 
			 
			 //inserimento nella tabella prodotto
			 ps = con.prepareStatement(datiProdotto);
			 ps.setString(1, g.getBarcode());
			 ps.setString(2, g.getNome());
			 ps.setFloat(3, g.getPrezzo());
			 int result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in prodotto OK");
			 }
			 else {
				 System.out.println("Inserimento in prodotto NON RIUSCITO");
			 }
			 ps.close();
			 
			 //inserimento nella tabella gadget
			 ps = con.prepareStatement(datiGadget);
			 ps.setString(1, g.getBarcode());
			 ps.setString(2, g.getProduttore());
			 ps.setString(3, g.getSerie());
			 result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in gadget OK");
			 }
			 else {
				 System.out.println("Inserimento in gadget NON RIUSCITO");
			 }
			 
			 
			 //commit di tutte le operazioni
			 con.commit();
		 }
		 catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void insertConsole(Console c) {
		//operazione 3
		Connection con = null;
		PreparedStatement ps = null;
		String datiProdotto = "INSERT INTO prodotto(barcode,nome,prezzo) VALUES (?,?,?)";
		String datiConsole = "INSERT INTO console(prodotto,famiglia,annoRilascio) VALUES (?,?,?)";
		
		 try {
			 con = DBConnectionPool.getConnection();
			 
			 
			 //inserimento nella tabella prodotto
			 ps = con.prepareStatement(datiProdotto);
			 ps.setString(1, c.getBarcode());
			 ps.setString(2, c.getNome());
			 ps.setFloat(3, c.getPrezzo());
			 int result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in prodotto OK");
			 }
			 else {
				 System.out.println("Inserimento in prodotto NON RIUSCITO");
			 }
			 ps.close();
			 
			 //inserimento nella tabella console
			 ps = con.prepareStatement(datiConsole);
			 ps.setString(1, c.getBarcode());
			 ps.setString(2, c.getFamiglia());
			 ps.setString(3, c.getAnnoRilascio());
			 result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in console OK");
			 }
			 else {
				 System.out.println("Inserimento in console NON RIUSCITO");
			 }
			 
			 //commit di tutte le operazioni
			 con.commit();
		 }
		 catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void insertEmployee(Dipendente d) {
		//operazione 4
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO dipendente(employeeNumber,nome,cognome,dataDiNascita,sesso,oreSettimanali,puntoVendita) VALUES (?,?,?,?,?,?,?)";
		
		 try {
			 con = DBConnectionPool.getConnection();
			 	 
			 ps = con.prepareStatement(query);
			 ps.setString(1, d.getEmployeeNumber());
			 ps.setString(2, d.getNome());
			 ps.setString(3, d.getCognome());
			 ps.setDate(4, Utility.toSqlDate(d.getDataDiNascita()));
			 ps.setString(5, d.getSesso());
			 ps.setInt(6, d.getOreSettimanali());
			 ps.setInt(7, d.getPuntoVendita());
			 int result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in dipendente OK");
			 }
			 else {
				 System.out.println("Inserimento in dipendente NON RIUSCITO");
			 }

			 con.commit();
		 }
		 catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void insertShop(PuntoVendita pv) {
		//operazione 5
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO puntoVendita(via,civico,citta,provincia,numeroDipendenti) VALUES (?,?,?,?,?)";
		
		 try {
			 con = DBConnectionPool.getConnection();
			 	 
			 ps = con.prepareStatement(query);
			 ps.setString(1, pv.getVia());
			 ps.setString(2, pv.getCivico());
			 ps.setString(3, pv.getCitta());
			 ps.setString(4, pv.getProvincia());
			 ps.setInt(5, pv.getNumeroDipendenti());
			 int result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in puntovendita OK");
			 }
			 else {
				 System.out.println("Inserimento in puntovendita NON RIUSCITO");
			 }

			 con.commit();
		 }
		 catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void insertFideltyCard(CartaFedelta c) {
		//operazione 6
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO cartaFedelta(numeroTessera,livello,cliente) VALUES (?,?,?)";
		
		 try {
			 con = DBConnectionPool.getConnection();
			 
			 
			 ps = con.prepareStatement(query);
			 ps.setString(1, c.getNumeroTessera());
			 ps.setInt(2, c.getLivello());
			 ps.setString(3, c.getCliente());
			 int result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in cartafedelta OK");
			 }
			 else {
				 System.out.println("Inserimento in cartafedelta NON RIUSCITO");
			 }

			 con.commit();
		 }
		 catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	
	private void insertCustomer(Cliente c) {
		//operazione 7
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO cliente(codiceFiscale,nome,cognome,dataDiNascita,sesso) VALUES (?,?,?,?,?)";
		
		 try {
			 con = DBConnectionPool.getConnection();
			 
			 
			 ps = con.prepareStatement(query);
			 ps.setString(1, c.getCodiceFiscale());
			 ps.setString(2, c.getNome());
			 ps.setString(3, c.getCognome());
			 ps.setDate(4, Utility.toSqlDate(c.getDataDiNascita()));
			 ps.setString(5, c.getSesso());
			 int result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("Inserimento in cliente OK");
			 }
			 else {
				 System.out.println("Inserimento in cliente NON RIUSCITO");
			 }

			 con.commit();
		 }
		 catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void findCustomerByCardLevel(int level) {
		//operazione 8
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT c.* FROM cliente c,cartafedelta cf WHERE c.codiceFiscale = cf.cliente AND cf.livello = ?";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, level);
			rs = ps.executeQuery();
			
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setCodiceFiscale(rs.getString("codiceFiscale"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setDataDiNascita(rs.getDate("dataDiNascita"));
				c.setSesso(rs.getString("sesso"));
				c.printCustomer();
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void findPegi18Videogame() {
		//operazione 9
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//nella console SQL funziona, qui no se non è tutto sulla stessa riga
		String query = "SELECT p.*,v.* FROM videogioco v, prodotto p, categoria c, contenuto cnt WHERE v.etaPEGI = 18 AND v.prodotto = p.barcode AND v.prodotto = c.videogioco AND v.prodotto = cnt.videogioco GROUP BY p.barcode";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while(rs.next()) {
				Videogioco v = new Videogioco();
				v.setBarcode(rs.getString("barcode"));
				v.setNome(rs.getString("nome"));
				v.setPrezzo(rs.getInt("prezzo"));
				v.setPiattaforma(rs.getString("piattaforma"));
				v.setDescrizione(rs.getString("descrizione"));
				v.setDataRilascio(rs.getDate("dataRilascio"));
				v.setCondizioni(rs.getString("condizioni"));
				v.setNumeroGiocatori(rs.getString("numeroGiocatori"));
				v.setEtaPEGI(18);
				v.print();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void findAllEmployeesInShop(int codice) {
		//operazione 10
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM dipendente d WHERE puntovendita = ?";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, codice);
			rs = ps.executeQuery();
			
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while(rs.next()) {
				Dipendente d = new Dipendente();
				d.setEmployeeNumber(rs.getString("employeeNumber"));
				d.setNome(rs.getString("nome"));
				d.setCognome(rs.getString("cognome"));
				d.setDataDiNascita(rs.getDate("dataDiNascita"));
				d.setSesso(rs.getString("sesso"));
				d.setOreSettimanali(rs.getInt("oresettimanali"));
				d.setPuntoVendita(rs.getInt("puntovendita"));
				d.print();
				System.out.println("");
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printAllShopsWithHolders() {
		//operazione 11
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM puntoVendita pv, titolaresocio ts WHERE pv.codice = ts.puntovendita";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				PuntoVendita pv = new PuntoVendita();
				pv.setCodice(rs.getInt("codice"));
				pv.setVia(rs.getString("via"));
				pv.setCivico(rs.getString("civico"));
				pv.setCitta(rs.getString("citta"));
				pv.setProvincia(rs.getString("provincia"));
				pv.setNumeroDipendenti(rs.getInt("numeroDipendenti"));
				String nomeTitolare = rs.getString("nome");
				String cognomeTitolare = rs.getString("cognome");
				pv.print();
				System.out.printf(" | nome titolare: %10s | cognome titolare: %10s\n", nomeTitolare, cognomeTitolare);
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printAllPurchasesByCustomer(String codiceFiscale) {
		//operazione 12
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT a.*, p.nome, cin.quantita FROM cliente c, effettuato_da eda, acquisto a, contenuto_in cin, prodotto p WHERE c.codicefiscale = ? AND c.codicefiscale = eda.cliente AND eda.acquisto = a.numeroordine AND a.numeroordine = cin.acquisto AND cin.prodotto = p.barcode GROUP BY a.numeroordine";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, codiceFiscale);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Acquisto a = new Acquisto();
				String nomeProdotto;
				int quantita;
				
				a.setNumeroOrdine(rs.getString("numeroordine"));
				a.setData(rs.getDate("dataacquisto"));
				a.setMetodoPagamento(rs.getString("metodopagamento"));
				a.setImportoTotale(rs.getInt("importototale"));
				a.setAcconto(rs.getFloat("acconto"));
				a.setStato(rs.getString("stato"));
				a.setIndirizzoSpedizione(rs.getString("indirizzospedizione"));
				nomeProdotto = rs.getString("nome");
				quantita = rs.getInt("quantita");
				a.print();
				System.out.printf("| prodotto: %s | quantita: %d", nomeProdotto, quantita);
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printItemAvailability() {
		//operazione 13
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT p.barcode, p.nome, v.quantita + d.quantita AS 'Disponibilita Totale' FROM prodotto p, vende v, depositato d WHERE p.barcode = v.prodotto AND p.barcode = d.prodotto GROUP BY p.barcode";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String prodotto = rs.getString("barcode");
				String nome = rs.getString("nome");
				int quantita = rs.getInt("Disponibilita Totale");
				System.out.printf("codice a barre: %s | nome: %s | disponibilità totale: %d\n", prodotto, nome, quantita);
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printUnavailableItemsInShop(int codice) {
		//operazione 14
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT p.barcode,p.nome FROM prodotto p, vende v, puntovendita pv WHERE p.barcode = v.prodotto AND v.puntovendita = pv.codice AND v.quantita = 0 AND pv.codice = ?";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, codice);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String barcode = rs.getString("barcode");
				String nome = rs.getString("nome");
				System.out.printf("prodotto: %s | nome: %s\n", barcode, nome);
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printVideogamesPerReleaseYear(int anno) {
		//operazione 15
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT p.barcode,p.nome FROM prodotto p, videogioco v WHERE p.barcode = v.prodotto AND YEAR(v.datarilascio) = ?";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, anno);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String barcode = rs.getString("barcode");
				String nome = rs.getString("nome");
				System.out.printf("prodotto: %s | nome: %s\n", barcode, nome);
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printGadgetsPerCount() {
		//opreazione 16
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT g.serie, COUNT(*) as numero_gadget FROM gadget g GROUP BY g.serie ORDER BY numero_gadget DESC";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String serie = rs.getString("serie");
				int numeroGadget = rs.getInt("numero_gadget");
				System.out.printf("serie: %s | nu"
						+ ""
						+ "mero gadget: %d\n", serie, numeroGadget);
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printItemsInShop(int codice) {
		//operazione 17
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT p.* FROM prodotto p, vende v, puntovendita pv WHERE p.barcode = v.prodotto AND v.puntovendita = pv.codice AND v.quantita > 0 AND pv.codice = ?";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, codice);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Prodotto p = new Prodotto();
				p.setBarcode(rs.getString("barcode"));
				p.setNome(rs.getString("nome"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.print();
				System.out.print("\n");
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printConsolesFromSameFamily(String famiglia) {
		//operazione 18
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//su sql funziona
		String query = "SELECT * FROM prodotto p, console c WHERE p.barcode = c.prodotto AND c.famiglia LIKE %?%";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, famiglia);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Console c = new Console();
				c.setBarcode(rs.getString("barcode"));
				c.setNome(rs.getString("nome"));
				c.setPrezzo(rs.getFloat("prezzo"));
				c.setFamiglia(rs.getString("famiglia"));
				c.setAnnoRilascio(rs.getString("annorilascio")); 
				c.print();
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printPreorders() {
		//operazione 19
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT a.*, p.*, v.*, c.nome, cnt.tipo FROM acquisto a, contenuto_in cin, prodotto p, videogioco v, contenuto cnt, categoria c WHERE a.numeroordine = cin.acquisto AND cin.prodotto = p.barcode AND p.barcode = v.prodotto AND v.prodotto = c.videogioco AND v.prodotto = cnt.videogioco AND a.stato = 'Prenotato'";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Acquisto a = new Acquisto();
				Videogioco v = new Videogioco();
				a.setNumeroOrdine(rs.getString("numeroordine"));
				a.setData(rs.getDate("dataacquisto"));
				a.setMetodoPagamento(rs.getString("metodopagamento"));
				a.setImportoTotale(rs.getInt("importototale"));
				a.setAcconto(rs.getFloat("acconto"));
				a.setStato(rs.getString("stato"));
				a.setIndirizzoSpedizione(rs.getString("indirizzospedizione"));
				a.print();
				System.out.println("");
				v.setBarcode(rs.getString("barcode"));
				v.setNome(rs.getString("nome"));
				v.setPrezzo(rs.getInt("prezzo"));
				v.setPiattaforma(rs.getString("piattaforma"));
				v.setDescrizione(rs.getString("descrizione"));
				v.setDataRilascio(rs.getDate("dataRilascio"));
				v.setCondizioni(rs.getString("condizioni"));
				v.setNumeroGiocatori(rs.getString("numeroGiocatori"));
				v.setEtaPEGI(18);
				v.print();
				System.out.println("");		
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printDiscountedItemsInShop(int codice) {
		//operazione 20
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT p.*,v.sconto FROM prodotto p, vende v, puntovendita pv WHERE p.barcode = v.prodotto AND v.puntovendita = pv.codice AND pv.codice = ? AND v.sconto > 0";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, codice);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Prodotto p = new Prodotto();
				p.setBarcode(rs.getString("barcode"));
				p.setNome(rs.getString("nome"));
				p.setPrezzo(rs.getFloat("prezzo"));
				int sconto = rs.getInt("sconto");
				p.print();
				System.out.printf(" | sconto: %d\n", sconto);
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	private void printAllOrdersInConsegna() {
		//operazione 21
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM acquisto a WHERE a.stato = 'In Consegna'";
		
		try {
			con = DBConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Acquisto a = new Acquisto();
				a.setNumeroOrdine(rs.getString("numeroordine"));
				a.setData(rs.getDate("dataacquisto"));
				a.setMetodoPagamento(rs.getString("metodopagamento"));
				a.setImportoTotale(rs.getInt("importototale"));
				a.setAcconto(rs.getFloat("acconto"));
				a.setStato(rs.getString("stato"));
				a.setIndirizzoSpedizione(rs.getString("indirizzospedizione"));
				a.print();
			}
			
		}
		catch(SQLException s) {
			 System.err.println(s.getMessage());
			 Utility.printSQLException(s);
		 }
		 finally {
			 try {
				 if(ps != null)
					 ps.close();
				 if(rs != null)
					 rs.close();
			 }
			 catch(SQLException s) {
				 System.err.println(s.getMessage());
				 Utility.printSQLException(s);
			 }
		 }
	}
	
	
	private void ui() throws IOException {
		int scelta = -1, scelta2;
		String scelta3;
		Scanner in = new Scanner(System.in);
		do {
			System.out.printf("**** %-29.22s ****\n", "Operazioni disponibili");
			System.out.println("[1] Inserisci un nuovo videogioco");
			System.out.println("[2] Inserisci un nuovo gadget");
			System.out.println("[3] Inserisci una nuova console");
			System.out.println("[4] Inserisci un nuovo dipendente");
			System.out.println("[5] Inserisci un nuovo punto vendita");
			System.out.println("[6] Inserisci una nuova carta fedeltà");
			System.out.println("[7] Inserisci un cliente");
			System.out.println("[8] Elencare tutti i clienti in possesso di una carta fedeltà almeno di livello 3");
			System.out.println("[9] Elencare tutte le informazioni sui videogiochi la cui valutazione PEGI di età è 18");
			System.out.println("[10] Elencare tutti i dipendenti che lavorano presso un determinato punto vendita");
			System.out.println("[11] Creare un elenco di tutti i punti vendita con nome e cognome dei rispettivi titolari soci");
			System.out.println("[12] Elencare tutte le informazioni sugli acquisti effettuati da un determinato cliente, "
					+ "assieme al nome e alla quantità dei vari prodotti acquistati per ogni acquisto");
			System.out.println("[13] Elencare la disponibilità totale per ogni prodotto, "
					+ "che includa sia la disponibilità nei punti vendita che quella disponibili in magazzini");
			System.out.println("[14] Creare un elenco dei prodotti la cui disponibilità presso un determinato punto vendita è 0");
			System.out.println("[15] Elencare tutti i videogiochi rilasciati in un determinato anno");
			System.out.println("[16] Creare un elenco che conteggia i gadget in base alla serie in ordine decrescente");
			System.out.println("[17] Elencare tutti i prodotti in vendita presso un determinato punto vendita");
			System.out.println("[18] Elencare tutte le console appartenenti alla stessa famiglia");
			System.out.println("[19] Elencare tutte le informazioni riguardanti i preordini, includendo tutte le informazioni sui videogiochi");
			System.out.println("[20] Elencare tutti i prodotti in sconto disponibili presso un punto vendita");
			System.out.println("[21] Elencare tutte le informazioni sugli ordini in consegna");
			System.out.println("[0] Esci dall'applicazione");
			do{
				System.out.print("Inserisci scelta: ");
				scelta = in.nextInt();
				if(scelta < 0 || scelta >21)
					System.out.println("Scelta non valida. Ripetere inserimento.");
			}while(scelta < 0 || scelta >21);
			in.nextLine();
			
			switch(scelta) {
				case 1:
					Videogioco v = new Videogioco();
					String s;
					
					System.out.print("Inserisci codice a barre(12 caratteri): ");
					v.setBarcode(in.nextLine());
					System.out.print("Inserisci nome: ");
					v.setNome(in.nextLine());
					System.out.print("Inserisci prezzo: ");
					v.setPrezzo(in.nextFloat());
					in.nextLine();
					System.out.print("Inserisci piattaforma: ");
					v.setPiattaforma(in.nextLine());
					System.out.print("Inserisci descrizione: ");
					v.setDescrizione(in.nextLine());
					System.out.print("Inserisci data rilascio (aaaa-mm-gg): ");
					java.util.Date dataRilascio = Utility.formatStringToDate(in.nextLine());
					v.setDataRilascio(dataRilascio);
					System.out.print("Inserisci condizioni: ");
					v.setCondizioni(in.nextLine());
					System.out.print("Inserisci numero giocatori: ");
					v.setNumeroGiocatori(in.nextLine());
					System.out.print("Inserisci eta PEGI: ");
					v.setEtaPEGI(in.nextInt());
					in.nextLine();
					System.out.println("Inserisci contenuti PEGI (invio per ogni contenuto, 0 per concludere)");
					do {
						System.out.print(">");
						s = in.nextLine();
						if(!s.equals("0"))
							v.addContenuto(s);
					}while(!s.equals("0"));
					System.out.println("Inserisci categorie (invio per ogni categoria, 0 per concludere)");
					List<String> categorie = new ArrayList<String>();
					do {
						System.out.print(">");
						s = in.nextLine();
						if(!s.equals("0"))
							v.addCategoria(s);
					}while(!s.equals("0"));
					this.insertVideogame(v);
					break;
					
				case 2:
					Gadget g = new Gadget();
					System.out.println("Inserisci codice a barre(12 caratteri): ");
					g.setBarcode(in.nextLine());
					System.out.println("Inserisci nome: ");
					g.setNome(in.nextLine());
					System.out.println("Inserisci prezzo: ");
					g.setPrezzo(in.nextFloat());
					in.nextLine();
					System.out.println("Inserisci produttore: ");
					g.setProduttore(in.nextLine());
					System.out.println("Inserisci serie: ");
					g.setSerie(in.nextLine());
					this.insertGadget(g);
					break;
					
				
				case 3:
					Console c = new Console();
					System.out.println("Inserisci codice a barre(12 caratteri): ");
					c.setBarcode(in.nextLine());
					System.out.println("Inserisci nome: ");
					c.setNome(in.nextLine());
					System.out.println("Inserisci prezzo: ");
					c.setPrezzo(in.nextFloat());
					System.out.println("Inserisci famiglia: ");
					c.setFamiglia(in.nextLine());
					System.out.println("Inserisci anno rilascio: ");
					c.setAnnoRilascio(in.nextLine());
					this.insertConsole(c);
					break;
					
				case 4:
					Dipendente d = new Dipendente();
					System.out.println("Inserisci employee number (8 caratteri): ");
					d.setEmployeeNumber(in.nextLine());
					System.out.println("Inserisci nome: ");
					d.setNome(in.nextLine());
					System.out.println("Inserisci cognome: ");
					d.setCognome(in.nextLine());
					System.out.println("Inserisci data di nascita (aaaa-mm-gg): ");
					java.util.Date dataDiNascita = Utility.formatStringToDate(in.nextLine());
					d.setDataDiNascita(dataDiNascita);
					System.out.println("Inserisci sesso (M/F/O): ");
					d.setSesso(in.nextLine());
					System.out.println("Inserisci ore settimanali: ");
					d.setOreSettimanali(in.nextInt());
					in.nextLine();
					System.out.println("Inserisci codice punto vendita in cui lavora: ");
					d.setPuntoVendita(in.nextInt());
					in.nextLine();
					this.insertEmployee(d);
					break;
					
				//per qualche ragione mi mette il numero dipendenti nella via, ed il numero dipendenti lo lascia a 0
				case 5:
					PuntoVendita pv = new PuntoVendita();
					System.out.println("Inserisci via: ");
					pv.setVia(in.nextLine());
					System.out.println("Inserisci civico: ");
					pv.setCivico(in.nextLine());
					System.out.println("Inserisci città: ");
					pv.setCitta(in.nextLine());
					System.out.println("Inserisci provincia: ");
					pv.setProvincia(in.nextLine());
					System.out.println("Inserisci numero dipendenti: ");
					pv.setVia(in.nextLine());
					this.insertShop(pv);
					break;
					
				
				case 6:
					CartaFedelta cf = new CartaFedelta();
					System.out.println("Inserisci numero tessera (12 caratteri): ");
					cf.setNumeroTessera(in.nextLine());
					System.out.println("Inserisci livello: ");
					cf.setLivello(in.nextInt());
					in.nextLine();
					System.out.println("Inserisci codice fiscale cliente: ");
					cf.setCliente(in.nextLine());
					this.insertFideltyCard(cf);
					break;
				
					
				case 7:
					Cliente c1 = new Cliente();
					System.out.println("Inserisci codice fiscale: ");
					c1.setCodiceFiscale(in.nextLine());
					System.out.println("Inserisci nome: ");
					c1.setNome(in.nextLine());
					System.out.println("Inserisci cognome: ");
					c1.setCognome(in.nextLine());
					System.out.println("Inserisci data di nascita (aaaa-mm-gg): ");
					java.util.Date datadn = Utility.formatStringToDate(in.nextLine());
					c1.setDataDiNascita(datadn);
					System.out.println("Inserisci sesso: ");
					c1.setSesso(in.nextLine());
					this.insertCustomer(c1);
					break;
					
					
				case 8:
					this.findCustomerByCardLevel(3);
					break;
					
				case 9:
					this.findPegi18Videogame();
					break;
					
				case 10:
					System.out.println("Inserisci codice negozio: ");
					scelta2 = in.nextInt();
					this.findAllEmployeesInShop(scelta2);
					break;
					
				case 11:
					this.printAllShopsWithHolders();
					break;
					
				case 12:
					System.out.println("Inserisci codice fiscale cliente: ");
					scelta3 = in.nextLine();
					this.printAllPurchasesByCustomer(scelta3);
					break;
					
				case 13:
					this.printItemAvailability();
					break;
					
				case 14:
					System.out.println("Inserisci codice negozio: ");
					scelta2 = in.nextInt();
					this.printUnavailableItemsInShop(scelta2);
					break;
					
				case 15:
					System.out.println("Inserisci anno di rilascio: ");
					scelta2 = in.nextInt();
					this.printVideogamesPerReleaseYear(scelta2);
					break;
					
				case 16:
					this.printGadgetsPerCount();
					break;
					
				case 17:
					System.out.println("Inserisci codice negozio: ");
					scelta2 = in.nextInt();
					this.printItemsInShop(scelta2);
					break;
				
				case 18:
					System.out.println("Inserisci famiglia console: ");
					scelta3 = in.nextLine();
					this.printConsolesFromSameFamily(scelta3);
					break;
					
				case 19:
					this.printPreorders();
					break;
					
				case 20:
					System.out.println("Inserisci codice negozio: ");
					scelta2 = in.nextInt();
					this.printDiscountedItemsInShop(scelta2);
					break;
					
				case 21:
					this.printAllOrdersInConsegna();
					break;
			}
			
			System.out.println("");
			System.out.println("");
			
			
		}while(scelta != 0);
		
		in.close();
	}
	
	
	public static void main(String[] args) {
		DBApplication db = new DBApplication();
		db.testConnection();

		try {
			System.out.println("**** Benvenuto nel nostro database ****");
			db.ui();
		} catch (IOException e) {
		}
		
		System.out.println("Arrivederci!");
		db.release();
		System.exit(0);
	}

}
