package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Labirinto: ha la responsabilità di creare il labirinto, 
 * di memorizzare la stanza iniziale (entrata) e quella finale (uscita)
 * @author ZIAPPO
 * @see Partita
 * @version base
 */
public class Labirinto {
	
	private Stanza stanzaVincente;
	private Stanza stanzaIngresso;
	
	public Labirinto() {
		inizia();
	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void inizia() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.setNear("nord", biblioteca);
		atrio.setNear("est", aulaN11);
		atrio.setNear("sud", aulaN10);
		atrio.setNear("ovest", laboratorio);
		aulaN11.setNear("est", laboratorio);
		aulaN11.setNear("ovest", atrio);
		aulaN10.setNear("nord", atrio);
		aulaN10.setNear("est", aulaN11);
		aulaN10.setNear("ovest", laboratorio);
		laboratorio.setNear("est", atrio);
		laboratorio.setNear("ovest", aulaN11);
		biblioteca.setNear("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        stanzaIngresso = atrio;  
		stanzaVincente = biblioteca;
    }
    
    /**
	 * 
	 * @return restituisce la stanza vincente
	 */
    public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
    
    /**
	 * 
	 * @return restituisce la stanza d'ingresso
	 */
    public Stanza getStanzaCorrente() {
    	return stanzaIngresso;
    }
    
    
}
