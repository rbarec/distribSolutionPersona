package persona.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;
import persona.dao.Persona;
import persona.dao.PersonaRepository;
import persona.util.CatalogoDTO;
import persona.util.CatalogoDTO.ItemCat;

@Log4j2
@Service
public class PersonaService {

	@Autowired
	PersonaRepository personaRepository;
	
	CatalogoClientService catalogoClientService;



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

		return personaRepository.queryList(//
				identificacion, //
				estado, filter);
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

	public List<String> findAll() {
		List<Persona> f = personaRepository.findAll();
		List<String> r = new ArrayList<>();

		for (Persona i : f) {
			int a = getRandom(1, 10);
			String getE = getEstadoCivil(a);
			i.setEstadoCivil(getE);
			if( i.getTipoIdentificacion() !=null ) {
				String t = i.getTipoIdentificacion(); 
				i.setTipoIdentificacion(t.toLowerCase());
			}
			if( i.getTipoPersona() !=null ) {
				String t = i.getTipoPersona(); 
				i.setTipoPersona(t.toLowerCase());
			}
			personaRepository.save(i);
			r.add("" + i.getIdentificacion() + " " + i.getNombres() + " " + i.getApellidos() + " "
					+ i.getEstadoCivil()+" "+i.getTipoPersona()+" "+i.getTipoIdentificacion());
		}
		return r;
	}

	
	
	
	
	
	
	private int getRandom(int a, int b) {
		Random r = new Random();
		int lowerBound = 1;
		int upperBound = 11;
		int result = r.nextInt(upperBound - lowerBound) + lowerBound;
		return result;
	}

	private String getEstadoCivil(int r) {
		List<String> a = new ArrayList<>();
		a.add("soltero");
		a.add("soltero");
		a.add("soltero");
		a.add("soltero");
		a.add("viudo");
		a.add("divorciado");
		a.add("casado");
		a.add("casado");
		a.add("soltero");
		a.add("soltero");
		a.add("viudo");
		a.add("divorciado");
		a.add("casado");
		a.add("viudo");
		a.add("divorciado");
		a.add("casado");
		return a.get(r);
	}

	public Persona getRandomPersona() throws Exception {
		int id = getRandom(1, 2000);
		long l = id;
		Long l2 = Long.valueOf(l);
		Optional<Persona> p = personaRepository.findById(l2);
		if (p.isPresent()) {
			validarPersona(p.get());
			return p.get();
		} else {
			int id2 = getRandom(1, 2000);
			long l2x = id2;
			Long l22 = Long.valueOf(l2x);
			Optional<Persona> p2 = personaRepository.findById(l22);
			validarPersona(p2.get());
			return p2.isPresent() ? p2.get() : null;
		}
	}
	
	
	public boolean validarPersona(Persona p) throws Exception{
		
		 CatalogoDTO  ec = catalogoClientService.getCatalogo_EstadosCivil();
		 boolean vec=false;
		 boolean vtp=false;
		 boolean vti=false;
		 
		 for( ItemCat i : ec.getItems() ) {
			 if( i.getKey().equalsIgnoreCase( p.getEstadoCivil() )  ) {
				 vec =  true;
			 }
		 }
		 ec = catalogoClientService.getCatalogo_TipoPersona();
		 for( ItemCat i : ec.getItems() ) {
			 if( i.getKey().equalsIgnoreCase( p.getTipoPersona() )  ) {
				 vtp =  true;
			 }
		 }
		 ec = catalogoClientService.getCatalogo_TipoIdentificacion();
		 for( ItemCat i : ec.getItems() ) {
			 if( i.getKey().equalsIgnoreCase( p.getTipoIdentificacion() )  ) {
				 vti =  true;
			 }
		 }
		 
		 return vec && vtp && vti;
	}

	public List<String> getcatalogos() throws Exception {
		// TODO Auto-generated method stub
		return catalogoClientService.getcatalogos();
	}



}
