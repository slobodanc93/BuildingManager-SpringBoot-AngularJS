package scvetkovic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import scvetkovic.model.Poruka;

public interface PorukaService {
	
	Poruka findOne(Long id);
	Page<Poruka> findAll(int pageNum);
	void save(Poruka poruka);
	Poruka remove(Long id);

	Page<Poruka> pretraga(
			@Param("zgradaId") Long zgradaId,
			@Param("naslov") String naslov,
			@Param("tip") String tip,
			int pageNum);

	Page<Poruka> findByZgradaId(Long zgradaId, int pageNum);

}
