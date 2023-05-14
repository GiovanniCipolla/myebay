package it.prova.myebay.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.prova.myebay.exception.AnnuncioChiusoException;
import it.prova.myebay.exception.CreditoInsufficienteException;
import it.prova.myebay.exception.UtenteNonTrovatoException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService {

	@Autowired
	private AcquistoRepository repository;

	@Autowired
	private AnnuncioService annuncioService;

	@Autowired
	private UtenteService utenteService;

	@Override
	public List<Acquisto> listAll() {
		return (List<Acquisto>) repository.findAll();
	}

	@Override
	public Acquisto caricaSingoloElemento(Long id) {
		return repository.findById(id).orElseThrow(() -> new UtenteNonTrovatoException());
	}

	@Override
	public Acquisto caricaElementoConUtente(Long id) {
		return repository.findByIdConUtente(id).orElseThrow(() -> new UtenteNonTrovatoException());
	}

	// per inserilo rispettare questi parametri
	// deve essere preso da un annuncio aperto, controllare
	// ci devono essere disbonibiltà economiche
	// si deve sotrrare il prezzo e aggiungere al venditore
	// settare l'annuncio chiuso
	@Override
	public void inserisciNuovoAcquisto(Long idAnnuncio) {

		// creare Acquisto , dovremmo settarlo a mano visto che non ci sarà una insert
		Acquisto acquistoInstance = new Acquisto();

		//controlliamo se l'annuncio è aperto o chiuso
		if (!annuncioService.caricaSingoloElemento(idAnnuncio).isAperto())
			throw new AnnuncioChiusoException();

		// prendiamo lusername dell'autenticato e vediamo se esiste nel db per sicurezza
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		if (utenteService.findByUsername(username) == null)
			throw new UtenteNonTrovatoException();

		Utente utente = utenteService.findByUsername(username);

		// tramite l'username risaliamo all'utente e controlliamo se ci sono le disponibilità economiche
		// per acquistare l'annuncio
		if (utente.getCreditoResiduo() < annuncioService.caricaSingoloElemento(idAnnuncio).getPrezzo())
			throw new CreditoInsufficienteException();

		// settiamo il credito dell'acquirente e del venditore
		Double nuovoCreditoUtenteCheAcquista = utente.getCreditoResiduo()
				- annuncioService.caricaSingoloElemento(idAnnuncio).getPrezzo();
		Double nuovoCreditoUtenteCheVende = annuncioService.caricaElementoConUtente(idAnnuncio).getUtente()
				.getCreditoResiduo() + annuncioService.caricaSingoloElemento(idAnnuncio).getPrezzo();
		utente.setCreditoResiduo(nuovoCreditoUtenteCheAcquista);
		annuncioService.caricaElementoConUtente(idAnnuncio).getUtente().setCreditoResiduo(nuovoCreditoUtenteCheVende);
		// mettiamo l'annuncio nullo
		annuncioService.caricaSingoloElemento(idAnnuncio).setAperto(false);
		
		//settiamo tutti i campi dell'acquisto copiando dall'annuncio
		acquistoInstance.setDataAcquisto(LocalDate.now());
		acquistoInstance.setDescrizione(annuncioService.caricaSingoloElemento(idAnnuncio).getTestoAnnuncio());
		acquistoInstance.setPrezzo(annuncioService.caricaSingoloElemento(idAnnuncio).getPrezzo());
		acquistoInstance.setUtente(utente);
		
		repository.save(acquistoInstance);

	}

	@Override
	public List<Acquisto> findByExampleRicerca(Acquisto example) {
		// TODO Auto-generated method stub
		return null;
	}

}
