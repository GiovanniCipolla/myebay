package it.prova.myebay.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "annuncio")
public class Annuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "testoannuncio")
	private String testoAnnuncio;
	@Column(name = "prezzo")
	private Integer prezzo;
	@Column(name = "data")
	private LocalDate data;
	@Column(name = "aperto")
	private boolean aperto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utenteInserimento;
	
	@ManyToMany(mappedBy = "annunci", fetch = FetchType.LAZY)
	private Set<Categoria> categorie = new HashSet<>();

	public Annuncio() {
		super();
	}

	public Annuncio(String testoAnnuncio, Integer prezzo, LocalDate data, boolean aperto) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
	}

	

	public Annuncio(Long id) {
		super();
		this.id = id;
	}

	

	public Annuncio(Long id, String testoAnnuncio, Integer prezzo, LocalDate data, boolean aperto) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public boolean isAperto() {
		return aperto;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public Utente getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(Utente utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}
	
	
	
}
