package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza atrio;
	private Stanza biblioteca;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		atrio=this.labirinto.getStanzaIngresso();
		biblioteca=this.labirinto.getStanzaVincente();
	}

	@Test
	void testGetStanzaVincente() {
		assertEquals(biblioteca,this.labirinto.getStanzaVincente());
	}

	@Test
	void testGetStanzaCorrente() {
		assertEquals(atrio, this.labirinto.getStanzaIngresso());
	}

}
