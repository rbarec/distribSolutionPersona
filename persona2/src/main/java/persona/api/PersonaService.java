package persona.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import persona.dao.Persona;
import persona.dao.PersonaRepository;
import persona.util.CatalogoDTO;
import persona.util.CatalogoDTO.ItemCat;

@Log4j2
//@CacheConfig(cacheNames = "catalogo")
@Service
public class PersonaService {

	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	CatalogoClientService catalogoClientService;
	
//	@Autowired
//	CacheManagementService cacheManagementService;

//	@Autowired
//	@Qualifier("queryTemplate")
//	private RedisTemplate<String, List<Object>> template;

	public Persona getRandomPersona() throws Exception {
		int id = getRandom(1, 2000);
		System.out.println(" RANDOM >>>>>" + id);
		long l = id;
		Long l2 = Long.valueOf(l);
		Persona pcache = getFromCache(l2);
		if (pcache != null) {
			pcache.setCache(true);
			return pcache;
		} else {

			Optional<Persona> p = personaRepository.findById(l2);
			if (p.isPresent()) {
				validarPersona(p.get());
				toCache(p.get());
				return p.get();
			} else {
//				int id2 = getRandom(1, 2000);
//				long l2x = id2;
//				Long l22 = Long.valueOf(l2x);
//				Optional<Persona> p2 = personaRepository.findById(l22);
//				validarPersona(p2.get());
//				return p2.isPresent() ? p2.get() : null;
				return null;
			}
		}
	}

	public Persona apiGetPersonaByIdentificacion(String identificacion) throws Exception {
		Optional<Persona> per = personaRepository.findByIdentificacion(identificacion);
		if (per.isPresent()) {
			if (validarPersona(per.get())) {
				toCache(per.get());
				return per.get();
			}
		}
		return null;
	}

	public Persona apiGetPersonaById(Long idPersona) throws Exception {
		Persona pcache = getFromCache(idPersona);
		if (pcache != null) {
			pcache.setCache(true);
			return pcache;
		} else {
			Optional<Persona> per = personaRepository.findById(idPersona);
			if (per.isPresent()) {
				validarPersona(per.get());
				toCache(per.get());
				return per.get();
			}
			return null;
		}
	}

	private boolean toCache(Persona per) {
//		cacheManagementService.putItem("persona", "personaID-" + per.getId(), per);
		
//		RedisConnection conn = template.getConnectionFactory().getConnection();
//		try {
//			String key = "personaID-" + per.getId();
//			String out = new Gson().toJson(per);
//			conn.append(key.getBytes(), out.getBytes());
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}

	private Persona getFromCache(Long l2) {
//		Object o = cacheManagementService.getItem("persona", "personaID-" + l2);
	//	if(o!=null) return (Persona)o;
		return null;
//		String key = "personaID-" + l2;
//		RedisConnection conn = template.getConnectionFactory().getConnection();
//		try {
//		boolean existeKey = conn.exists(key.getBytes());
//		System.out.println("la " + key + " existe?   " + existeKey);
//		if (existeKey) {
//			byte[] data = conn.get(key.getBytes());
//			if (data != null) {
//				String jsondata = new String(data, StandardCharsets.UTF_8);
//				Persona dataPersona = new Gson().fromJson(jsondata, Persona.class);
//				System.out.println("CACHE object ....:   " + dataPersona);
//				conn.close();
//				return dataPersona;
//			} 
//		}
//		}catch(Exception e) {
//			conn.close();
//		}
//		return null;
	}

	private byte[] tobytes(Object yourObject) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(yourObject);
			out.flush();
//		  byte[] yourBytes = bos.toByteArray();
			return bos.toByteArray();
		} finally {
			try {
				bos.close();
			} catch (IOException ex) {
				// ignore close exception
			}
		}
	}

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
			if (i.getTipoIdentificacion() != null) {
				String t = i.getTipoIdentificacion();
				i.setTipoIdentificacion(t.toLowerCase());
			}
			if (i.getTipoPersona() != null) {
				String t = i.getTipoPersona();
				i.setTipoPersona(t.toLowerCase());
			}
			personaRepository.save(i);
			r.add("" + i.getIdentificacion() + " " + i.getNombres() + " " + i.getApellidos() + " " + i.getEstadoCivil()
					+ " " + i.getTipoPersona() + " " + i.getTipoIdentificacion());
		}
		return r;
	}

	public boolean validarPersona(Persona p) throws Exception {
		CatalogoDTO ec = catalogoClientService.getCatalogo_EstadosCivil();
		boolean vec = false;
		boolean vtp = false;
		boolean vti = false;

		for (ItemCat i : ec.getItems()) {
			if (i.getKey().equalsIgnoreCase(p.getEstadoCivil())) {
				vec = true;
			}
		}
		ec = catalogoClientService.getCatalogo_TipoPersona();
		for (ItemCat i : ec.getItems()) {
			if (i.getKey().equalsIgnoreCase(p.getTipoPersona())) {
				vtp = true;
			}
		}
		ec = catalogoClientService.getCatalogo_TipoIdentificacion();
		for (ItemCat i : ec.getItems()) {
			if (i.getKey().equalsIgnoreCase(p.getTipoIdentificacion())) {
				vti = true;
			}
		}
		return vec && vtp && vti;
	}

	public List<String> getcatalogos() throws Exception {
		// TODO Auto-generated method stub
		return catalogoClientService.getcatalogos();
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

}
