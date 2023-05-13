package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;

	public Partita(Labirinto labirinto){
		
		this.labirinto = labirinto;
		this.finita = false;
		stanzaCorrente = this.labirinto.getStanzaIngresso();
		stanzaVincente = this.labirinto.getStanzaVincente();
		giocatore = new Giocatore();
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * 
	 * @return restituisce la stanza vincente
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * @return Restituisce la stanza corrente
	 *
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * 
	 * @return il giocatore
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}

	/**
	 * Aggiorna la stanza corrente con la prossima stanza
	 * @return vero se partita vinta
	 */
	public void setStanzaCorrente(Stanza prossimaStanza) {
		this.stanzaCorrente = prossimaStanza;
	}
	
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() > 0;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
}
