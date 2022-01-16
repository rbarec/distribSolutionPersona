package persona.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import persona.util.CatalogoDTO;
import persona.util.CatalogoDTO.ItemCat;



@Service
public class CatalogoClientService {

//	@Autowired
//	@Qualifier("queryTemplate")
//	private RedisTemplate<String, List<Object>> template;
	
	@Value("${invoke_catalog.estado_civil}")
	String rutaEstadoCivil;

	@Value("${invoke_catalog.tipo_persona}")
	String rutaTipoPersona;

	@Value("${invoke_catalog.tipo_identificacion}")
	String rutaTipoIdentificacion;

	@Autowired
	protected RestTemplate restTemplate;
	
	
	public List<String> getcatalogos() throws Exception {
		List<String> a = new ArrayList<>();
		CatalogoDTO a1 = getCatalogo_EstadosCivil();
		if (a1 == null || a1.getItems() == null) {
			a.add("Error Estado Civil  - null");
		} else {
			for (ItemCat x : a1.getItems()) {
				a.add("estadoCivil:  " + x.getKey());
			}
		}
		a1 = getCatalogo_TipoPersona();
		if (a1 == null || a1.getItems() == null) {
			a.add("Error TipoPersona  - null");
		} else {
			for (ItemCat x : a1.getItems()) {
				a.add("TipoPersona:  " + x.getKey());
			}
		}
		a1 = getCatalogo_TipoIdentificacion();
		if (a1 == null || a1.getItems() == null) {
			a.add("Error TipoIdentificacion  - null");
		} else {
			for (ItemCat x : a1.getItems()) {
				a.add("TipoIdentificacion:  " + x.getKey());
			}
		}
		return a;
	}

	
	public CatalogoDTO getCatalogo_EstadosCivil() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set("Content-Type", "application/json");

		String val = restTemplate.getForObject(rutaEstadoCivil, String.class);
		System.out.println(val);
		CatalogoDTO convertedObject = new Gson().fromJson(val, CatalogoDTO.class);
		System.out.println(convertedObject);
		
		CatalogoDTO json = restTemplate.getForObject(rutaEstadoCivil, CatalogoDTO.class);
		
		System.out.println(json);

		return json;

	}

	
	public CatalogoDTO getCatalogo_TipoPersona() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set("Content-Type", "application/json");

		CatalogoDTO json = restTemplate.getForObject(rutaTipoPersona, CatalogoDTO.class);
		System.out.println(json);

		return json;
	}

	
	public CatalogoDTO getCatalogo_TipoIdentificacion() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set("Content-Type", "application/json");

		CatalogoDTO json = restTemplate.getForObject(rutaTipoIdentificacion, CatalogoDTO.class);
		System.out.println(json);

		return json;
	}
	
}
