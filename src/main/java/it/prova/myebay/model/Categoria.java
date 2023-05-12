package it.prova.myebay.model;

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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "codice")
	private String codice;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "categoria_annuncio", joinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "annuncio_id", referencedColumnName = "ID"))
	private Set<Annuncio> annunci = new HashSet<>();

	public Categoria() {
		super();
	}

	
	public Categoria(String descrizione, String codice) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
	}

	


	public Categoria(Long id) {
		super();
		this.id = id;
	}


	public Categoria(Long id, String descrizione, String codice, Set<Annuncio> annunci) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.codice = codice;
		this.annunci = annunci;
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

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Set<Annuncio> getAnnunci() {
		return annunci;
	}

	public void setAnnunci(Set<Annuncio> annunci) {
		this.annunci = annunci;
	}
	
	
}
