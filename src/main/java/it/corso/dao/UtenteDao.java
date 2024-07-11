package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Utente;
import java.util.List;


public interface UtenteDao extends CrudRepository<Utente, Integer>{

	Utente findByNickname(String nickname); //facciamo in modo che il nickname sia univoco, dichiarando questo metodo di verifica prima della registrazione
	Utente findByToken(String token); // verifichiamo l'autenticazione con questo metodo
}
