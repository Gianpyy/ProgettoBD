package catenadinegozi;

public class Prodotto {
	
	private String barcode, nome;
	private float prezzo;
	
	public Prodotto() {	}
	
	public Prodotto(String barcode, String nome, float prezzo) {
		super();
		this.barcode = barcode;
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	//metodi utility
	public void print() {
		System.out.printf("prodotto: %13s | nome: %20s | prezzo: %.2f", barcode, nome, prezzo);
	}
	
}
