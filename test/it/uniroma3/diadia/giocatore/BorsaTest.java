package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	
	
	private Borsa borsaPiena;
	private Borsa borsaVuota;
	private Borsa borsaSemiVuota;
	
	@BeforeEach
	void setUp() {
		
		/* crea le borse */
		this.borsaPiena = new Borsa();
		this.borsaVuota = new Borsa();
		this.borsaSemiVuota = new Borsa();
		
		
		/* crea gli attrezzi */
		this.attrezzo1 = new Attrezzo("Piccone", 4);
		this.attrezzo2 = new Attrezzo("Ascia", 10);
		this.attrezzo3 = new Attrezzo("Foglio", 1);
		

		
		/* aggiungi gli attrezzi alle borse */
		this.borsaPiena.addAttrezzo(attrezzo2);
		
		
		this.borsaSemiVuota.addAttrezzo(attrezzo1);
		this.borsaSemiVuota.addAttrezzo(attrezzo3);
		
		
	}

	@Test
	void testAddAttrezzoBorsaVuota() {
		assertEquals(true, this.borsaVuota.addAttrezzo(attrezzo1));
	}
	
	@Test
	void testAddAttrezzoBorsaPiena() {
		assertEquals(false, this.borsaPiena.addAttrezzo(attrezzo1));
	}
	
	@Test
	void testAddAttrezzoBorsaSemiPiena() {
		assertEquals(true, this.borsaSemiVuota.addAttrezzo(attrezzo1));
	}


	@Test
	void testHasAttrezzoPresente() {
		assertEquals(true, this.borsaSemiVuota.hasAttrezzo("Piccone"));
	}
	
	@Test
	void testHasAttrezzoAssente() {
		assertEquals(false, this.borsaSemiVuota.hasAttrezzo("Ascia"));
	}
	
	@Test
	void testHasAttrezzoNoAttrezzi() {
		assertEquals(false, this.borsaVuota.hasAttrezzo("Ascia"));
	}

	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertNull(this.borsaVuota.removeAttrezzo("Piccone"));
	}
	
	@Test
	void testRemoveAttrezzoUnAttrezzo() {
		assertNotNull(this.borsaPiena.removeAttrezzo("Ascia"));
	}
	
	@Test
	void testRemoveAttrezzoPiuDiUno() {
		assertNotNull(this.borsaSemiVuota.removeAttrezzo("Foglio"));
	}
	
	@Test
	void testRemoveAttrezzoBorsaPiena() {
		assertNotNull(this.borsaPiena.removeAttrezzo("Chiave"));
	}

}
