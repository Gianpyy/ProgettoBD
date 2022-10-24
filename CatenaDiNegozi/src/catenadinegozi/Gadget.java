package catenadinegozi;

public class Gadget extends Prodotto {
	private String produttore;
	private String serie;
	
	public Gadget() {
		
	}
	
	public Gadget(String barcode, String nome, int prezzo, String produttore, String serie) {
		super(barcode, nome, prezzo);
		this.produttore = produttore;
		this.serie = serie;
	}

	public String getProduttore() {
		return produttore;
	}

	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	
}
