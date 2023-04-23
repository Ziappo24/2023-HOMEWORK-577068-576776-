package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {


	private String dirBloccata;
	private String key;

	public StanzaBloccata(String nome, String dirBloccata, String chiave) {
		super(nome);
		this.dirBloccata = dirBloccata;
		this.key = chiave;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(!super.hasAttrezzo(key) && this.dirBloccata.equals(direzione))
			return this;
		else
			return super.getStanzaAdiacente(direzione);			
	}

	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(key))
			return super.getDescrizione() + this.infoDirezione() + this.infoChiave();
		else
			return super.getDescrizione();
	}

	public String infoDirezione() {
		return "\nDirezione bloccata: " + this.dirBloccata;
	}

	public String infoChiave() {
		return "\nAttrezzo sbloccante: " + this.key;
	}

	public String getNomeChiave() {
		return this.key;
	}
	public boolean isOpen() {
		return this.getAttrezzo(this.getNomeChiave())!=null;
	}
}
