package persona.dao;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, Long>, PersonaRepositoryCustom {


//	@Cacheable(value = "persona", key = "#p0", cacheManager = "metadataCacheManager")
	public Optional<Persona> findById(//
			@Param("id")  Long id);

	
//	@Cacheable(value = "persona", key = "#p0", cacheManager = "metadataCacheManager")
	public Optional<Persona> findByIdentificacion(//
			@Param("identificacion")  String identificacion);
}