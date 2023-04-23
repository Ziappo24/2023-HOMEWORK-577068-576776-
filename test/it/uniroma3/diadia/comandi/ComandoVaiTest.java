package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	
	private Partita partita;
	private Comando comandoVai;
	private Stanza partenza;
	
	@BeforeEach
	void setUp() {
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIO(new IOConsole());
		this.partita = new Partita();
		this.partenza = new Stanza("partenza");
		this.partita.setStanzaCorrente(this.partenza);
		
		/* stanza esistente */
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.setNear("sud", destinazione);
	}

	@Test
	public void testVaiStanzaNonEsistente() {
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(this.partita);
		assertEquals("partenza", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaEsistente() {
		this.comandoVai.setParametro("sud");
		this.comandoVai.esegui(this.partita);
		assertEquals("destinazione", this.partita.getStanzaCorrente().getNome());
	}
	
	
}
