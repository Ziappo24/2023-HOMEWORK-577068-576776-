package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class GiocatoreTest {

	private Giocatore giocatore;
	private Attrezzo attrezzoBorsa;
	String risposta = "Borsa vuota";
	

	@BeforeEach
	void setUp() {
		
		this.giocatore = new Giocatore();
		
		this.attrezzoBorsa = new Attrezzo("occhiali", 1);
		this.giocatore.getBorsa().addAttrezzo(attrezzoBorsa);
	}

	@Test
	void testSetCfu() {
		this.giocatore.setCfu(10);
		assertEquals(10, this.giocatore.getCfu());
	}
	
	@Test
	void testGetCfu() {
		assertEquals(20, this.giocatore.getCfu());
	}
	
	@Test
	void testGetBorsaNonVuota() {
		assertNotNull(this.giocatore.getBorsa());
	}

}
