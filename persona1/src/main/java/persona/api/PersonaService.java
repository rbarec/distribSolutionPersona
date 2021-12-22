package persona.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import persona.dao.Persona;
import persona.dao.PersonaRepository;
import persona.util.GenericRequestSearch;

@Log4j2
@Service
public class PersonaService {

	@Autowired
	PersonaRepository personaRepository;

	public Persona getPersonaById(Long idPersona) throws Exception {
		Optional<Persona> per = personaRepository.findById(idPersona);
		if (per.isPresent()) {
			return per.get();
		}
		return null;
	}

	public Persona getPersonaByIdentificacionEx( //
			String identificacion, //
			String estado //
	) throws Exception {

		List<Persona> result = personaRepository.queryList(//
				identificacion, //
				estado, null);
		return result.get(0);
	}

	public List<Persona> queryList(//
			String identificacion, //
			String estado, String filter) throws Exception {

		return  personaRepository.queryList(//
				identificacion, //
				estado, filter);
	}

	public List<Persona> findAll() {
		return personaRepository.findAll();
	}

	/**
	 * 
	 * <b>crear Persona</b><br />
	 * <br />
	 */
	public Persona createPersonaNormal(//
			Persona newObj)//
			throws Exception {

		personaRepository.insert(newObj);
		// ===========================
		//
		return newObj;
	}

}
