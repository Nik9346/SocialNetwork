package it.corso.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "post_pubblicati")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private LocalDateTime dataOra;
	
	@Column
	private String contenuto;
	
	@Column
	private String immagine;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_utente", referencedColumnName = "id")
	private Utente utente;
	
	@OneToMany (mappedBy = "post",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true) //un post avrà molti commenti, se vado a cancellare un post, cancello i commenti
	private List<Commento> commenti = new ArrayList<>();
	
	@OneToMany (mappedBy = "post",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true) //un post avrà molti like, se vado a cancellare un post, cancello i commenti
	private List<Like> likes = new ArrayList<>();
	
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
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public List<Commento> getCommenti() {
		return commenti;
	}
	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}
	public List<Like> getLikes() {
		return likes;
	}
	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
}
