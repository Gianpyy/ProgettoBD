package catenadinegozi;

import java.util.Date;

public class Acquisto {
	private String numeroOrdine, metodoPagamento, stato, indirizzoSpedizione;
	private Date data;
	private float importoTotale, acconto;
	
	public Acquisto() {
		
	}

	public Acquisto(String numeroOrdine, String metodoPagamento, String stato, String indirizzoSpedizione, Date data,
			float importoTotale, float acconto) {
		super();
		this.numeroOrdine = numeroOrdine;
		this.metodoPagamento = metodoPagamento;
		this.stato = stato;
		this.indirizzoSpedizione = indirizzoSpedizione;
		this.data = data;
		this.importoTotale = importoTotale;
		this.acconto = acconto;
	}

	public String getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(String numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(float importoTotale) {
		this.importoTotale = importoTotale;
	}

	public float getAcconto() {
		return acconto;
	}

	public void setAcconto(float acconto) {
		this.acconto = acconto;
	}
	
	public void print() {
		System.out.printf("numero ordine: %12s | ", numeroOrdine);
		System.out.print("data acquisto: " + data + " | ");
		System.out.printf("metodo pagamento: %10s | importo totale: %.2f | acconto: %.2f | stato: %s | indirizzo spedizione: %s ", metodoPagamento, importoTotale, acconto, stato, indirizzoSpedizione);
	}
}
