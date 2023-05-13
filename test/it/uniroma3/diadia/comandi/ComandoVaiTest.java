package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	
	private static final String NOME_STANZA_PARTENZA = "partenza";
	private Partita partita;
	private Comando comandoVai;
	private Stanza partenza;
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() {
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIO(new IOConsole());
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale(NOME_STANZA_PARTENZA)
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.partenza = new Stanza(NOME_STANZA_PARTENZA);
		this.partita.setStanzaCorrente(this.partenza);
		
		/* stanza esistente */
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.setNear("sud", destinazione);
	}

	@Test
	public void testVaiStanzaNonEsistente() {
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(this.partita);
		assertEquals(NOME_STANZA_PARTENZA, this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaEsistente() {
		this.comandoVai.setParametro("sud");
		this.comandoVai.esegui(this.partita);
		assertEquals("destinazione", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresenteInDirezioneSbagliata() {
		Stanza destinazione = new Stanza("Destinazione");
		this.partenza.setNear("sud", destinazione);
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals(NOME_STANZA_PARTENZA, this.partita.getStanzaCorrente().getNome());
	}
	

	
}
