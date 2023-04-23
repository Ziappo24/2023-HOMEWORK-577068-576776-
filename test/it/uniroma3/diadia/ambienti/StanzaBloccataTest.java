package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private static final String CHIAVE = "chiave";
	StanzaBloccata stanza;
	Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() {
		attrezzo = new Attrezzo(CHIAVE, 1);
		this.stanza = new StanzaBloccata("StanzaBloccata", null, CHIAVE);
	}

	@Test
	public void testChiusa() {
		assertFalse(this.stanza.isOpen());
	}
	
	@Test
	public void testAperta() {
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.isOpen());
	}

}
