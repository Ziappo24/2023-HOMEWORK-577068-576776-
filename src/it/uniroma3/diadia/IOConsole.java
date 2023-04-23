package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {
	
	/**
	 * Stampa il messaggio che apparte sullo schermo per l'utente
	 * @param msg
	 */
	public void showMsg(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * Legge tutta una riga inserita come input dalla tastiera
	 * @return la ruga che viene scansionata
	 */
	public String readLine() {
		Scanner lineScanner = new Scanner(System.in);
		String line = lineScanner.nextLine();
		// lineScanner.close();
		return line;
	}
}
