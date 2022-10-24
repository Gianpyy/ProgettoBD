package catenadinegozi;

public class Console extends Prodotto{
	private String famiglia;
	private String annoRilascio;
	
	public Console() {
		
	}
	
	public Console(String barcode, String nome, int prezzo, String famiglia, String annoRilascio) {
		super(barcode, nome, prezzo);
		this.famiglia = famiglia;
		this.annoRilascio = annoRilascio;
	}
	public String getFamiglia() {
		return famiglia;
	}
	public void setFamiglia(String famiglia) {
		this.famiglia = famiglia;
	}
	public String getAnnoRilascio() {
		return annoRilascio;
	}
	public void setAnnoRilascio(String annoRilascio) {
		this.annoRilascio = annoRilascio;
	}
	
	public void print() {
		System.out.printf("prodotto: %13s | nome: %20s | prezzo: %.2f\n | famiglia: %s | anno rilascio: %s", super.getBarcode(), super.getNome(), super.getPrezzo(), famiglia, annoRilascio);
	}
}
