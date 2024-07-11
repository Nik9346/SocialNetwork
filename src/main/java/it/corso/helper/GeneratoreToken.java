package it.corso.helper;

import java.time.Instant;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component //notation padre di tutti i package identifica questa classe come componente strutturale dell'applicazione a cui possiamo accedere tramite la notation autowired
public class GeneratoreToken {

	public String generazioneToken(String nickname)
	{
		LocalDateTime now = LocalDateTime.now(); //istanziamo e invochiamo una variabile datatime che generiamo all'invocazione del metodo
		Instant instant = now.toInstant(OffsetDateTime.now().getOffset());
		long timestamp = instant.getEpochSecond() *1000; // in millisecondi *1000
		String nicknameCodificato = Base64.getEncoder().encodeToString(nickname.getBytes()); //codifichiamo il nickname in base64
		return nicknameCodificato + "_" + timestamp; // formiamo il token con il nickname codificato _ il timestamp che ad ogni accesso cambia e quindi è univoco
		
	}
}
