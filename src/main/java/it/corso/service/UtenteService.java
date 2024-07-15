package it.corso.service;

import java.util.List;

import it.corso.dto.UtenteDto;
import it.corso.helper.Risposta;
import it.corso.model.Utente;

public interface UtenteService {
	
	Risposta registrazioneUtente(Utente utente);
	Risposta modificaDatiUtente(String token, Utente nuoviDati);
	Risposta eliminazioneUtente(String token);
	
	Object datiUtenteById(Integer id); //facciamo ritornare un object in quanto dobbiamo ritornare o risposta o utente che sono un object
	List<UtenteDto> elencoUtenti(); //trasformazione del normale oggetto utente in utenteDto
	
	Risposta loginUtente(String nickname, String password);
	Risposta logoututente(String token);
}
