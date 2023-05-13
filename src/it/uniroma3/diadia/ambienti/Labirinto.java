package it.uniroma3.diadia.ambienti;



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
	
    public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
    
    public Stanza getStanzaIngresso() {
    	return stanzaIngresso;
    }
    
    public void setStanzaIngresso(Stanza stanzaIngresso) {
    	this.stanzaIngresso = stanzaIngresso;
    }
   
    public void setStanzaVincente(Stanza stanzaVincente) {
    	this.stanzaVincente = stanzaVincente;
    }
    
	/*public Labirinto() {
	inizia();
}*/

/**
 * Crea tutte le stanze e le porte di collegamento
 */
/* private void inizia() {

	*//* crea gli attrezzi *//*
	Attrezzo lanterna = new Attrezzo("lanterna",3);
	Attrezzo osso = new Attrezzo("osso",1);
	Attrezzo chiave = new Attrezzo("chiave",1);
	Attrezzo coltello = new Attrezzo("coltello",2);
	Attrezzo scala = new Attrezzo("scala", 5);
	
	*//* crea stanze del labirinto *//*
	Stanza atrio = new Stanza("Atrio");
	Stanza aulaN11 = new Stanza("Aula N11");
	Stanza aulaN10 = new Stanza("Aula N10");
	Stanza laboratorio = new Stanza("Laboratorio Campus");
	Stanza biblioteca = new Stanza("Biblioteca");
	
	*//* stanze particolari *//*
	StanzaMagica mensa = new StanzaMagica("Mensa");
	StanzaBuia bagno = new StanzaBuia("Bagno", "lanterna");
	StanzaBloccata stanzino = new StanzaBloccata("Stanzino", "nord", "chiave");
	
	*//* collega le stanze */ /*
	atrio.setNear("nord", biblioteca);
	atrio.setNear("est", aulaN11);
	atrio.setNear("sud", aulaN10);
	atrio.setNear("ovest", laboratorio);
	aulaN11.setNear("nord", mensa);
	aulaN11.setNear("sud", stanzino);
	aulaN11.setNear("est", laboratorio);
	aulaN11.setNear("ovest", atrio);
	aulaN10.setNear("nord", atrio);
	aulaN10.setNear("sud", bagno);
	aulaN10.setNear("est", aulaN11);
	aulaN10.setNear("ovest", laboratorio);
	laboratorio.setNear("est", atrio);
	laboratorio.setNear("ovest", aulaN11);
	biblioteca.setNear("sud", atrio);
	biblioteca.setNear("est", mensa);
	mensa.setNear("sud", aulaN11);
	mensa.setNear("ovest", biblioteca);
	stanzino.setNear("nord", aulaN11);
	stanzino.setNear("est", bagno);
	bagno.setNear("nord", aulaN10);
	bagno.setNear("ovest", stanzino);

    /* pone gli attrezzi nelle stanze *//*
	aulaN10.addAttrezzo(lanterna);
	atrio.addAttrezzo(osso);
	laboratorio.addAttrezzo(chiave);
	stanzino.addAttrezzo(scala);
	mensa.addAttrezzo(coltello);

	// il gioco comincia nell'atrio
    stanzaIngresso = atrio;  
	stanzaVincente = biblioteca;
} */


}
