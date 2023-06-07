package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{

	private static final String MESSAGGIO = "Chi dovrei salutare?";
	private static final String NOME = "saluta";

	public ComandoSaluta() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio()==null)
			super.getIO().showMsg(MESSAGGIO);
		else
			super.getIO().showMsg(partita.getStanzaCorrente().getPersonaggio().saluta());
	}

}
