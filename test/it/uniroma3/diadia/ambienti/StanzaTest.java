package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	/* creo delle variabili stanza per fare delle prove */
	private Stanza stanzaNord;
	private Stanza stanzaSud;
	private Stanza stanzaEst;
	private Stanza stanzaOvest;
	private Stanza emptyAdiacente;
	private Stanza stanzaPiena;

	/* creo delle variabili attrezzo per fare i test */
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzo4;
	private Attrezzo attrezzo5;
	private Attrezzo attrezzo6;
	private Attrezzo attrezzo7;
	private Attrezzo attrezzo8;
	private Attrezzo attrezzo9;
	private Attrezzo attrezzo10;
	private Attrezzo attrezzo11;
	private Attrezzo attrezzo12;


	private Stanza stanzaVuota;

	private static final Direzione DIREZIONE_NORD = Direzione.NORD;
	private static final Direzione DIREZIONE_SUD = Direzione.SUD;
	private static final Direzione DIREZIONE_EST = Direzione.EST;
	private static final Direzione DIREZIONE_OVEST = Direzione.OVEST;
	@BeforeEach
	void setUp() {
		this.stanzaNord = new Stanza("Nord");
		this.stanzaSud = new Stanza("Sud");
		this.stanzaEst = new Stanza("Est");
		this.stanzaOvest = new Stanza("Ovest");
		this.emptyAdiacente = new Stanza("Empty Adiacente");
		this.emptyAdiacente.setNear(DIREZIONE_NORD, stanzaNord);
		this.emptyAdiacente.setNear(DIREZIONE_SUD, stanzaSud);
		this.emptyAdiacente.setNear(DIREZIONE_EST, stanzaEst);
		this.emptyAdiacente.setNear(DIREZIONE_OVEST, stanzaOvest);
		this.stanzaPiena = new Stanza("stanzaPiena");

		/* inizializzo gli attrezzi */

		//creazione degli attrezzi
		this.attrezzo1 = new Attrezzo("Piccone", 4);
		this.attrezzo2 = new Attrezzo("Ascia", 3);
		this.attrezzo3 = new Attrezzo("Martello", 2);
		this.attrezzo4 = new Attrezzo("Corda", 1);

		//inizializzazione delle variabili per testare la presenza di attrezzi
		this.attrezzo5 = new Attrezzo("Chiave", 1);
		this.attrezzo6 = new Attrezzo("Libro", 5);
		this.attrezzo7 = new Attrezzo("Cacciavite", 2);
		this.attrezzo8 = new Attrezzo("Pala", 4);

		//inizializzazione delle variabili per testare la rimozione di attrezzi
		this.attrezzo9 = new Attrezzo("Faro", 2);
		this.attrezzo10 = new Attrezzo("Mappa", 1);
		this.attrezzo11 = new Attrezzo("Bussola", 3);
		this.attrezzo12 = new Attrezzo("Torcia", 1);

		this.stanzaPiena.addAttrezzo(attrezzo1);
		this.stanzaPiena.addAttrezzo(attrezzo2);
		this.stanzaPiena.addAttrezzo(attrezzo3);
		this.stanzaPiena.addAttrezzo(attrezzo4);
		this.stanzaPiena.addAttrezzo(attrezzo5);
		this.stanzaPiena.addAttrezzo(attrezzo6);
		this.stanzaPiena.addAttrezzo(attrezzo7);
		this.stanzaPiena.addAttrezzo(attrezzo8);
		this.stanzaPiena.addAttrezzo(attrezzo9);
		this.stanzaPiena.addAttrezzo(attrezzo10);
		
		this.stanzaNord.addAttrezzo(attrezzo2);
		this.stanzaNord.addAttrezzo(attrezzo4);
		this.stanzaNord.addAttrezzo(attrezzo6);
		this.stanzaNord.addAttrezzo(attrezzo12);
		
		this.stanzaSud.addAttrezzo(attrezzo7);
		
		this.stanzaVuota = new Stanza("stanzaVuota");
	}

	@Test
	void testAddAttrezzoStanzaVuota() {
		assertEquals(true, this.emptyAdiacente.addAttrezzo(attrezzo1));
	}
	
	@Test
	void testAddAttrezzoStanzaPiena() {
		assertEquals(false, this.stanzaPiena.addAttrezzo(attrezzo11));
	}
	
	@Test
	void testAddAttrezzoStanzaSemiPiena() {
		assertEquals(true, this.stanzaNord.addAttrezzo(attrezzo5));
	}

	@Test
	void testRemoveAttrezzoStanzaVuota() {
		assertEquals(false, this.stanzaVuota.removeAttrezzo("Piccone"));
	}
	
	@Test
	void testRemoveAttrezzoUnAttrezzo() {
		assertEquals(true, this.stanzaSud.removeAttrezzo("Cacciavite"));
	}
	
	@Test
	void testRemoveAttrezzoPiuDiUno() {
		assertEquals(true, this.stanzaNord.removeAttrezzo("Torcia"));
	}
	
	@Test
	void testRemoveAttrezzoStanzaPiena() {
		assertEquals(true, this.stanzaPiena.removeAttrezzo("Ascia"));
	}
	
	@Test
	void testHasAttrezzoPresente() {
		assertEquals(true, this.stanzaNord.hasAttrezzo("Corda"));
	}
	
	@Test
	void testHasAttrezzoAssente() {
		assertEquals(false, this.stanzaNord.hasAttrezzo("Martello"));
	}
	
	@Test
	void testHasAttrezzoNoAttrezzi() {
		assertEquals(false, this.stanzaVuota.hasAttrezzo("Corda"));
	}

}
