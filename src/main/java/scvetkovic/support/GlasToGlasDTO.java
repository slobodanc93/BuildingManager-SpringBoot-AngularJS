package scvetkovic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scvetkovic.model.Glas;
import scvetkovic.web.dto.GlasDTO;

@Component
public class GlasToGlasDTO implements Converter<Glas, GlasDTO>  {

	@Override
	public GlasDTO convert(Glas source) {
		if(source==null){
			return null;
		}
		GlasDTO target = new GlasDTO();
		target.setId(source.getId());
		target.setPorukaId(source.getPoruka().getId());
		target.setPorukaNaslov(source.getPoruka().getNaslov());
		target.setPredlogPodrzan(source.getPredlogPodrzan());
		target.setKomentar(source.getKomentar());
		
		return target;
	}
	
	public List<GlasDTO> convert(List<Glas> source){
		List<GlasDTO> target = new ArrayList<>();	
		for(Glas g: source){
			target.add(convert(g));
		}	
		return target;
	}

}
