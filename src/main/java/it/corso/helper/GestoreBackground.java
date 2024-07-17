package it.corso.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.corso.dao.UtenteDao;
import it.corso.model.Utente;

@Component //con questa notation diventa di accesso generale
public class GestoreBackground {
	
	@Autowired
	private UtenteDao utenteDao;
	
	//funzione utilizzata per fare il logout automatico delle utenze dopo un tot di tempo
	@Scheduled(fixedDelay = 60000) //fissiamo un delay per eseguire questa funzione, va espresso in millisecondi
	public void logoutUtenteAutomatico() {
		List<Utente> utenti = (List<Utente>) utenteDao.findAll();
		utenti.forEach(u->{
			if(u.getToken() != null) 
				u.setToken(null);
		});
		utenteDao.saveAll(utenti); //con saveAll facciamo un salvataggio generale
	}

}
