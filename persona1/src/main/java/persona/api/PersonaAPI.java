package persona.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import persona.dao.Persona;
import persona.util.GenericResponseSearch;
import persona.util.MyUtil;

@Log4j2
@Api(value = "Api  Personas")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/personas")
public class PersonaAPI {
	
	@Autowired
	PersonaService personaService;
	
	@ApiOperation(value = "Buscar Personas con Paginacion y ordenacion disponibles")
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "debug", allowEmptyValue = true, example = "0,1"),
			@ApiImplicitParam(name = "page", allowEmptyValue = true),
			@ApiImplicitParam(name = "size", allowEmptyValue = true) })
	@ResponseBody
	public ResponseEntity<GenericResponseSearch<List<Persona>>> searchPagination(
			@Validated @RequestBody @ApiParam(value = "Datos de Personas a Buscar", required = true) //
			Persona r, //
			HttpServletRequest httpRequest) {
		//
		long startTransactionTime = System.currentTimeMillis();
		HttpHeaders headers = new HttpHeaders();
		String transactionId = MyUtil.generateUID();
		String user = "TEST-USER-01";
		GenericResponseSearch<List<Persona>> response = new GenericResponseSearch<>();
		try {

			List<Persona> results = personaService.queryList(r.getIdentificacion(), //
					r.getEstado(), //
					"");
			response.setMessage("EXITO");
			response.setCode(200);
			response.setResult(results);
			headers.add("transaction_id", transactionId);
			headers.add("transaction_time", totalTime(startTransactionTime));
			return new ResponseEntity<>(response, HttpStatus.OK);

		}  catch (Exception e) {
			response.setCode(500);
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {

			MyUtil.addTransactionTime(startTransactionTime);
			
			long totalTime = System.currentTimeMillis() - startTransactionTime;
			log.info("totalTxTime", "" + totalTime);
		}
	}

	
	protected String totalTime(long init) {
		return "" + (System.currentTimeMillis() - init);
	}
}
