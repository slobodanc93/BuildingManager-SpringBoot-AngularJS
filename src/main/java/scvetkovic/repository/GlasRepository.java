package scvetkovic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scvetkovic.model.Glas;

@Repository
public interface GlasRepository extends JpaRepository<Glas, Long> {
	
	Page<Glas> findByPorukaId(Long porukaId, Pageable pageRequest);

}
