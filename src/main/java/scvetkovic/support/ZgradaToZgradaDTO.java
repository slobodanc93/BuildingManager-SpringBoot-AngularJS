package scvetkovic.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scvetkovic.model.Zgrada;
import scvetkovic.web.dto.ZgradaDTO;

@Component
public class ZgradaToZgradaDTO implements Converter<Zgrada, ZgradaDTO> {

	@Override
	public ZgradaDTO convert(Zgrada source) {
		if(source==null){
			return null;
		}
		ZgradaDTO target = new ZgradaDTO();
		target.setId(source.getId());
		target.setAdresa(source.getAdresa());
		target.setBrojStanara(source.getBrojStanara());
		target.setBrojStanova(source.getBrojStanova());
		target.setPredsednik(source.getPredsednik());
		return target;
	}
	
	public List<ZgradaDTO> convert(List<Zgrada> source) {
		List<ZgradaDTO> target = new ArrayList<>();
		for(Zgrada z : source) {
			target.add(convert(z));
		}
		return target;
	}

}
