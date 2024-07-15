package it.corso.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="relazioni_amicizia")
public class Amicizia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private boolean stato;
	@ManyToOne(cascade = CascadeType.REFRESH) //molte amicizie possono essere richieste ad un utente
	@JoinColumn(name="id_richiedente", referencedColumnName = "id")
	private Utente richiedente;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_ricevente", referencedColumnName = "id")
	private Utente ricevente;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isStato() {
		return stato;
	}
	public void setStato(boolean stato) {
		this.stato = stato;
	}
	public Utente getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(Utente richiedente) {
		this.richiedente = richiedente;
	}
	public Utente getRicevente() {
		return ricevente;
	}
	public void setRicevente(Utente ricevente) {
		this.ricevente = ricevente;
	}
	
}
