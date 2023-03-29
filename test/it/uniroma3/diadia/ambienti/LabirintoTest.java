package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza atrio;
	private Stanza biblioteca;
	
	@BeforeEach
	void setUp() {
		this.labirinto=new Labirinto();
		atrio=this.labirinto.getStanzaCorrente();
		biblioteca=this.labirinto.getStanzaVincente();
	}

	@Test
	void testGetStanzaVincente() {
		assertEquals(biblioteca,this.labirinto.getStanzaVincente());
	}

	@Test
	void testGetStanzaCorrente() {
		assertEquals(atrio, this.labirinto.getStanzaCorrente());
	}

}
