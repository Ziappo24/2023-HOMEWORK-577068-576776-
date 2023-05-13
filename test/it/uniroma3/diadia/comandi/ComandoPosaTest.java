package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {
	
	private static final String ARMA_DA_POSARE = "arma";
	private ComandoPosa comandoPosa;
	private Partita partita;
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() {
		this.comandoPosa = new ComandoPosa();
		this.comandoPosa.setIO(new IOConsole());
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("iniziale")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo nuovoAttrezzo = new Attrezzo(ARMA_DA_POSARE, 1);
		borsa.addAttrezzo(nuovoAttrezzo);
	}

	@Test
	public void testEseguiPresente() {
		this.comandoPosa.setParametro(ARMA_DA_POSARE);
		this.comandoPosa.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(ARMA_DA_POSARE));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(ARMA_DA_POSARE));
	}
	
	@Test
	public void testEseguiNonPresente() {
		this.comandoPosa.setParametro("candela");
		this.comandoPosa.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("candela"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("candela"));
	}
	
	@Test
	public void testEseguiStanzaPiena() {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		for (int i = 0; i < 10; i++) {
			stanzaCorrente.addAttrezzo(new Attrezzo("attrezzo"+i, 1));
		}
		this.comandoPosa.setParametro(ARMA_DA_POSARE);
		this.comandoPosa.esegui(partita);
		assertFalse(stanzaCorrente.hasAttrezzo(ARMA_DA_POSARE));
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(ARMA_DA_POSARE));
	}
}
