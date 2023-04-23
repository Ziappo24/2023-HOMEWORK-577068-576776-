package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoInfo implements Comando {
	
	private IO io;
	private static final String NOME = "info";
	
	@Override
	public void esegui(Partita partita) {
		this.io.showMsg("Stanza corrente: " + partita.getStanzaCorrente().getDescrizione());
		this.io.showMsg("Informazioni sulla partita: " + partita.getGiocatore().toString());
	}
	
	@Override
	public void setParametro(String parametro) { }

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return null;
	}
}
