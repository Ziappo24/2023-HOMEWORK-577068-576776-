package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StregaTest {
	
	private static final String STANZA_OVEST_1_ATTREZZO = "stanzaOvest1attrezzo";
	@SuppressWarnings("unused")
	private static final String INIZIALE = "iniziale";
	private static final String STANZA_EST_2_ATTREZZI = "stanzaEst2Attrezzi";
	private Strega strega;
	private Partita partita;
	private Attrezzo regalo;
	
	
	@BeforeEach
	void setUp() throws Exception {
		Labirinto labirinto = Labirinto.newBuilder("labirinto6.txt").getLabirinto();
	//					.addStanzaIniziale(INIZIALE)
	//					.addStanza(STANZA_EST_2_ATTREZZI)
	//					.addAttrezzo("attrezzo1", 1)
	//					.addAttrezzo("attrezzo2", 2)
	//					.addAdiacenze(INIZIALE, STANZA_EST_2_ATTREZZI, Direzione.EST)
	//					.addStanza(STANZA_OVEST_1_ATTREZZO)
	//					.addAttrezzo("attrezzo3", 3)
	//					.addAdiacenze(INIZIALE, STANZA_OVEST_1_ATTREZZO, Direzione.OVEST)
						
		this.partita = new Partita(labirinto);
		this.strega = new Strega("GabriellaCincotti", "ICSPALLOCCO");
		this.regalo = new Attrezzo("regalo", 1);
	}
	
	@Test
	public void testAgisciNoSaluto() {
		this.strega.agisci(this.partita);
		assertEquals(STANZA_OVEST_1_ATTREZZO, this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testAgisciSiSaluto() {
		this.strega.saluta();
		this.strega.agisci(this.partita);
		assertEquals(STANZA_EST_2_ATTREZZI, this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void riceviRegalo() {
		this.strega.riceviRegalo(this.regalo, this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(this.regalo.getNome()));
	}
}







