package scvetkovic.service;

import java.util.List;

import scvetkovic.model.Zgrada;

public interface ZgradaService {
	
	Zgrada findOne(Long id);
	List<Zgrada> findAll();
	void save(Zgrada zgrada);
	void remove(Long id);

}
