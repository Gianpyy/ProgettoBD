package catenadinegozi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Videogioco extends Prodotto{
	
	private String piattaforma, descrizione, condizioni, numeroGiocatori;
	private Date dataRilascio;
	private List<String> categorie;
	private List<String> contenutoPEGI;
	private int etaPEGI;
	
	public Videogioco() { 
		super();
		categorie = new ArrayList<String>();
		contenutoPEGI = new ArrayList<String>();
	}
	
	public Videogioco(String barcode, String nome, int prezzo, String piattaforma, String descrizione,
			String condizioni, String numeroGiocatori, Date dataRilascio, List<String> categorie,
			List<String> contenutoPEGI, int etaPEGI) {
		super(barcode, nome, prezzo);
		this.piattaforma = piattaforma;
		this.descrizione = descrizione;
		this.condizioni = condizioni;
		this.numeroGiocatori = numeroGiocatori;
		this.dataRilascio = dataRilascio;
		this.categorie = categorie;
		this.contenutoPEGI = contenutoPEGI;
		this.etaPEGI = etaPEGI;
	}

	public Date getDataRilascio() {
		return dataRilascio;
	}
	public String getPiattaforma() {
		return piattaforma;
	}
	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCondizioni() {
		return condizioni;
	}
	public void setCondizioni(String condizioni) {
		this.condizioni = condizioni;
	}
	public String getNumeroGiocatori() {
		return numeroGiocatori;
	}
	public void setNumeroGiocatori(String numeroGiocatori) {
		this.numeroGiocatori = numeroGiocatori;
	}
	public List<String> getCategorie() {
		return categorie;
	}
	public void setCategorie(List<String> categorie) {
		this.categorie = categorie;
	}
	public List<String> getContenutoPEGI() {
		return contenutoPEGI;
	}
	public void setContenutoPEGI(List<String> contenutoPEGI) {
		this.contenutoPEGI = contenutoPEGI;
	}
	public int getEtaPEGI() {
		return etaPEGI;
	}
	public void setEtaPEGI(int etaPEGI) {
		this.etaPEGI = etaPEGI;
	}
	public void setDataRilascio(Date dataRilascio) {
		this.dataRilascio = dataRilascio;
	}
	
	//metodi utility
	public void print() {
		System.out.printf("prodotto: %13s | nome: %20s | prezzo: %.2f | piattaforma: %10s | descrizione: %10s | condizioni: %10s | "
				+ "numero giocatori: %3s | eta PEGI: %d\n", super.getBarcode(), super.getNome(), super.getPrezzo(), piattaforma, descrizione, condizioni, numeroGiocatori, etaPEGI);
	}
	
	public void addCategoria(String categoria) {
		categorie.add(categoria);
	}
	
	public void addContenuto(String contenuto) {
		contenutoPEGI.add(contenuto);
	}
}
