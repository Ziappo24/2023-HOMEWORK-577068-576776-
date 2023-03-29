package it.uniroma3.diadia;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class Comando {

    private String nome;
    private String parametro;

    public Comando(String[] istruzioni, int indice) {

	
		if (indice==0)
			this.sconosciuto(); 
		if (indice==1)
			setNome(istruzioni, indice-1);
		if(indice==2) {
			setNome(istruzioni, indice-2);
			setParametro(istruzioni, indice-1);
		}
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String[] istruzioni, int indice) {
    	this.nome = istruzioni[indice];
    }
    public void setParametro(String[] istruzioni, int indice) {
    	this.parametro = istruzioni[indice];
    }

    public String getParametro() {
        return this.parametro;
    }

    public String sconosciuto() {
        return (this.nome = null);
    }
}