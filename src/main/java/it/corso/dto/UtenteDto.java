package it.corso.dto;

import java.time.LocalDate;
import java.util.List;
import it.corso.model.Amicizia;
import it.corso.model.Commento;
import it.corso.model.Like;
import it.corso.model.Post;

public class UtenteDto {

	private Integer id;
	private LocalDate dataIscrizione;
	private String nome;
	private String cognome;
	private String nickname;
	private String immagineProfilo;
	private List<PostUtenteDto> posts; //è fondamentale mantenere i nomi delle variabili uguali all'oggetto originale nel model
//	private List<Commento> commenti;
//	private List<Like> likes;
//	private List<Amicizia> richieste;
//	private List<Amicizia> ricevute;
	
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
	
	public List<PostUtenteDto> getPosts() {
		return posts;
	}
	public void setPosts(List<PostUtenteDto> posts) {
		this.posts = posts;
	}
}
