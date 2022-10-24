package catenadinegozi;

public class PuntoVendita {
	private int codice, numeroDipendenti;
	private String Via, Civico, Citta, Provincia;
	
	public PuntoVendita() { }
	
	public PuntoVendita(int codice, String via, String civico, String citta, String provincia, int numeroDipendenti) {
		super();
		this.codice = codice;
		this.numeroDipendenti = numeroDipendenti;
		Via = via;
		Civico = civico;
		Citta = citta;
		Provincia = provincia;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public int getNumeroDipendenti() {
		return numeroDipendenti;
	}

	public void setNumeroDipendenti(int numeroDipendenti) {
		this.numeroDipendenti = numeroDipendenti;
	}

	public String getVia() {
		return Via;
	}

	public void setVia(String via) {
		Via = via;
	}

	public String getCivico() {
		return Civico;
	}

	public void setCivico(String civico) {
		Civico = civico;
	}

	public String getCitta() {
		return Citta;
	}

	public void setCitta(String citta) {
		Citta = citta;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	
	//metodi utility
	public void print() {
		System.out.printf("codice: %d | via: %10s | civico: %2s | citta: %10s | provincia: %2s | numero dipendenti: %d",codice, Via, Civico, Citta, Provincia, numeroDipendenti);
	}
	
}
