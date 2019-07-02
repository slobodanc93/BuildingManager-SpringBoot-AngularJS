package scvetkovic.service;

import java.util.List;

import org.springframework.data.domain.Page;

import scvetkovic.model.Glas;

public interface GlasService {
	
	Glas findOne(Long id);
	List<Glas> findAll();
	void save(Glas glas);
	void remove(Long id);
	
	Page<Glas> findByPorukaId(Long porukaId, int pageNum);
	void glasaj(Glas glas);

}
