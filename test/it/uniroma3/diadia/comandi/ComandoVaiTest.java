package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	
	private static final String NOME_STANZA_PARTENZA = "partenza";
	private Partita partita;
	private AbstractComando comandoVai;
	private Stanza partenza;
	private Labirinto labirinto;
	private static final Direzione DIREZIONE_SUD = Direzione.SUD;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIO(new IOConsole());
		this.labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		this.partita = new Partita(labirinto);
		this.partenza = new Stanza(NOME_STANZA_PARTENZA);
		this.partita.setStanzaCorrente(this.partenza);
		
		/* stanza esistente */
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.setNear(DIREZIONE_SUD, destinazione);
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
		this.partenza.setNear(DIREZIONE_SUD, destinazione);
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals(NOME_STANZA_PARTENZA, this.partita.getStanzaCorrente().getNome());
	}
}
