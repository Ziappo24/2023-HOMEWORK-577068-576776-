package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Map<String, Stanza> nomeStanza;
	private Stanza ultimaAggiunta;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.nomeStanza = new HashMap<String, Stanza>();		
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

	public LabirintoBuilder addAdiacenze(String nomePartenza, String nomeAdiacente, String direzione) {
		Stanza stanzaPartenza = this.nomeStanza.get(nomePartenza);
		if(stanzaPartenza.getDirezioni().size()>=4) 
			return this;
		else {
			Stanza stanzaAdiacente = this.nomeStanza.get(nomeAdiacente);
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

	public LabirintoBuilder addStanzaBloccata(String nome, String dirBloccata, String sbloccante) {
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

}
