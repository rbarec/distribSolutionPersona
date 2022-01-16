package catalog.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import catalog.util.GenericResponse;
import catalog.util.MyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Api(value = "Conocimiento Base Consultas API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class KnobaseAPI {

	@Autowired
	CatalogoService myService;


	@ApiOperation(value = "Esta operacion obtiene Contexto y Grupo por codigo.")
	@GetMapping("/catalogos/{contexto}/grupo/{grupo}")
	public ResponseEntity<Catalogo> getCatalogoByCodigo( //
			@PathVariable @Validated String contexto //
			, @PathVariable @Validated String grupo //
			, HttpServletRequest request //
	) {
		Catalogo response = null;
		try {
			Catalogo obj = myService.getConocimientoContextoGrupo(contexto, grupo);
			return new ResponseEntity<>(obj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
		}
	}

}
