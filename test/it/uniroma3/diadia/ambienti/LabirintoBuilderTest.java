package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {
	private LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		labirintoBuilder = new LabirintoBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIngresso().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIngresso().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIngresso().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();
		int size = monolocale.getStanzaIngresso().getAttrezzi().size();
		assertTrue(size==1);
		List<Attrezzo> attrezziInStanzaIngresso = new ArrayList<>(monolocale.getStanzaIngresso().getAttrezzi().values());
		assertEquals(Arrays.asList(new Attrezzo("spada", 1)).toString(), attrezziInStanzaIngresso.toString());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenze(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenze(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIngresso().getStanzaAdiacente("nord"));
		assertEquals(Collections.singletonList("nord").toString(),bilocale.getStanzaIngresso().getDirezioni().toString());
		assertEquals(Collections.singletonList("sud").toString(),bilocale.getStanzaVincente().getDirezioni().toString());
	}
	
	@Test
	public void testTrilocale(){
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenze(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenze("biblioteca", nomeStanzaIniziale, "nord")
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenze("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenze(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIngresso().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaIngresso().getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenze(nomeStanzaIniziale, "stanza generica", "nord")
				.getLabirinto();
		assertTrue(labirintoBuilder.getListaStanze().size()<=2);
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza 1")
				.addStanza("stanza 2")
				.addStanza("stanza 3")
				.addStanza("stanza 4")
				.addStanza("stanza 5")
				.addAdiacenze(nomeStanzaIniziale, "stanza 1", "nord")
				.addAdiacenze(nomeStanzaIniziale, "stanza 2", "ovest")
				.addAdiacenze(nomeStanzaIniziale, "stanza 3", "sud")
				.addAdiacenze(nomeStanzaIniziale, "stanza 4", "est")
				.addAdiacenze(nomeStanzaIniziale, "stanza 5", "nord-est") // non dovrebbe essere aggiunta
				.getLabirinto();
				Stanza test = new Stanza("stanza 5");
		assertNull(maze.getStanzaIngresso().getStanzaAdiacente("nord-est"));
		assertTrue(maze.getStanzaIngresso().getMapStanzeAdiacenti().size()<=4);
		assertTrue(!maze.getStanzaIngresso().getMapStanzeAdiacenti().containsValue(test));
		Map<String, Stanza> mappa = new HashMap<>();
		mappa.putAll(Map.of(
		    "nord", new Stanza("stanza 1"),
		    "ovest", new Stanza("stanza 2"),
		    "sud", new Stanza("stanza 3"),
		    "est", new Stanza("stanza 4")
		));

		assertEquals(mappa.keySet().toString(), maze.getStanzaIngresso().getMapStanzeAdiacenti().keySet().toString());
		assertEquals(mappa.values().toString(), maze.getStanzaIngresso().getMapStanzeAdiacenti().values().toString());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getStanzaIngresso().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo.toString(), maze.getStanzaIngresso().getAttrezzo(nomeAttrezzo).toString());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
	    String nomeAttrezzo = "attrezzo";
	    int peso = 1;
	    String nomeStanza = "stanza 1";
	    labirintoBuilder
	            .addStanzaIniziale(nomeStanzaIniziale)
	            .addStanza(nomeStanza)
	            .addAttrezzo(nomeAttrezzo, peso)
	            .getLabirinto();
	    assertTrue(labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi().containsKey(nomeAttrezzo));
	    assertEquals(new Attrezzo(nomeAttrezzo, peso).toString(), this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo).toString());
	}

	
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		Map<String, Attrezzo> attrezzi = labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi();
		assertEquals(attrezzo.toString(),attrezzi.get(attrezzo.getNome()).toString());
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2).toString(),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2).toString());
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1).toString(),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1).toString());
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1).toString(),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1).toString());
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2).toString(),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2).toString());
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1).toString(),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1).toString());
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2).toString(),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2).toString());
	}
	
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica)labirintoBuilder.getListaStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2).toString(), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv).toString());
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1).toString(), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1).toString());
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave").addAttrezzo("chiave", 1)
		.addAdiacenze(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenze("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenze("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenze(nomeStanzaVincente, "stanza bloccata", "sud");
		StanzaBloccata stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		stanzaVincente.setNear("sud", stanzaBloccata);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente.toString(),labirintoBuilder.getListaStanze().get(stanzaVincente.getNome()).toString());	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave")
		.addAdiacenze(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenze("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenze("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenze(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		Stanza stanzaIniziale = new Stanza(nomeStanzaIniziale);
		stanzaBloccata.setNear("nord", stanzaVincente);
		stanzaBloccata.setNear("sud", stanzaIniziale);
		
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata.toString() ,labirintoBuilder.getListaStanze().get(stanzaBloccata.getNome()).toString());
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		
		Labirinto labirintoCompleto = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato","nord","chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenze(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenze("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenze("corridoio", "corridoio bloccato", "nord")
				.addAdiacenze("corridoio bloccato", "corridoio", "sud")
				.addAdiacenze("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenze("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenze("Aula 1", nomeStanzaVincente,"nord")
				.addAdiacenze(nomeStanzaVincente, "Aula 1", "sud")
				.addAdiacenze("corridoio", "stanza magica", "est")
				.addAdiacenze("stanza magica", "corridoio", "ovest")
				.addAdiacenze("corridoio", "stanza buia", "ovest")
				.addAdiacenze("stanza buia", "corridoio", "est")
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaIngresso().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza stanzaIniziale = labirintoCompleto.getStanzaIngresso();
		Stanza corridoio = labirintoCompleto.getStanzaIngresso().getStanzaAdiacente("nord");
		Stanza corridoioBloccato = labirintoCompleto.getStanzaIngresso().getStanzaAdiacente("nord").getStanzaAdiacente("nord");
		Stanza stanzaMagica = labirintoCompleto.getStanzaIngresso().getStanzaAdiacente("nord").getStanzaAdiacente("est");
		Stanza stanzaBuia = labirintoCompleto.getStanzaIngresso().getStanzaAdiacente("nord").getStanzaAdiacente("ovest");
		/*Stanza aula1 = labirintoCompleto.getStanzaIngresso().getStanzaAdiacente("nord").getStanzaAdiacente("nord").getStanzaAdiacente("nord");
		corridoioBloccato.setNear("nord", aula1);
		corridoioBloccato.setNear("sud", corridoio);*/
		assertEquals("corridoio",corridoio.getNome());
		assertTrue(corridoio.getMapStanzeAdiacenti().containsKey("ovest"));
		assertTrue(corridoio.getMapStanzeAdiacenti().containsKey("nord"));
		assertTrue(corridoio.getMapStanzeAdiacenti().containsKey("sud"));
		assertTrue(corridoio.getMapStanzeAdiacenti().containsKey("est"));
		Map<String, Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.putAll(Map.of(
		    "nord", corridoioBloccato,
		    "sud", stanzaIniziale,
		    "est", stanzaMagica,
		    "ovest", stanzaBuia
		));
		assertEquals(mapAdiacenti.toString(),corridoio.getMapStanzeAdiacenti().toString());
		Attrezzo a1 = labirintoCompleto.getStanzaIngresso().getStanzaAdiacente("nord").getAttrezzo("chiave");
		Attrezzo a2 = labirintoCompleto.getStanzaIngresso().getStanzaAdiacente("nord").getAttrezzo("lanterna");
		assertEquals(a1.toString(), corridoio.getAttrezzo("chiave").toString());
		assertEquals(a2.toString(), corridoio.getAttrezzo("lanterna").toString());
	}
}
