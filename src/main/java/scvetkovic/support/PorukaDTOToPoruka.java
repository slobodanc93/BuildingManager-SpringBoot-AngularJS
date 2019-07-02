package scvetkovic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scvetkovic.model.Poruka;
import scvetkovic.model.Zgrada;
import scvetkovic.service.PorukaService;
import scvetkovic.service.ZgradaService;
import scvetkovic.web.dto.PorukaDTO;

@Component
public class PorukaDTOToPoruka  implements Converter<PorukaDTO, Poruka> {

	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private ZgradaService zgradaService;
	
	@Override
	public Poruka convert(PorukaDTO source) {
		Poruka target;
		
		if(source.getZgradaId() == null) {
			throw new IllegalArgumentException("Id zgrade ne može biti null");
		} 
		
		Zgrada zgrada = zgradaService.findOne(source.getZgradaId());

		if(source.getId() == null) {
			target = new Poruka();
		} else {
			target = porukaService.findOne(source.getId());
		}
		
		target.setZgrada(zgrada);
		target.setNaslov(source.getNaslov());
		target.setOpis(source.getOpis());
		target.setPotrebanProcenat(source.getPotrebanProcenat());
		target.setPrihvacen(source.getPrihvacen());
		target.setTip(source.getTip());

		
		return target;
	}
	
	public List<Poruka> convert(List<PorukaDTO> source) {	
		List<Poruka> target = new ArrayList<>();	
		for(PorukaDTO p : source) {
			target.add(convert(p));
		}	
		return target;
	}
}
