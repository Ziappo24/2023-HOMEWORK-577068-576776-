package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai implements Comando {
	

	private String parametro;
	private IO io;
	private static final String NOME = "vai";
	
	/**
	 * Esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza nextStanza = null;
		/* nessuna direzione */
		if(parametro==null)  {
			this.io.showMsg("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		nextStanza = stanzaCorrente.getStanzaAdiacente(this.parametro);
		if(nextStanza==null) {
			this.io.showMsg("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(nextStanza);
		this.io.showMsg(partita.getStanzaCorrente().getNome());
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(partita.getGiocatore().getCfu()-1);
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
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
		return this.parametro;
	}
}
