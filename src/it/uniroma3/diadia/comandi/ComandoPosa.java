package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPosa extends AbstractComando{
	private static final String NOME = "posa";
	
	public ComandoPosa() {
		super.setNome(NOME);
	}
	
	@Override
	public void esegui(Partita partita) {
		// il giocatore possiede l'attrezzo nella borsa? 
				if(partita.getGiocatore().getBorsa().hasAttrezzo(super.getParametro())) {
					Attrezzo layItem = partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro());
					// l'ho posato nella stanza? 
					if(partita.getStanzaCorrente().addAttrezzo(layItem)) {
						// allora lo cancello dalla borsa 
						if(partita.getGiocatore().getBorsa().removeAttrezzo(super.getParametro())!=null) 	// siccome removeAttrezzo di borsa mi ritorna un oggetto di tipo attrezzo
							super.getIO().showMsg(super.getParametro() + " e' stato spostato nella stanza corrente");	// devo almeno verificare che non sia null, perché non mi ritorna un booleano...
						return;
					}
					else
						super.getIO().showMsg("Spiacente la stanza e' piena");
				}
				else
					super.getIO().showMsg("Oggetto non presente nello zaino");

				return;
	}

}
