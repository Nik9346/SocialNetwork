package it.corso.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //classe di configurazione
public class GestoreBean {

	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	ModelMapper mapper() {  //strumento utilizzato per mappare il modello da utilizzare per la presentazione dei dati
		return new ModelMapper();
	}
}
