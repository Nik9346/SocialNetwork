package it.corso.dto;

import java.time.LocalDateTime;
import java.util.List;
import it.corso.model.Commento;
import it.corso.model.Like;


public class PostUtenteDto {

	private Integer id;
	private LocalDateTime dataOra;
	private String contenuto;
	private String immagine;
	private List<CommentoPostDto> commenti;
//	private List<Like> likes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	public List<CommentoPostDto> getCommenti() {
		return commenti;
	}
	public void setCommenti(List<CommentoPostDto> commenti) {
		this.commenti = commenti;
	}
}
