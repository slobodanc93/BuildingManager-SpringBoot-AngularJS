package scvetkovic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scvetkovic.model.Zgrada;

@Repository
public interface ZgradaRepository  extends JpaRepository<Zgrada, Long>  {

}
