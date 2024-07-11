package it.corso.service;

import java.util.List;

import it.corso.helper.Risposta;
import it.corso.model.Utente;

public interface UtenteService {
	
	Risposta registrazioneUtente(Utente utente);
	Risposta modificaDatiUtente(Integer id, Utente nuoviDati);
	Risposta eliminazioneUtente(Integer id);
	Object datiUtenteById(Integer id); //facciamo ritornare un object in quanto dobbiamo ritornare o risposta o utente che sono un object
	List<Utente> elencoUtenti();
	Risposta loginUtente(String nickname, String password);
	Risposta logoututente(String token);
}
