package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private IO io;
	private String nomeAttrezzo;
	private static final String NOME = "prendi";


	@Override
	public void esegui(Partita partita) {
		// se in questa partita nella stanza corrente c'è questo attrezzo 
		if(partita.getStanzaCorrente().hasAttrezzo(this.nomeAttrezzo)) {
			Attrezzo takenItem = partita.getStanzaCorrente().getAttrezzo(this.nomeAttrezzo);
			// prendi l'attrezzo 
			if(partita.getGiocatore().getBorsa().addAttrezzo(takenItem)) {
				if(partita.getStanzaCorrente().removeAttrezzo(takenItem)) {
					this.io.showMsg("L'attrezzo "+ this.nomeAttrezzo +" e' stato preso e messo nello zaino");
					return;
				}
				else
					this.io.showMsg("Spiacente ma la borsa e' piena");
			}
			else
				this.io.showMsg("La borsa cosi non regge, vai oltre il limite di peso");
		}
		else
			this.io.showMsg("Attrezzo " + this.nomeAttrezzo + " non presente nella stanza");
		return;
	}


	@Override
	public void setParametro(String parametro) { 
		this.nomeAttrezzo = parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
}
