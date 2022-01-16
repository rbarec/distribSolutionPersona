package persona.api;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;
import persona.dao.Persona;
import persona.dao.PersonaRepository;
import persona.pool.JedisUtil;
import persona.util.CatalogoDTO;
import persona.util.GenericResponse;
import persona.util.GenericResponseSearch;
import persona.util.MyUtil;
import persona.util.RandomNumbers;
import persona.util.CatalogoDTO.ItemCat;

@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/personas")
public class PersonaAPI {

	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	CatalogoClientService catalogoClientService;

	@Autowired
	private JedisUtil jedisUtil;

	@GetMapping("/random")
	@ResponseBody
	public ResponseEntity<GenericResponse<Persona>> allRandom_Personas(//
			HttpServletRequest request) {
		//
		long startTransactionTime = System.currentTimeMillis();
		HttpHeaders headers = new HttpHeaders();
		String transactionId = MyUtil.generateUID();
		GenericResponse<Persona> response = new GenericResponse<>();
		try {
			RandomNumbers prng = new RandomNumbers();
			Long l2 = prng.range(1, 2001);
			String key = "personaID-" + l2;
			boolean existeCache = jedisUtil.exists(key);
			if (existeCache) {
				String strobj = jedisUtil.get(key);
				Persona dataPersona = new Gson().fromJson(strobj, Persona.class);
				System.out.println("CACHE object ....:   " + dataPersona);
				dataPersona.setCache(true);
				response.setResult(dataPersona);
				response.setMessage("EXITO ");
				response.setCode(200);
			} else {
				Optional<Persona> p = personaRepository.findById(l2);
				if (p.isPresent()) {
					System.out.println("DB object ....:   " + p);
					validarPersona(p.get());
					String out = new Gson().toJson(p);
					jedisUtil.set(key, out);
					p.get().setCache(true);
					response.setResult(p.get());
					response.setMessage("EXITO ");
					response.setCode(200);
				}
			}
			response.setTime(totalTime(startTransactionTime));
			headers.add("transaction_id", transactionId);
			headers.add("transaction_time", totalTime(startTransactionTime));
			return new ResponseEntity<>(response, headers, HttpStatus.OK);
			//
		} catch (Exception e) {
			//
			log.error(e);
			headers.add("transaction_id", transactionId);
			headers.add("transaction_time", totalTime(startTransactionTime));
			return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean validarPersona(Persona p) throws Exception {
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

	protected String totalTime(long init) {
		return "" + (System.currentTimeMillis() - init);
	}

}
