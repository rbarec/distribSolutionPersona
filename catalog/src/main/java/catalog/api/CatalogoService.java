package catalog.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catalog.dao.CatalogoRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CatalogoService {

	@Autowired
	CatalogoRepository catalogoRepository;

	public Catalogo getCatalogoActivo(String codigo) throws Exception {
		log.info("invoacion de catalogo");
		Optional<Catalogo> result = catalogoRepository.findByCodigo(codigo);
		Catalogo cat = result.isPresent() ? result.get() : null;
		return cat;
	}

	
	
}
