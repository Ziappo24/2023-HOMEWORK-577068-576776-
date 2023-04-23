package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica factory;
	
	
	
	@BeforeEach
	void setUp() {
		this.factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testAiuto() {
		testNomeParametroComando("aiuto", "aiuto", null);
	}
	
	@Test
	public void testVai() {
		testNomeParametroComando("vai", "vai", "nord");
	}
	
	@Test
	public void testFine() {
		testNomeParametroComando("fine", "fine", null);
	}
	@Test
	public void testPrendi() {
		testNomeParametroComando("prendi", "prendi", "osso");
	}
	@Test
	public void testPosa() {
		testNomeParametroComando("posa", "posa", "osso");
	}
	@Test
	public void testComandoVuoto() {
		testNomeParametroComando("", "non_valido", null);
	}
	@Test
	public void testComandoNonValido() {
		testNomeParametroComando("marameo!", "non_valido", null);
	}
	@Test
	private void testNomeParametroComando(String nomeComando, String comandoAtteso, String parametro) {
		Comando comando = this.factory.costruisciComando(nomeComando, new IOConsole());
		if(parametro!=null)
			comando.setParametro(parametro);
		assertEquals(comandoAtteso, comando.getNome());
		assertEquals(parametro, comando.getParametro());
	}

}
