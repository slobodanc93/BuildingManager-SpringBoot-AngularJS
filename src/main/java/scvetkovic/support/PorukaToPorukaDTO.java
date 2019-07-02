package scvetkovic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scvetkovic.model.Poruka;
import scvetkovic.web.dto.PorukaDTO;

@Component
public class PorukaToPorukaDTO  implements Converter<Poruka, PorukaDTO> {

	@Override
	public PorukaDTO convert(Poruka source) {
		if(source==null){
			return null;
		}
		PorukaDTO target = new PorukaDTO();
		target.setId(source.getId());
		target.setNaslov(source.getNaslov());
		target.setOpis(source.getOpis());
		target.setPotrebanProcenat(source.getPotrebanProcenat());
		target.setPrihvacen(source.getPrihvacen());
		target.setTip(source.getTip());
		target.setZgradaId(source.getZgrada().getId());
		target.setZgradaAdresa(source.getZgrada().getAdresa());
		
		return target;
	}
	
	public List<PorukaDTO> convert(List<Poruka> source) {
		List<PorukaDTO> target = new ArrayList<>();	
		for(Poruka p : source) {
			target.add(convert(p));
		}
		return target;
	}

}
