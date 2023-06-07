package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai extends AbstractComando {
	

	private static final String NOME = "vai";
	
	public ComandoVai() {
		super.setNome(NOME);
	}
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza nextStanza = null;
		/* nessuna direzione */
		if(super.getParametro()==null)  {
			super.getIO().showMsg("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		Direzione direzione;
		try {
			direzione = Direzione.valueOf(super.getParametro().toUpperCase());
		} catch (IllegalArgumentException e) {
			//caso in cui viene specificata una direzione non contemplata dall'enum Direzione
			super.getIO().showMsg("Direzione insesistente");
			return;
		}
		nextStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if(nextStanza==null) {
			super.getIO().showMsg("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(nextStanza);
		super.getIO().showMsg(partita.getStanzaCorrente().getNome());
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(partita.getGiocatore().getCfu()-1);
	}
	
}
