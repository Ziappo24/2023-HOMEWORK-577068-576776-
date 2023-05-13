package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatorePesoNome implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		if(a1.getPeso()-a2.getPeso()<0)
			return -1;
		if(a1.getPeso()-a2.getPeso()==0) {
			if(a1.getNome().compareTo(a2.getNome())<0)
				return -1;
			if(a1.getNome().compareTo(a2.getNome())==0)
				return 0;
			else
				return 1;
		}
		else
			return 1;
	}
}
