package it.prova.myebay.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;

public class AcquistoDTO {

	private Long id;

	@NotBlank(message = "{acquisto.descrizione.notblank}")
	@Size(min = 2, max = 40, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String descrizione;

	@NotNull(message = "{acquisto.data.notnull}")
	private LocalDate data;

	@NotNull(message = "{acquisto.prezzo.notnull}")
	@Min(1)
	private Integer prezzo;

	@NotNull(message = "{acquisto.utente.notnull}")
	private UtenteDTO utenteAcquirente;

	private Long utenteAcquirenteId;

	public AcquistoDTO() {
	}

	public AcquistoDTO(Long id) {
		this.id = id;
	}

	public AcquistoDTO(Long id, String descrizione, LocalDate data, Integer prezzo) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
	}

	public AcquistoDTO(String descrizione, LocalDate data, Integer prezzo) {
		super();
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

	public UtenteDTO getUtenteAcquirente() {
		return utenteAcquirente;
	}

	public void setUtenteAcquirente(UtenteDTO utenteAcquirente) {
		this.utenteAcquirente = utenteAcquirente;
	}

	public Long getUtenteAcquirenteId() {
		return utenteAcquirenteId;
	}

	public void setUtenteAcquirenteId(Long utenteAcquirenteId) {
		this.utenteAcquirenteId = utenteAcquirenteId;
	}

	public Acquisto buildAcquistoModel(boolean includeUtenteAcquirente) {
		Acquisto result = new Acquisto(this.id, this.descrizione, this.data, this.prezzo);

		if (includeUtenteAcquirente && utenteAcquirenteId != null)
			result.setUtenteAcquirente(new Utente(utenteAcquirenteId));

		return result;
	}

	public static AcquistoDTO buildAcquistoDTOFromModel(Acquisto acquistoModel, boolean includeUtenteAcquirente) {
		AcquistoDTO result = new AcquistoDTO(acquistoModel.getId(), acquistoModel.getDescrizione(),
				acquistoModel.getData(), acquistoModel.getPrezzo());

		if (includeUtenteAcquirente)
			result.utenteAcquirenteId = acquistoModel.getUtenteAcquirente().getId();

		return result;
	}

	public static List<AcquistoDTO> createAcquistoDTOListFromModelList(List<Acquisto> modelListInput,
			boolean includeUtenteAcquirente) {
		return modelListInput.stream().map(acquistoEntity -> {
			return AcquistoDTO.buildAcquistoDTOFromModel(acquistoEntity, includeUtenteAcquirente);
		}).collect(Collectors.toList());
	}
}
