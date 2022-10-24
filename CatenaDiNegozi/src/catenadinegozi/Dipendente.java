package catenadinegozi;

import java.util.Date;

public class Dipendente {
	private String employeeNumber, nome, cognome;
	private Date dataDiNascita;
	private String sesso;
	private int oreSettimanali, puntoVendita;
	
	public Dipendente() {
		super();
	}

	public Dipendente(String employeeNumber, String nome, String cognome, Date dataDiNascita, String sesso,
			int oreSettimanali, int puntoVendita) {
		super();
		this.employeeNumber = employeeNumber;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.sesso = sesso;
		this.oreSettimanali = oreSettimanali;
		this.puntoVendita = puntoVendita;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
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

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public int getOreSettimanali() {
		return oreSettimanali;
	}

	public void setOreSettimanali(int oreSettimanali) {
		this.oreSettimanali = oreSettimanali;
	}
	
	public int getPuntoVendita() {
		return puntoVendita;
	}

	public void setPuntoVendita(int puntoVendita) {
		this.puntoVendita = puntoVendita;
	}

	public void print() {
		System.out.printf("employeNumber: %8s | nome: %10s | cognome %10s | ", employeeNumber, nome, cognome);
		System.out.print("data di nascita: " + dataDiNascita + " | ");
		System.out.printf("sesso: %1s | ore settimanali: %2d ", sesso, oreSettimanali);
	}
	
}
