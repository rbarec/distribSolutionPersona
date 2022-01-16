package catalog.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catalog.dao.CatalogoRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CatalogoService {

	@Autowired
	CatalogoRepository catalogoRepository;

	

	public Catalogo getConocimientoContextoGrupo(String contexto, String grupo) {
		Optional<Catalogo> result = catalogoRepository.findByContextoAndGrupo(contexto, grupo);
		Catalogo cat = result.isPresent() ? result.get() : null;
		return cat;
	}

	
	
}
