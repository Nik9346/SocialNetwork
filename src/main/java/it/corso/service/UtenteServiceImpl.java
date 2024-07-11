package it.corso.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import it.corso.dao.UtenteDao;
import it.corso.helper.GeneratoreToken;
import it.corso.helper.Risposta;
import it.corso.model.Utente;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private GeneratoreToken generatoreToken;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Risposta registrazioneUtente(Utente utente) {
		if(utenteDao.findByNickname(utente.getNickname()) != null) //se già qualcuno è registrato con lo stesso nickname
			return new Risposta(409,"Nickname già in uso"); //blocchiamo tutto fornendo una risposta con la stringa nickname già registrato
		try {
			utente.setDataIscrizione(LocalDate.now());
			utente.setPassword(encoder.encode(utente.getPassword())); // codifichiamo la password prima di passare il dato al database
			utenteDao.save(utente);
			return new Risposta(201, "Utente Registrato"); // passiamo il messaggio dell'utente registrato con codice
															// 201
		} catch (DataIntegrityViolationException e) {
			return new Risposta(400, e.getMessage()); // nel caso di una violazione nel dataIntegrity rispondiamo con
														// una bad request
		}
	}

	@Override
	public Risposta modificaDatiUtente(Integer id, Utente nuoviDati) {
		Optional<Utente> utenteOptional = utenteDao.findById(id); // protezione se l'utente non è presente nel db,
																	// restituiamo 404
		if (!utenteOptional.isPresent())
			return new Risposta(404, "utente non trovato");

		Utente utente = utenteOptional.get(); // recuperiamo l'utente dal db tramite il dao e costituiamo
												// l'oggetto utente
		// utente.setNome(nuoviDati.getNome()); rottura incredibile
		BeanUtils.copyProperties(nuoviDati, utente, "id", "dataIscrizione", "nickname", "immagineProfilo", "password",
				"token"); // non crea una copia dell'oggetto ma copia le proprietà quindi da nuovidati a
							// utente passando le stringhe si escludono i campi
		try {
			utenteDao.save(utente);
			return new Risposta(202, "Dati Utente Aggiornati");
		} catch (DataIntegrityViolationException e) {
			return new Risposta(400, e.getMessage()); // nel caso di una violazione nel dataIntegrity rispondiamo con
														// una bad request
		}

	}

	@Override
	public Risposta eliminazioneUtente(Integer id) {
		Optional<Utente> utenteOptional = utenteDao.findById(id); // protezione se l'utente non è presente nel db,
																	// restituiamo 404
		if (!utenteOptional.isPresent())
			return new Risposta(404, "Utente non trovato");
		utenteDao.delete(utenteOptional.get());
		return new Risposta(202, "Utente eliminato");
	}

	@Override
	public Object datiUtenteById(Integer id) {
		Optional<Utente> utenteOptional = utenteDao.findById(id); // protezione se l'utente non è presente nel db,
																	// restituiamo 404
		if (!utenteOptional.isPresent())
			return new Risposta(404, "Utente non trovato");
		return utenteOptional.get();
	}

	@Override
	public List<Utente> elencoUtenti() {

		return (List<Utente>) utenteDao.findAll();
	}

	@Override
	public Risposta loginUtente(String nickname, String password) {
		Utente utente = utenteDao.findByNickname(nickname);
		if(utente != null && encoder.matches(password, utente.getPassword())) //verifichiamo se l'utente esiste e la password va bene
		{
			utente.setToken(generatoreToken.generazioneToken(utente.getNickname())); // genero il token e lo vado a settare come attributo dell'utente
			utenteDao.save(utente); // salviamo solo l'attributo token in quanto gli altri dati saranno riscritti allo stesso modo perchè non modificati
			return new Risposta(200, utente.getToken());
		}
		return new Risposta(401, "Non autorizzato");
	}

	@Override
	public Risposta logoututente(String token) {
		Utente utente = utenteDao.findByToken(token);
		if(utente == null) //se non abbiamo trovato nessun utente, qualcuno sta forzando il sistema
			return new Risposta(401, "Operazione non autorizzata");
		utente.setToken(null); // se troviamo l'utente, settiamo il token su null
		utenteDao.save(utente); //salviamo il nuovo attributo
		return new Risposta(200,"Logout avvenuto"); //mandiamo indietro il messaggio di avvenuto logout
		
	}

}
