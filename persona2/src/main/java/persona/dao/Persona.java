package persona.dao;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * "_id": 7, "tipo_persona": "natural", "tipo_identificacion": "cedula",
 * "identificacion": "0922067715", "nombres": "Ronald", "apellidos": "Barrera
 * Larrea", "extranjero": false, "genero": "masculino", "estado_civil":
 * "casado", "correo": "rbar.ec@gmail.com", "estado": "activo", "direccion":
 * "Mucho Lote 2",
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "adm_personas")
@JsonInclude(Include.ALWAYS)
@TypeAlias("Persona")
@EqualsAndHashCode(exclude = { "id" })
public class Persona implements Serializable {
	private static final long serialVersionUID = 151708330150367L;

	@Id
	@Field
	private Long id;

	@Field(value = "tipo_persona")
	private String tipoPersona;

	@Field(value = "tipo_identificacion")
	private String tipoIdentificacion;

	@Field(value = "identificacion")
	private String identificacion;

	@Field(value = "nombres")
	private String nombres;
	//
	@Field(value = "apellidos")
	private String apellidos;

	@Field(value = "extranjero")
	private boolean extranjero;

	@Field(value = "genero")
	private String genero;

	@Field(value = "estado_civil")
	private String estadoCivil;

	@Field(value = "estado")
	private String estado;

	@Field(value = "direccion")
	private String direccion;

	@Field(value = "correo")
	private String correo;

	@Field(value = "nacionalidad")
	private String nacionalidad;

}
