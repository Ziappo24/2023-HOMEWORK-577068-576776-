package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private Set<Direzione> direzioni;
	private AbstractPersonaggio personaggio;


	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<Direzione, Stanza>(NUMERO_MASSIMO_DIREZIONI);
		this.attrezzi = new HashMap<String, Attrezzo>(NUMERO_MASSIMO_ATTREZZI);
		this.direzioni = new TreeSet<>();
	}



	/* nuova implementazione con funzione di HashMap */
	public void setNear(Direzione direzione, Stanza stanza) {
		if(this.stanzeAdiacenti.size()>=NUMERO_MASSIMO_DIREZIONI)
				return;
		this.stanzeAdiacenti.put(direzione, stanza);
	}



	/* nuova implementazione con funzione di HashMap */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}


	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.toString();
	}


	public Map<String,Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo!=null && this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) 
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
		if (attrezzo!=null && this.attrezzi.get(attrezzo.getNome())!=null)
			return true;
		else 
			return false;
	}


	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.stanzeAdiacenti.keySet());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(attrezzi.values().toString()+" ");

		return risultato.toString();
	}

    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.containsKey(nomeAttrezzo);
    }



	/* nuova implementazione */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);	
	}


	/* nuova implementazione */
    public boolean removeAttrezzo(String nomeAttrezzo) {
        Attrezzo itemRemove = this.attrezzi.remove(nomeAttrezzo);
        return itemRemove != null;
    }


	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}



    public Set<Direzione> getDirezioni() {
        return this.direzioni;
    }



	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public void setPersonaggio(AbstractPersonaggio ap) {
		this.personaggio = ap;
	}
}