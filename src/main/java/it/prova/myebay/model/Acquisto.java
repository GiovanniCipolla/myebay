package it.prova.myebay.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "acquisto")
public class Acquisto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "prezzo")
	private Integer prezzo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id",nullable = false)
	private Utente utenteAcquirente;
	

	public Acquisto() {
		super();
	}

	public Acquisto(String descrizione, LocalDate data, Integer prezzo) {
		super();
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
	}

	

	public Acquisto(Long id) {
		super();
		this.id = id;
	}

	

	public Acquisto(Long id, String descrizione, LocalDate data, Integer prezzo) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Utente getUtenteAcquirente() {
		return utenteAcquirente;
	}

	public void setUtenteAcquirente(Utente utenteAcquirente) {
		this.utenteAcquirente = utenteAcquirente;
	}


	
	
	
	
}
