package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPosa implements Comando{
	private IO io;
	private String nomeAttrezzo;
	private static final String NOME = "posa";
	
	@Override
	public void esegui(Partita partita) {
		// il giocatore possiede l'attrezzo nella borsa? 
				if(partita.getGiocatore().getBorsa().hasAttrezzo(this.nomeAttrezzo)) {
					Attrezzo layItem = partita.getGiocatore().getBorsa().getAttrezzo(this.nomeAttrezzo);
					// l'ho posato nella stanza? 
					if(partita.getStanzaCorrente().addAttrezzo(layItem)) {
						// allora lo cancello dalla borsa 
						if(partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeAttrezzo)!=null) 	// siccome removeAttrezzo di borsa mi ritorna un oggetto di tipo attrezzo
							this.io.showMsg(this.nomeAttrezzo + " e' stato spostato nella stanza corrente");	// devo almeno verificare che non sia null, perché non mi ritorna un booleano...
						return;
					}
					else
						this.io.showMsg("Spiacente la stanza e' piena");
				}
				else
					this.io.showMsg("Oggetto non presente nello zaino");

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
