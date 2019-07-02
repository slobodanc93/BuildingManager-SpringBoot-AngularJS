package scvetkovic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scvetkovic.model.Poruka;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long>  {


	@Query("SELECT p FROM Poruka p WHERE "
			+ "(:zgradaId is NULL or p.zgrada.id like :zgradaId) AND "
			+ "(:naslov is NULL or p.naslov like :naslov) AND "
			+ "(:tip is NULL or p.tip = :tip)"
			)
	Page<Poruka> pretraga (
			@Param("zgradaId") Long zgradaId,
			@Param("naslov") String naslov,
			@Param("tip") String tip,
			Pageable pageRequest	
			);
	
	Page<Poruka> findByZgradaId (Long zgradaId, Pageable pageable);
 	
}
