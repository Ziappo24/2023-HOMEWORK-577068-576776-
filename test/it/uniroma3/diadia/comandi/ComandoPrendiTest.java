package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPrendiTest {
	
	private static final String ARMA_NELLA_STANZA = "arma";
	private ComandoPrendi comandoPrendi;
	private Partita partita;
	
	@BeforeEach
	void setUp() {
		this.comandoPrendi = new ComandoPrendi();
		this.comandoPrendi.setIO(new IOConsole());
		this.partita = new Partita();
		Attrezzo nuovoAttrezzo = new Attrezzo(ARMA_NELLA_STANZA, 1);
		this.partita.getStanzaCorrente().addAttrezzo(nuovoAttrezzo);
	}

	@Test
	public void testEseguiAttrezzoPresente() {
		this.comandoPrendi.setParametro(ARMA_NELLA_STANZA);
		this.comandoPrendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(ARMA_NELLA_STANZA));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(ARMA_NELLA_STANZA));
	}
	
	@Test
	public void testEseguiAttrezzoNonPresente() {
		String nonPresente = "calamaio";
		this.comandoPrendi.setParametro(nonPresente);
		this.comandoPrendi.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("calamaio"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("calamaio"));
	}
	
	@Test
	public void testEseguiBorsaPiena() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		for (int i = 0; i < 10; i++) {
			borsa.addAttrezzo(new Attrezzo("attrezzo"+i, 1));
		}
		this.comandoPrendi.setParametro(ARMA_NELLA_STANZA);
		this.comandoPrendi.esegui(partita);
		assertFalse(borsa.hasAttrezzo(ARMA_NELLA_STANZA));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(ARMA_NELLA_STANZA));
	}
}
