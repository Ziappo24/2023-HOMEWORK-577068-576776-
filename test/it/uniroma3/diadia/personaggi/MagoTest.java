package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class MagoTest {
	
	private Partita partita;
	private Attrezzo regalo;
	private Mago mago;
	
	@BeforeEach
	void setUp() throws Exception {
		Labirinto labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		this.partita = new Partita(labirinto);
		this.regalo = new Attrezzo("regalo", 1);
		this.mago = new Mago("Franco", "Sono Franco il mago stanco", regalo);
	}
	
	@Test
	public void testAgisci() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.mago.agisci(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
	}

	@Test
	public void testRiceviRegalo() {
		Attrezzo dimezzato = new Attrezzo("dimezzato", 4);
		this.mago.riceviRegalo(dimezzato, this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(dimezzato.getNome()));
		assertEquals(2,this.partita.getStanzaCorrente().getAttrezzo(dimezzato.getNome()).getPeso());
	}
	
	@Test
	public void testAgisciDueVolte() {
		assertNotEquals(this.partita.toString(), this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.mago.agisci(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.partita.getStanzaCorrente().removeAttrezzo(this.regalo.getNome());
		this.mago.agisci(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
	}
}
