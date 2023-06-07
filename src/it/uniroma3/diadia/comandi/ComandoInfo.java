package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoInfo extends AbstractComando {
	
	private static final String NOME = "info";
	
	public ComandoInfo() {
		super.setNome(NOME);
	}
	@Override
	public void esegui(Partita partita) {
		super.getIO().showMsg("Stanza corrente: " + partita.getStanzaCorrente().getDescrizione());
		super.getIO().showMsg("Informazioni sulla partita: " + partita.getGiocatore().toString());
	}
	

}
