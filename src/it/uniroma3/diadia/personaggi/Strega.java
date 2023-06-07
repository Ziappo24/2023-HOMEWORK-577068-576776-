package it.uniroma3.diadia.personaggi;

import java.util.TreeSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreNumAttrezzi;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_RISATA = "IHIHIHIHIH questo me lo tengo marameo!";
	private static final String SI_SALUTO = "Arrivederci anche a lei!";
	private static final String NO_SALUTO = "Come osi non salutare! Abracadabra!";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
		
	}

	@Override
	public String agisci(Partita partita) {
		TreeSet<Stanza> stanze2NumAttrezzi = new TreeSet<Stanza>(new ComparatoreNumAttrezzi());
		for(Direzione direzione : partita.getStanzaCorrente().getDirezioni()) {
			stanze2NumAttrezzi.add(partita.getStanzaCorrente().getStanzaAdiacente(direzione));
		}
		if(!super.haSalutato()) {
			partita.setStanzaCorrente(stanze2NumAttrezzi.last());
			return NO_SALUTO;
		}
		else {
			partita.setStanzaCorrente(stanze2NumAttrezzi.first());
			return SI_SALUTO;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {
		return MESSAGGIO_RISATA;
	}
}
