package scvetkovic.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import scvetkovic.model.Poruka;
import scvetkovic.repository.PorukaRepository;
import scvetkovic.service.PorukaService;

@Service
@Transactional
public class JpaPorukaServiceImpl implements PorukaService {
	
	private int itemsPerPage = 5;
	
	@Autowired
	private PorukaRepository porukaRepository;
	
	@Override
	public Poruka findOne(Long id) {
		return porukaRepository.findOne(id);
	}

	@Override
	public Page<Poruka> findAll(int pageNum) {
		return porukaRepository.findAll(new PageRequest(pageNum, itemsPerPage));
	}

	@Override
	public void save(Poruka poruka) {
		/*
		if(poruka.getSlobodnihDana() == null) {
			Integer slobodnihDana = 20;
			slobodnihDana= slobodnihDana + poruka.getGodinaStaza() / 5;
			slobodnihDana= slobodnihDana + poruka.getSektor().getBonusSlobodnihDana();
			poruka.setSlobodnihDana(slobodnihDana);
		}
		*/		
		porukaRepository.save(poruka);
	}

	@Override
	public Poruka remove(Long id) {
		Poruka poruka = porukaRepository.findOne(id);
		if(poruka != null) {
			porukaRepository.delete(poruka);
		}
		return poruka;
	}

	@Override
	public Page<Poruka> pretraga(Long zgradaId, String naslov, String tip, int pageNum) {
		if(naslov != null) {
			naslov = "%" + naslov + "%";
		}

		return porukaRepository.pretraga(zgradaId, naslov, tip, new PageRequest(pageNum, itemsPerPage));
	}
	
	@Override
	public Page<Poruka> findByZgradaId(Long zgradaId, int pageNum) {
		return porukaRepository.findByZgradaId(zgradaId, new PageRequest(pageNum, itemsPerPage));
	}

}
