package scvetkovic.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import scvetkovic.model.Glas;
import scvetkovic.model.Poruka;
import scvetkovic.model.Zgrada;
import scvetkovic.repository.GlasRepository;
import scvetkovic.service.GlasService;

@Service
@Transactional
public class JpaGlasServiceImpl implements GlasService {

	private int itemsPerPage = 5;
	
	@Autowired
	private GlasRepository glasRepository;
	

	@Override
	public Glas findOne(Long id) {
		return glasRepository.findOne(id);
	}

	@Override
	public List<Glas> findAll() {
		return glasRepository.findAll();
	}

	@Override
	public void save(Glas glas) {
		glasRepository.save(glas);
	}

	@Override
	public void remove(Long id) {
		glasRepository.delete(id);
	}

	@Override
	public Page<Glas> findByPorukaId(Long porukaId, int pageNum) {
		return glasRepository.findByPorukaId(porukaId, new PageRequest(pageNum, itemsPerPage));
	}

	@Override
	public void glasaj(Glas glas) {
		if(glas == null) {
			throw new IllegalArgumentException("Novi glas ne može biti null!");
		}
		
		Poruka poruka = glas.getPoruka();
		Zgrada zgrada = poruka.getZgrada();
		
		if(zgrada.getBrojStanara() < poruka.getGlasovi().size()) {
			throw new IllegalArgumentException("Glasali su svi stanari!");
		}
		
		Integer brojPozitivnihGlasova = 0;
		for(int i=0; i < poruka.getGlasovi().size(); i++) {
			if( poruka.getGlasovi().get(i).getPredlogPodrzan().equals("DA") ) {
				brojPozitivnihGlasova++;
			}
		}
		
		double stvarniProcenat = ((brojPozitivnihGlasova / (double) zgrada.getBrojStanara()) * 100);
		System.out.println("Stvarni procenat:" + stvarniProcenat);
		double potrebanProcenat = glas.getPoruka().getPotrebanProcenat();
		System.out.println("Potreban procenat:" + potrebanProcenat);
		
		if (stvarniProcenat >= potrebanProcenat){
			poruka.setPrihvacen(true);
		}

		glasRepository.save(glas);

	} 

	
}
