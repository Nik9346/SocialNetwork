package it.corso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.dto.UtenteDto;
import it.corso.helper.PasswordValidationException;
import it.corso.helper.Risposta;
import it.corso.model.Utente;
import it.corso.schema.LoginUtenteSchema;
import it.corso.service.UtenteService;
import jakarta.validation.Valid;

@RestController // da utilizzare per far in modo che sia un controller specifico per un rest
@RequestMapping("/social/utenti")
@Tag(name = "Controller Utente", description = "Funzionalità per la gestione degli utenti") // proprità utilizzata per
																							// la documentazione
public class UtenteController {
	@Autowired
	private UtenteService utenteService;

	// endpoint #1: registrazione utente segniamo su ogni endpoint l'indirizzo
	// completo localhost:8080/social/utenti/create
	@PostMapping("/create")
	public ResponseEntity<Risposta> registrazioneUtente(@Valid @RequestBody Utente utente) { // con requestBody possiamo
																								// prendere direttamente
																								// il
		// json e convertirlo a oggetto in automatico le
		// chiavi utilizzate nel json devono essere
		// identiche alle variabili degli attributi oggetto
		if (!utente.getPassword().matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,50}"))
			throw new PasswordValidationException(); // lancia questa eccezione personalizzata
		Risposta risposta = utenteService.registrazioneUtente(utente);
		return ResponseEntity.status(risposta.getCodice()).body(risposta); // invochiamo il getcodice per assegnarlo
																			// allo stato e la risposta al body
	}

	// endpoint #2: dati utente localhost:8080/social/utenti/get/{id utente}
	// //attraverso una path variable
	@GetMapping("/get/{id}") // nelle parentesi graffe ci va un segnaposto generico, le {} fanno capire che è
								// una parte dinamica
	public ResponseEntity<Object> datiUtenteById(@PathVariable("id") Integer id) // ci va lo stesso segnaposto della
																					// richiesta
	{
		Object risposta = utenteService.datiUtenteById(id);
		if (risposta instanceof Risposta)
			return ResponseEntity.status(((Risposta) risposta).getCodice()).body((Risposta) risposta); // castiamo la
																										// classe
																										// dell'object a
																										// Risposta e
																										// poi
																										// assegniamo
																										// gli attributi
		return ResponseEntity.status(HttpStatus.OK).body((UtenteDto) risposta); // se è un'istanza dell'oggetto utente
																				// allora settiamo la risposta su ok e
																				// castiamo nel body la risposta a
																				// utente
	}

	// endpoint #3: elenco utenti localhost:8080/social/utenti/get
	@GetMapping("/get")
	public ResponseEntity<List<UtenteDto>> elencoUtenti() {
		return ResponseEntity.status(HttpStatus.OK).body(utenteService.elencoUtenti()); // come corpo impostiamo quello
																						// che ci fornisce il metodo di
																						// servizio
	}

	// endpoint #4: modifica dati utente localhost:8080/social/utenti/update/{tkn
	// utente}
	@PutMapping("/update/{tkn}")
	public ResponseEntity<Risposta> modificaDatiUtente(@PathVariable("tkn") String token,
			@Valid @RequestBody Utente nuoviDati) {
		Risposta risposta = utenteService.modificaDatiUtente(token, nuoviDati);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

	// endpoint #5: eliminazione utente localhost:8080/social/utenti/delete/{tkn
	// utente}
	@DeleteMapping("/delete/{tkn}")
	public ResponseEntity<Risposta> eliminazioneUtente(@PathVariable("tkn") String token) {
		Risposta risposta = utenteService.eliminazioneUtente(token);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

	// endpoint #6: login utente localhost:8080/social/utenti/login
	@Operation(summary = "Login Utente", description = "Gestione richiesta Login Utente e verifica credenziali")
	@io.swagger.v3.oas.annotations.parameters.RequestBody // attenzione a non confonderla con quella di springFramework
	(content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginUtenteSchema.class))

	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login Autorizzato - ritorna token di autenticazione", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Risposta.class))),
			@ApiResponse(responseCode = "401", description = "Login non Autorizzato", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Risposta.class))) })
	@PutMapping("/login") // serve una richiesta che passi i dati nel body inoltre apportiamo una modifica
							// x il token quindi put è corretto
	public ResponseEntity<Risposta> loginUtente(@RequestBody Map<String, String> corpoRichiesta) { // sfrutto la mappa
																									// che è l'oggetto
																									// simile al json
																									// chiave valore
		Risposta risposta = utenteService.loginUtente(corpoRichiesta.get("nickname"), corpoRichiesta.get("password")); // recupero
																														// i
																														// dati
																														// dalle
																														// relative
																														// chiavi
																														// del
																														// json
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

	// endpoint #7: logout utente localhost:8080/social/utenti/logout/{token}
	@Operation(summary = "Logout Utente", description = "Gestione richiesta Logout Utente (richiede token auth)")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Logout Autorizzato", 
					content = @Content(
							mediaType = "application/json", 
							schema = @Schema(implementation = Risposta.class))),
			@ApiResponse(
					responseCode = "401", 
					description = "Operazione non autorizzata", 
					content = @Content(
							mediaType = "application/json", 
							schema = @Schema(implementation = Risposta.class))) })
	@DeleteMapping("/logout/{tkn}")
	public ResponseEntity<Risposta> logoutUtente(
			@Parameter(description = "Token di autenticazione")
			@PathVariable("tkn") String token) {
		Risposta risposta = utenteService.logoututente(token);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}

	// metodo per intercettare eccezione di validazione dati utente
	@ExceptionHandler(BindException.class) // notazione eccezione org.springframework.validation
	public ResponseEntity<Map<String, String>> gestioneEccezioneValidazione(BindException e) {
		Map<String, String> errori = new HashMap<>(); // si utilizza la versione più efficiente poichè non ci interessa
														// ordinamento
		e.getBindingResult().getFieldErrors() // tutto il registro degli errori riscontrati sui campi
				.forEach(error -> errori.put(error.getField(), error.getDefaultMessage())); // per ogni errore prendiamo
																							// il campo errore e il
																							// messaggio di default
		return ResponseEntity.badRequest() // setta la risposta a badrequest
				.body(errori); // il corpo della nostra risposta diventa la mappa degli errori
	}

	// metodo per intercettare eccezione di validazione password
	@ExceptionHandler(PasswordValidationException.class)
	public ResponseEntity<Risposta> gestioneEccezioneValidazionePassword(PasswordValidationException e) {
		Risposta risposta = new Risposta(400, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(risposta);
	}
}
