package catenadinegozi;

import java.sql.Date;

public class Cliente {
	private String codiceFiscale, nome, cognome, sesso;
	private java.util.Date dataDiNascita;
	
	public Cliente() { }
	
	public Cliente(String codiceFiscale, String nome, String cognome, String sesso, java.util.Date dataDiNascita) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public java.util.Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(java.util.Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	//metodi utility
	
	public void printCustomer() {
		System.out.printf("codicefiscale: %s | nome: %-10s | cognome: %-16s"
				+ " | sesso: %1s | datadinascita: ",codiceFiscale, nome, cognome, sesso);
		System.out.println(dataDiNascita);
	}
	
}
