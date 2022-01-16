package persona.api;
//package persona.api;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import lombok.extern.log4j.Log4j2;
//import persona.dao.Persona;
//import persona.util.GenericResponse;
//import persona.util.GenericResponseSearch;
//import persona.util.MyUtil;
//
//@Log4j2
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/api/personas")
//public class PersonaAPI {
//
//	@Autowired
//	PersonaService personaService;
//
//	@Autowired
//	@Qualifier("queryTemplate")
//	private RedisTemplate<String, List<Object>> template;
//
//
//
//	@Value("${micro-eis.timetolive.unit}")
//	private String ttlUnit;
//
//	@Value("${micro-eis.timetolive.value}")
//	private Integer ttlValue;
//
//	@GetMapping("/random")
//	@ResponseBody
//	public ResponseEntity<GenericResponse<Persona>> allRandom_Personas(//
//			HttpServletRequest request) {
//		//
//		long startTransactionTime = System.currentTimeMillis();
//		HttpHeaders headers = new HttpHeaders();
//		String transactionId = MyUtil.generateUID();
//		GenericResponse<Persona> response = new GenericResponse<>();
//		try {
//			Persona res = personaService.getRandomPersona();
//			response.setResult(res);
//			response.setMessage("EXITO " );
//			response.setCode(200);
//			response.setTime(totalTime(startTransactionTime));
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, headers, HttpStatus.OK);
//			//
//		} catch (Exception e) {
//			//
//			log.error(e);
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@GetMapping("/identificacion/{identificacion}")
//	@ResponseBody
//	public ResponseEntity<GenericResponse<Persona>> allRandom_Personas(//
//			@PathVariable @Validated String identificacion, //
//			HttpServletRequest request) {
//		//
//		long startTransactionTime = System.currentTimeMillis();
//		HttpHeaders headers = new HttpHeaders();
//		String transactionId = MyUtil.generateUID();
//		GenericResponse<Persona> response = new GenericResponse<>();
//		try {
//			Persona res = personaService.apiGetPersonaByIdentificacion(identificacion);
//			response.setResult(res);
//			response.setMessage("EXITO ");
//			response.setCode(200);
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			response.setTime(totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, headers, HttpStatus.OK);
//			//
//		} catch (Exception e) {
//			//
//			log.error(e);
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//
//	
//	
//	
//	
//	
//	@GetMapping("/id/{id}")
//	@ResponseBody
//	public ResponseEntity<GenericResponse<Persona>> getById(//
//			@PathVariable @Validated Long id, //
//			HttpServletRequest request) {
//		//
//		long startTransactionTime = System.currentTimeMillis();
//		HttpHeaders headers = new HttpHeaders();
//		String transactionId = MyUtil.generateUID();
//		GenericResponse<Persona> response = new GenericResponse<>();
//		try {
//			Persona res = personaService.apiGetPersonaById(id);
//			response.setResult(res);
//			response.setMessage("EXITO ");
//			response.setCode(200);
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			response.setTime(totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, headers, HttpStatus.OK);
//			//
//		} catch (Exception e) {
//			//
//			log.error(e);
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//	@GetMapping("/catalogos")
//	@ResponseBody
//	public ResponseEntity<GenericResponse<List<String>>> allcatalogos(//
//			HttpServletRequest request) {
//		//
//		long startTransactionTime = System.currentTimeMillis();
//		HttpHeaders headers = new HttpHeaders();
//		String transactionId = MyUtil.generateUID();
//		GenericResponse<List<String>> response = new GenericResponse<>();
//		try {
//			List<String> res = personaService.getcatalogos();
//			response.setResult(res);
//			response.setMessage("EXITO");
//			response.setCode(200);
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, HttpStatus.OK);
//			//
//		} catch (Exception e) {
//			//
//			log.error(e);
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
//	@ResponseBody
//	public ResponseEntity<GenericResponseSearch<List<Persona>>> searchPagination(@Validated @RequestBody //
//	Persona r, //
//			HttpServletRequest httpRequest) {
//		//
//		long startTransactionTime = System.currentTimeMillis();
//		HttpHeaders headers = new HttpHeaders();
//		String transactionId = MyUtil.generateUID();
//		String user = "TEST-USER-01";
//		GenericResponseSearch<List<Persona>> response = new GenericResponseSearch<>();
//		try {
//
//			List<Persona> results = personaService.queryList(r.getIdentificacion(), //
//					r.getEstado(), //
//					"");
//			response.setMessage("EXITO");
//			response.setCode(200);
//			response.setResult(results);
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, HttpStatus.OK);
//
//		} catch (Exception e) {
//			response.setCode(500);
//			response.setMessage(e.getMessage());
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		} finally {
//
//			MyUtil.addTransactionTime(startTransactionTime);
//
//			long totalTime = System.currentTimeMillis() - startTransactionTime;
//			log.info("totalTxTime", "" + totalTime);
//		}
//	}
//
//	@GetMapping("/all")
//	@ResponseBody
//	public ResponseEntity<GenericResponse<List<String>>> allPersonas(//
//			HttpServletRequest request) {
//		//
//		long startTransactionTime = System.currentTimeMillis();
//		HttpHeaders headers = new HttpHeaders();
//		String transactionId = MyUtil.generateUID();
//		GenericResponse<List<String>> response = new GenericResponse<>();
//		try {
//			List<String> res = personaService.findAll();
//			response.setResult(res);
//			response.setMessage("EXITO");
//			response.setCode(200);
//			headers.add("transaction_id", transactionId);
//			headers.add("transaction_time", totalTime(startTransactionTime));
//			return new ResponseEntity<>(response, HttpStatus.OK);
//			//
//		} catch (Exception e) {
//			//
//			log.error(e);
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	protected String totalTime(long init) {
//		return "" + (System.currentTimeMillis() - init);
//	}
//	
//	
//	
//	private Persona getObj(List<Object> obj) {
//		if(obj==null || obj.isEmpty()) {
//			log.error("Error obj conversion NULL ***************");
//		}
//		Object o = obj.get(0);
//		return (Persona)o;
//	}
//	
//	
//	private List<Object> getList(Persona p){
//		List<Object> arr = new ArrayList<>();
//		arr.add(p);
//		return arr;
//	}
//	
//	
//}
