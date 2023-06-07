package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	static final private String[] ELENCO_COMANDI = {"vai", "aiuto", "fine", "prendi", "posa", "guarda",
			"interagisci", "saluta"};
	private final static String NOME = "aiuto";

	public ComandoAiuto(String[] elencoComandi) {
		super.setNome(NOME);;
	}

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i<ELENCO_COMANDI.length; i++) 
			super.getIO().showMsg(ELENCO_COMANDI[i]+" ");
		super.getIO().showMsg(" ");
	}
}
