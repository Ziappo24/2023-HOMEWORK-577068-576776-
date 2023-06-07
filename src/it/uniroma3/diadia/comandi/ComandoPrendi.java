package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {

	private static final String NOME = "prendi";

	public ComandoPrendi() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		// se in questa partita nella stanza corrente c'è questo attrezzo 
		if(partita.getStanzaCorrente().hasAttrezzo(super.getParametro())) {
			Attrezzo takenItem = partita.getStanzaCorrente().getAttrezzo(super.getParametro());
			// prendi l'attrezzo 
			if(partita.getGiocatore().getBorsa().addAttrezzo(takenItem)) {
				if(partita.getStanzaCorrente().removeAttrezzo(super.getParametro())) {
					super.getIO().showMsg("L'attrezzo "+ super.getParametro() +" e' stato preso e messo nello zaino");
					return;
				}
				else
					super.getIO().showMsg("Spiacente ma la borsa e' piena");
			}
			else
				super.getIO().showMsg("La borsa cosi non regge, vai oltre il limite di peso");
		}
		else
			super.getIO().showMsg("Attrezzo " + super.getParametro() + " non presente nella stanza");
		return;
	}


}
