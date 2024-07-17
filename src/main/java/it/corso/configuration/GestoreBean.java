package it.corso.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
// localhost:8080/swagger-ui.html per accedere alla documentazione
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
	
	@Bean  //bean utilizzato per generare dei javadoc
	OpenAPI apiDocument() {
		OpenAPI api = new OpenAPI();
		Info apiInfo = new Info();
		apiInfo.title("Social Web Service");
		apiInfo.description("RESTFul Web Service App Social Network");
		apiInfo.version("1.0.0.0");
		api.info(apiInfo);
		return api;
	}
}
