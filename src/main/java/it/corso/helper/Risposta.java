package it.corso.helper;

public class Risposta {
	
	private int codice;
	private String messaggio;

	public Risposta(int codice, String messaggio) {
		this.codice = codice;
		this.messaggio = messaggio;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	
}
