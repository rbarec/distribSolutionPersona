package catalog.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import catalog.api.Catalogo;

@Repository
public interface CatalogoRepository extends MongoRepository<Catalogo, Long> {

	public Optional<Catalogo> findByCodigo(String codigo);
	public Optional<Catalogo> findByContextoAndGrupo(String contexto, String grupo);
	

}