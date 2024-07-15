package it.corso.dto;

import java.time.LocalDateTime;

import it.corso.model.Post;
import it.corso.model.Utente;


public class CommentoPostDto {
	
	private Integer id;
	private LocalDateTime dataOra;
	private String contenuto;
	private UtenteCommentoDto editor; //bisogna cambiare la chiave della variabile poichè se l'autore del post commenta il post si rompe tutto
	
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
	public UtenteCommentoDto getEditor() {
		return editor;
	}
	public void setEditor(UtenteCommentoDto editor) {
		this.editor = editor;
	}
	
	
}
