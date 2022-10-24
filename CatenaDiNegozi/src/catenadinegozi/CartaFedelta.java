package catenadinegozi;

public class CartaFedelta {
	private String numeroTessera, cliente;
	private int livello;
	
	public CartaFedelta() {
		
	}
	
	public CartaFedelta(String numeroTessera, int livello, String cliente) {
		super();
		this.numeroTessera = numeroTessera;
		this.livello = livello;
	}

	public String getNumeroTessera() {
		return numeroTessera;
	}

	public void setNumeroTessera(String numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

	public int getLivello() {
		return livello;
	}

	public void setLivello(int livello) {
		this.livello = livello;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
