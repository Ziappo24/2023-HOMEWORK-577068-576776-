package it.uniroma3.diadia;

public interface IO {
	/**
	 * Stampa il messaggio che apparte sullo schermo per l'utente
	 * @param msg
	 */
	public void showMsg(String msg);
	
	/**
	 * Legge tutta una riga inserita come input dalla tastiera
	 * @return la ruga che viene scansionata
	 */
	public String readLine();
}
