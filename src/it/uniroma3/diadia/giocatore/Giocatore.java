package it.uniroma3.diadia.giocatore;

public class Giocatore {

	static final private int CFU_INIZIALI = 20;

	private int cfu;
	private Borsa zaino;


	public Giocatore() {

		this.cfu = CFU_INIZIALI; 
		this.zaino = new Borsa();
	}

	/**
	 * 
	 * @return il numero di CFU corrente
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * aggiorna il numero di Cfu
	 * @param cfu aggiornate
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}

	/**
	 * 
	 * @return il contenuto dello zaino
	 */
	public Borsa getBorsa() {
		return zaino;
	}
	@Override
	public String toString() {
		return "Numero CFU: " + this.cfu + " Contenuto borsa: " + this.zaino.toString();
	}

}
