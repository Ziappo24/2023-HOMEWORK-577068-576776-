package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
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

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai + <direzione>", "aiuto", "fine", "info", "prendi + <nomeAttrezzo>", "posa + <nomeAttrezzo"};

	private Partita partita;
	public static IOConsole iO;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {

		String[] istruzione;
		int indice = 0;
		iO.showMsg(MESSAGGIO_BENVENUTO);
		iO.showMsg("");		
		do	{
			istruzione = null;
			istruzione = iO.readLine().split(" ");
			indice = istruzione.length;
		}
		while (!processaIstruzione(istruzione, indice));

	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String[] istruzione, int indice) {

		Comando daEseguire = new Comando(istruzione, indice);

		if(daEseguire.getNome()==null) {
			iO.showMsg("Devi scrivere qualcosa!");
			return false;
		}
		if (daEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} 
		else if (daEseguire.getNome().equals("vai"))
			this.vai(daEseguire.getParametro());

		else if (daEseguire.getNome().equals("aiuto"))
			this.aiuto();

		else if (daEseguire.getNome().equals("info"))
			this.info();

		else if (daEseguire.getNome().equals("prendi"))
			this.prendi(daEseguire.getParametro());

		else if (daEseguire.getNome().equals("posa"))
			this.posa(daEseguire.getParametro());
		
		else if((this.partita.getGiocatore().getCfu())==0) {
			iO.showMsg("Caro utente hai terminato i CFU");
			this.fine();
		}
		else
			iO.showMsg("Comando sconosciuto");

		if (this.partita.vinta()) {
			iO.showMsg("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Mi da informazioni sulla stanza corrente e su cosa honella borsa
	 * 
	 */
	private void info() {
		iO.showMsg(this.partita.getStanzaCorrente().getDescrizione());
		iO.showMsg(this.partita.getGiocatore().getBorsa().toString());
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			iO.showMsg(elencoComandi[i]+" ");
		iO.showMsg(" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione!=null) {
			Stanza nextStanza = null;
			nextStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (nextStanza == null)
				iO.showMsg("Direzione inesistente");
			else {
				this.partita.setStanzaCorrente(nextStanza);
				int cfu = this.partita.getGiocatore().getCfu();
				this.partita.getGiocatore().setCfu(--cfu);
			}
		}
		else
			iO.showMsg("Dove desideri andare");
		
		iO.showMsg(partita.getStanzaCorrente().getDescrizione()+" "+"CFU:"+partita.getGiocatore().getCfu()+"\n"+partita.getGiocatore().getBorsa().toString());
	}

	/**
	 * prende un attrezzo dalla stanza, lo copia nella borsa e poi lo rimuove dalla stanza
	 * @author ZIAPPO
	 * @param nomeAttrezzo
	 * @return true se va tutto a buon fine, false altrimenti con uno specifico messaggio di errore
	 */
	public boolean prendi(String nomeAttrezzo) {
		/* se in questa partita nella stanza corrente c'è questo attrezzo */
		if(nomeAttrezzo!=null && this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo takenItem = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			/* prendi l'attrezzo */
			if(this.partita.getGiocatore().getBorsa().addAttrezzo(takenItem)) {
				if(this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo)) {
					iO.showMsg("L'attrezzo "+ nomeAttrezzo +" e' stato preso e messo nello zaino");
					return true;
				}
				else
					iO.showMsg("Spiacente ma la borsa e' piena");
			}
			else
				iO.showMsg("La borsa cosi non regge, vai oltre il limite di peso");
		}
		else
			iO.showMsg("Oggetto non presente");
		return false;
	}

	/**
	 * Prende un attrezzo dalla borsa, lo copia nella stanza e lo rimuove dalla borsa
	 * @param attrezzo
	 * @return true se l'operazione va a buon fine, false + messaggio di errore altrimenti
	 */
	public boolean posa(String nomeAttrezzo) {
		/* il giocatore possiede l'attrezzo nella borsa? */
		if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo layItem = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			/* l'ho posato nella stanza? */
			if(this.partita.getStanzaCorrente().addAttrezzo(layItem)) {
				/* allora lo cancello dalla borsa */
				if(this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo)!=null) 	// siccome removeAttrezzo di borsa mi ritorna un oggetto di tipo attrezzo
					iO.showMsg(nomeAttrezzo + " e' stato spostato nella stanza corrente");	// devo almeno verificare che non sia null, perché non mi ritorna un booleano...
				return true;
			}
			else
				iO.showMsg("Spiacente la stanza e' piena");
		}
		else
			iO.showMsg("Oggetto non presente nello zaino");

		return false;
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		iO.showMsg("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		iO = new IOConsole();
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}