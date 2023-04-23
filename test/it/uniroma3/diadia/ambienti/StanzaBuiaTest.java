package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private StanzaBuia stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() {
		attrezzo = new Attrezzo("lanterna", 1);
		this.stanza = new StanzaBuia("stanza", "lanterna");
	}

	@Test
	public void testBuia() {
		assertEquals("Qui c'è un buio pesto", this.stanza.getDescrizione());
		System.out.println(this.stanza.getDescrizione());
	}
	
	@Test
	public void testLuce() {
		stanza.addAttrezzo(attrezzo);
		assertEquals("stanza" + "\nUscite: " + "\nAttrezzi nella stanza: " + attrezzo.toString() + " ", this.stanza.getDescrizione());
		System.out.println(this.stanza.getDescrizione());
	}

}
