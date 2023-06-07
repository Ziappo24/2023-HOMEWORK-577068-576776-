package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{


	private static final String NOME = "fine";
	public static final String MESSAGGIO_FINE = "Grazie per aver giocato!";

	public ComandoFine() {
		super.setNome(NOME);
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		super.getIO().showMsg(MESSAGGIO_FINE);  // si desidera smettere
	}

}
