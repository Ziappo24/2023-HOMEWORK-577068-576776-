package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Classe Labirinto: ha la responsabilità di creare il labirinto, 
 * di memorizzare la stanza iniziale (entrata) e quella finale (uscita)
 * @author ZIAPPO
 * @see Partita
 * @version base
 */
public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIngresso;

	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c =
				new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIngresso = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}

	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public Stanza getStanzaIngresso() {
		return stanzaIngresso;
	}

	public void setStanzaIngresso(Stanza stanzaIngresso) {
		this.stanzaIngresso = stanzaIngresso;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaIngresso = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaIngresso;
	}
	/*public Labirinto() {
	inizia();
}*/

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	/* private void inizia() {

	 *//* crea gli attrezzi *//*
	Attrezzo lanterna = new Attrezzo("lanterna",3);
	Attrezzo osso = new Attrezzo("osso",1);
	Attrezzo chiave = new Attrezzo("chiave",1);
	Attrezzo coltello = new Attrezzo("coltello",2);
	Attrezzo scala = new Attrezzo("scala", 5);

	  *//* crea stanze del labirinto *//*
	Stanza atrio = new Stanza("Atrio");
	Stanza aulaN11 = new Stanza("Aula N11");
	Stanza aulaN10 = new Stanza("Aula N10");
	Stanza laboratorio = new Stanza("Laboratorio Campus");
	Stanza biblioteca = new Stanza("Biblioteca");

	   *//* stanze particolari *//*
	StanzaMagica mensa = new StanzaMagica("Mensa");
	StanzaBuia bagno = new StanzaBuia("Bagno", "lanterna");
	StanzaBloccata stanzino = new StanzaBloccata("Stanzino", "nord", "chiave");

	    *//* collega le stanze */ /*
	atrio.setNear("nord", biblioteca);
	atrio.setNear("est", aulaN11);
	atrio.setNear("sud", aulaN10);
	atrio.setNear("ovest", laboratorio);
	aulaN11.setNear("nord", mensa);
	aulaN11.setNear("sud", stanzino);
	aulaN11.setNear("est", laboratorio);
	aulaN11.setNear("ovest", atrio);
	aulaN10.setNear("nord", atrio);
	aulaN10.setNear("sud", bagno);
	aulaN10.setNear("est", aulaN11);
	aulaN10.setNear("ovest", laboratorio);
	laboratorio.setNear("est", atrio);
	laboratorio.setNear("ovest", aulaN11);
	biblioteca.setNear("sud", atrio);
	biblioteca.setNear("est", mensa);
	mensa.setNear("sud", aulaN11);
	mensa.setNear("ovest", biblioteca);
	stanzino.setNear("nord", aulaN11);
	stanzino.setNear("est", bagno);
	bagno.setNear("nord", aulaN10);
	bagno.setNear("ovest", stanzino);

    /* pone gli attrezzi nelle stanze *//*
	aulaN10.addAttrezzo(lanterna);
	atrio.addAttrezzo(osso);
	laboratorio.addAttrezzo(chiave);
	stanzino.addAttrezzo(scala);
	mensa.addAttrezzo(coltello);

	// il gioco comincia nell'atrio
    stanzaIngresso = atrio;  
	stanzaVincente = biblioteca;
} */

	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Map<String, Stanza> nomeStanza;
		private Stanza ultimaAggiunta;

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.nomeStanza = new HashMap<>();
		}

		public LabirintoBuilder addStanzaIniziale(String nomeIniziale) {
			Stanza iniziale = new Stanza(nomeIniziale);
			this.labirinto.setStanzaIngresso(iniziale);
			this.aggiungiEAggiorna(iniziale);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeVincente) {
			Stanza vincente = new Stanza(nomeVincente);
			this.labirinto.setStanzaVincente(vincente);
			this.aggiungiEAggiorna(vincente);
			return this;
		}

		public LabirintoBuilder addAdiacenze(String nomePartenza, String nomeAdiacente, Direzione direzione) {
			Stanza stanzaPartenza = this.nomeStanza.get(nomePartenza);
			if (stanzaPartenza.getDirezioni().size() >= 4) {
				return this;
			} else {
				Stanza stanzaAdiacente = this.nomeStanza.get(nomeAdiacente);
				stanzaPartenza.getDirezioni().add(direzione);
				stanzaPartenza.setNear(direzione, stanzaAdiacente);
				return this;
			}
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Attrezzo attrezzo = new Attrezzo(nome, peso);
			this.ultimaAggiunta.addAttrezzo(attrezzo);
			return this;
		}

		public LabirintoBuilder addStanza(String nome) {
			if (!this.nomeStanza.containsKey(nome)) {					// appena modificato
				Stanza stanza = new Stanza(nome);
				this.aggiungiEAggiorna(stanza);
			}
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, Direzione dirBloccata, String sbloccante) {
			Stanza stanzaBloccata = new StanzaBloccata(nome, dirBloccata, sbloccante);
			this.aggiungiEAggiorna(stanzaBloccata);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza stanzaMagica = new StanzaMagica(nome, soglia);
			this.aggiungiEAggiorna(stanzaMagica);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String nomeLuce) {
			Stanza stanzaBuia = new StanzaBuia(nome, nomeLuce);
			this.aggiungiEAggiorna(stanzaBuia);
			return this;
		}

		public LabirintoBuilder  addMago(String nome, String presentazione, Attrezzo attrezzo) {
			Mago m = new Mago(nome, presentazione, attrezzo);
			if(this.ultimaAggiunta==null)
				return this;
			this.ultimaAggiunta.setPersonaggio(m);
			return this;
		}

		public LabirintoBuilder  addCane(String nome, String presentazione, String ciboPreferito, Attrezzo regalo) {
			Cane c = new Cane(nome, presentazione, ciboPreferito, regalo);
			if(this.ultimaAggiunta==null)
				return this;
			this.ultimaAggiunta.setPersonaggio(c);
			return this;
		}

		public LabirintoBuilder  addStrega(String nome, String presentazione) {
			Strega s = new Strega(nome, presentazione);
			if(this.ultimaAggiunta==null)
				return this;
			this.ultimaAggiunta.setPersonaggio(s);
			return this;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		private void aggiungiEAggiorna(Stanza stanza) {
			this.ultimaAggiunta = stanza;
			this.nomeStanza.put(stanza.getNome(), stanza);
		}

		public Map<String, Stanza> getListaStanze() {
			return this.nomeStanza;
		}


		public Map<String, Stanza> getNome2stanza() {
			return nomeStanza;
		}
	}
}


