package it.corso.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="utenti")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private LocalDate dataIscrizione; //da camelCase a sintassi mysql data_iscrizione viene accettato
	
	@Column
	@Pattern(regexp="[a-zA-Z\\sàèìòù']{1,50}", message = "Errore nel campo nome")
	private String nome;
	
	@Column
	@Pattern(regexp="[a-zA-Z\\sàèìòù']{1,50}", message = "Errore nel campo cognome")
	private String cognome;
	
	@Column
	@Pattern(regexp="[a-zA-Z0-9.]{1,20}", message = "Errore nel campo nickname")
	private String nickname;
	
	@Column
	private String immagineProfilo;
	
	@Column
	@JsonProperty(access = Access.WRITE_ONLY) //settiamo la proprietà di accesso solo in scrittura quindi per costruire l'oggetto, non quando l'oggetto si deve presentare come json
	private String password;
	
	@Column
	@JsonIgnore //ignora completamente la presenza di questo attributo sia in fase di serializzazione che deserializzazione
	private String token;
	
	//setter e getter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDataIscrizione() {
		return dataIscrizione;
	}
	public void setDataIscrizione(LocalDate dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImmagineProfilo() {
		return immagineProfilo;
	}
	public void setImmagineProfilo(String immagineProfilo) {
		this.immagineProfilo = immagineProfilo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
