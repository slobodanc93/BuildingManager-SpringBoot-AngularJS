package scvetkovic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scvetkovic.model.Zgrada;
import scvetkovic.repository.ZgradaRepository;
import scvetkovic.service.ZgradaService;

@Service
public class JpaZgradaServiceImpl implements ZgradaService {

	@Autowired
	private ZgradaRepository zgradaRepository;

	@Override
	public Zgrada findOne(Long id) {
		return zgradaRepository.findOne(id);
	}

	@Override
	public List<Zgrada> findAll() {
		return zgradaRepository.findAll();
	}

	@Override
	public void save(Zgrada zgrada) {
		zgradaRepository.save(zgrada);

	}

	@Override
	public void remove(Long id) {
		zgradaRepository.delete(id);
	}

	
	
	
}
