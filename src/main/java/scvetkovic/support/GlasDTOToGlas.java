package scvetkovic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scvetkovic.model.Glas;
import scvetkovic.model.Poruka;
import scvetkovic.service.GlasService;
import scvetkovic.service.PorukaService;
import scvetkovic.web.dto.GlasDTO;

@Component
public class GlasDTOToGlas  implements Converter<GlasDTO, Glas> {

	@Autowired
	private PorukaService porukaService;

	@Autowired
	private GlasService glasService;
	
	@Override
	public Glas convert(GlasDTO source) {
		Glas target;
		
		if(source.getPorukaId() == null) {
			throw new IllegalArgumentException("Id poruke ne može biti null");
		} 
		
		Poruka poruka = porukaService.findOne(source.getPorukaId());

		if(source.getId() == null) {
			target = new Glas();
		} else {
			target = glasService.findOne(source.getId());
		}
		
		target.setPoruka(poruka);
		target.setPredlogPodrzan(source.getPredlogPodrzan());
		target.setKomentar(source.getKomentar());
		
		return target;
	}
	
	public List<Glas> convert(List<GlasDTO> source) {	
		List<Glas> target = new ArrayList<>();	
		for(GlasDTO g : source) {
			target.add(convert(g));
		}	
		return target;
	}
}
