package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_2 = "Il cane non accetta il tuo regalo";
	private static final String MESSAGGIO = "Il cane ha accettato il tuo regalo e ti lascia un attrezzo";
	private String ciboPreferito;
	private Attrezzo attrezzoRegalo;
	private boolean posato = false;
	
	private static final String MESSAGGIO_CANE = "Sei stato morso dal cane!";

	public Cane(String nome, String presentaz, String ciboPreferito, Attrezzo regalo) {
		super(nome, presentaz);
		this.ciboPreferito = ciboPreferito;
		this.attrezzoRegalo = regalo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_CANE;
	}

	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {
		if(regalo.getNome().equals(ciboPreferito)) {
			if(!this.posato) {
				partita.getStanzaCorrente().addAttrezzo(this.attrezzoRegalo);
				this.posato = true;
			}
			return MESSAGGIO;
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(regalo);
			this.agisci(partita);
			return MESSAGGIO_2;
		}
	}

}
