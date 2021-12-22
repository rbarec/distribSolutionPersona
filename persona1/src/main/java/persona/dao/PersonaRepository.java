package persona.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, Long>, PersonaRepositoryCustom {

//	// TODO Ver
//	// https://stackoverflow.com/questions/49316751/spring-data-jpa-findone-change-to-optional-how-to-use-this
//	public Optional<PersonaModel> findByIdentificacionAndTipoIdentificacionAndEstadox(//
//			String identificacion, String tipoIdentificacion, String estado);
//
//	public Optional<PersonaModel> findByIdentificacionAndTipoIdentificacionx(//
//			String identificacion, String tipoIdentificacion);

}