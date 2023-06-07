package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	private static final Direzione OVEST = Direzione.OVEST;

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;
	
	public DiaDia(IO io, Labirinto labirinto) {
		this.partita = new Partita(labirinto);
		this.io = io;
	}

	public void gioca() {

		String istruzione;
		this.io.showMsg(MESSAGGIO_BENVENUTO);		
		do	{
			istruzione = this.io.readLine();
		}
		while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		AbstractComando daEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		daEseguire = factory.costruisciComando(istruzione,this.io);
		daEseguire.esegui(this.partita);
		if (this.partita.vinta()) 
			this.io.showMsg("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			this.io.showMsg("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}   

	public static void main(String[] argc) throws Exception {
		Scanner scanner = new Scanner(System.in);
		IO ioConsole = new IOConsole();
		Labirinto labirinto = Labirinto.newBuilder("labirinto5.txt")
				.addStanzaIniziale("LabCampusOne")
				.addStanzaVincente("Biblioteca")
				.addAdiacenze("LabCampusOne","Biblioteca",OVEST)
				.getLabirinto();
		DiaDia gioco = new DiaDia(ioConsole, labirinto);
		gioco.gioca();
		scanner.close();
	}
}