package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class CaneTest {

	private Cane cane;
	private Attrezzo ciboPreferito;
	private Partita partita;
	private Attrezzo regalo;

	@BeforeEach
	void setUp() throws Exception {
		Labirinto labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		this.partita = new Partita(labirinto);
		this.regalo = new Attrezzo("regalo", 1);
		this.ciboPreferito = new Attrezzo("ScoobySnack", 2);
		this.cane = new Cane("Scooby", "Presentazione!", this.ciboPreferito.getNome(), this.regalo);
	}

	@Test
	public void testAgisci() {
		this.partita.getGiocatore().setCfu(10);
		this.cane.agisci(this.partita);
		assertEquals(9, this.partita.getGiocatore().getCfu());
	}

	@Test
	public void testRiceviCiboCorretto() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.cane.riceviRegalo(this.ciboPreferito, this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
	}
	
	@Test
	public void testRiceviCiboSbagliato() {
		this.partita.getGiocatore().setCfu(10);
		Attrezzo ciboSbagliato = new Attrezzo("CiboSbagliato", 2);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(ciboSbagliato.getNome()));
		this.cane.riceviRegalo(ciboSbagliato, this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(ciboSbagliato.getNome()));
		assertEquals(9, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testRiceviRegalo_DueVolte() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.cane.riceviRegalo(this.ciboPreferito, this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.partita.getStanzaCorrente().removeAttrezzo(this.regalo.getNome());
		this.cane.riceviRegalo(this.ciboPreferito, this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
	}
	

}
