package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	// variabili che mi servono a verificare se una partita è vinta
	private Partita partita1; 	
	private Partita partita2;	
	private Partita partita3;	
	private Stanza corrente1;
	
	// variabili che mi servono a verificare se una partita è finita e basta
	private Partita partita4;
	private Partita partita5;
	private Partita partita6;
	private Partita partita7;
	private Partita partita8;
	private Partita partita9;
	private Partita partita10;
	private Partita partita11;
	
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() {
		
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("iniziale")
				.addStanzaVincente("vincente")
				.getLabirinto();
		
		/* setup per vedere se la partita è vinta */
		// imposto stanza corrente e vincente diverse
		this.partita1 = new Partita(labirinto);
		this.corrente1 = new Stanza("Corrente1");
		this.partita1.setStanzaCorrente(corrente1);
		
		// imposto stanza corrente e vincente uguali
		this.partita2 = new Partita(labirinto);
		this.partita2.setStanzaCorrente(this.partita2.getStanzaVincente());
		
		// una delle due è null
		this.partita3 = new Partita(labirinto);
		
		/* setup per vedere se la partita è finita e basta */
		
		// finita vale 1, vinta vale 0, CFU vale 0
		this.partita4 = new Partita(labirinto);
		this.partita4.setFinita();
		this.partita4.getGiocatore().setCfu(0);
		
		// finita vale 0, vinta vale 1, CFU vale 0
		this.partita5 = new Partita(labirinto);
		this.partita5.setStanzaCorrente(this.partita5.getStanzaVincente());
		this.partita5.getGiocatore().setCfu(0);
		
		// finita vale 0, vinta vale 0, CFU vale 1
		this.partita6 = new Partita(labirinto);
		
		// finita vale 1, vinta vale 1, CFU vale 0
		this.partita7 = new Partita(labirinto);
		this.partita7.setFinita();
		this.partita7.setStanzaCorrente(this.partita1.getStanzaVincente());
		this.partita7.getGiocatore().setCfu(0);
		
		// finita vale 1, vinta vale 0, CFU vale 1
		this.partita8 = new Partita(labirinto);
		this.partita8.setFinita();
		
		// finita vale 0, vinta vale 1, CFU vale 1
		this.partita9 = new Partita(labirinto);
		this.partita9.setStanzaCorrente(this.partita9.getStanzaVincente());
		
		// finita vale 1, vinta vale 1, CFU vale 1
		this.partita10 = new Partita(labirinto);
		this.partita10.setFinita();
		this.partita10.setStanzaCorrente(this.partita10.getStanzaVincente());
		
		// finita vale 0, vinta vale 0, CFU vale 0
		this.partita11 = new Partita(labirinto);
		this.partita11.getGiocatore().setCfu(0);
	}

	/* adesso scrivo tutte le casistiche di test che mi avviano veri e propri test per le istanze base che ho creato */
	
	@Test
	public void testPartita1CorrenteDiversaVincente() {
		assertEquals(false, this.partita1.vinta());
	}
	
	@Test
	public void testPartita2CorrenteUgualeVincente() {
		assertEquals(true, partita2.vinta());
	}
	
	@Test
	public void testPartita3UnaNull() {
		assertEquals(false, partita3.vinta());
	}
	
	@Test
	public void testPartita4finita1vinta0cfu0() {
		assertEquals(true, partita4.isFinita());
	}
	
	@Test
	public void testPartita5finita0vinta1cfu0() {
		assertEquals(true, partita5.isFinita());
	}
	
	@Test
	public void testPartita6finita0vinta0cfu1() {
		assertEquals(false, partita6.isFinita());
	}

	@Test
	public void testPartita7finita1vinta1cfu0() {
		assertEquals(true, partita7.isFinita());
	}
	
	@Test
	public void testPartita8finita1vinta0cfu1() {
		assertEquals(true, partita8.isFinita());
	}
	
	@Test
	public void testPartita9finita0vinta1cfu1() {
		assertEquals(true, partita9.isFinita());
	}
	
	@Test
	public void testPartita10finita1vinta1cfu1() {
		assertEquals(true, partita10.isFinita());
	}
	
	@Test
	public void testPartita11finita0vinta0cfu0() {
		assertEquals(true, partita11.isFinita());
	}
}
