package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNome;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;
import it.uniroma3.diadia.attrezzi.ComparatorePesoNome;

/**
 * Classe Borsa: serve a tenere gli attrezzi
 * @author EDOARDO
 * @see Giocatore, Attrezzo
 * @version base
 */

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {

		this.pesoMax = DEFAULT_PESO_MAX_BORSA;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/**
	 * Aggiunge un attrezzo nella borsa
	 * @param attrezzo
	 * @return true se � stato aggiunto, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null) 
			return false;
		if(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		return true;
	}

	/**
	 * 
	 * @return restituisce il peso massimo sostenibile dalla borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Controlla se il nome dell'Attrezzo nel parametro corrisponde 
	 * ad un attrezzo nella borsa
	 * @param nomeAttrezzo
	 * @return attrezzo richiesto, altrimenti null
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi)
			if (attrezzo!=null && attrezzo.getNome().equals(nomeAttrezzo))
				return attrezzo;
		return null;
	}

	/**
	 * Calcola il peso corrente della borsa ad ogni sua invocazione
	 * @return peso della borsa aggiornato
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo : this.attrezzi)
			peso += attrezzo.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	/* nuova implementazione di hasAttrezzo */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.contains(this.getAttrezzo(nomeAttrezzo));
	}
	
	/*public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false; 	// inizializzo a false e quando lo trovo esco
		for(Attrezzo attrezzo : this.attrezzi) {
			if(nomeAttrezzo!=null && attrezzo!=null) {
				if(attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
			}
		}
		return trovato;
	}*/

	/**
	 * Rimuove un attrezzo
	 * @param nomeAttrezzo
	 * @return riferimento ad oggetto attrezzo
	 */
	/* nuova implementazione di removeAttrezzo */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo==null || !this.hasAttrezzo(nomeAttrezzo))
			return null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		Attrezzo itemRemove = null;
		Attrezzo rimosso = null;
		while(iteratore.hasNext()) {
			itemRemove = iteratore.next();
			if(itemRemove.getNome().contentEquals(nomeAttrezzo)) {
				rimosso = itemRemove;
				iteratore.remove();
			}
		}
		return rimosso;
		
	}
	
	/* public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo itemRemove = null;
		for(Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo!=null && attrezzo.getNome().equals(nomeAttrezzo)) {
				itemRemove = this.attrezzi[i];
				this.attrezzi[i]=this.attrezzi[i+1];
			}
			else {
				itemRemove = this.attrezzi[i];
				this.attrezzi[i]=null;
			}
		}
		return itemRemove;
	}*/

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : this.attrezzi)
				s.append(attrezzo.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		ComparatorePerPeso  comparatore = new ComparatorePerPeso();
		Collections.sort(this.attrezzi, comparatore);
		return this.attrezzi;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		ComparatorePerNome comparatore = new ComparatorePerNome();
		TreeSet<Attrezzo> setOrdinato = new TreeSet<Attrezzo> (comparatore);
		setOrdinato.addAll(attrezzi);
		return setOrdinato;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Set<Attrezzo> temp;
		ComparatorePerNome comparatore = new ComparatorePerNome();
		Map<Integer, Set<Attrezzo>> mappaAttrezzi;
		mappaAttrezzi = new HashMap<Integer, Set<Attrezzo>>();
		for(Attrezzo attrezzo : this.attrezzi) {	//scorro l'iterable ovvero l'array
			if(mappaAttrezzi.containsKey(attrezzo.getPeso())) {		// se trovo un attrezzo che ha lo stesso valore di peso
				temp = mappaAttrezzi.get(attrezzo.getPeso());		// lo prendo
				temp.add(attrezzo);									// lo metto in temp
			}
			else {		// non contiene un attrezzo di quel peso
				temp = new TreeSet<Attrezzo>(comparatore);			// creo alberello, con regola di ordine per nome (comparatore)
				temp.add(attrezzo);									// aggiungo all'alberello l'attrezzo che haun peso non ancora presente
				mappaAttrezzi.put(attrezzo.getPeso(), temp);		// lo metto nella mappa con tanto di chiave e valore
			}
		}
		return mappaAttrezzi;	
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		ComparatorePesoNome comparatore = new ComparatorePesoNome();
		TreeSet<Attrezzo> tempSort = new TreeSet<Attrezzo>(comparatore);
		tempSort.addAll(this.attrezzi);
		return tempSort;
	}
	
}
