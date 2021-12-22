package persona.dao;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Personas del sistema ", value = "Persona")
public class Persona implements Serializable {
	private static final long serialVersionUID = 151708330150367L;

	@Id
	@Field
	private Long id;

	@Field(value = "tipo_persona")
	@ApiModelProperty(value = "tipo Persona natural o Juridica")
	private String tipoPersona;

	@Field(value = "tipo_identificacion")
	@ApiModelProperty(value = "Tipo Identificacion ced(ula) ruc pas(pasaporte) pla(ca) noi(dentificacion)")
	private String tipoIdentificacion;

	@Field(value = "identificacion")
	@ApiModelProperty(value = "Numero de Identificacion ")
	private String identificacion;

	@Field(value = "nombres")
	@ApiModelProperty(value = "Dos nombres completos")
	private String nombres;
	//
	@Field(value = "apellidos")
	@ApiModelProperty(value = "Dos apellidos")
	private String apellidos;

	@Field(value = "extranjero")
	@ApiModelProperty(value = "extranjero")
	private boolean extranjero;

	@Field(value = "genero")
	@ApiModelProperty(value = "genero MAS FEM IND")
	private String genero;

	@Field(value = "estado_civil")
	@ApiModelProperty(value = "estadoCivil")
	private String estadoCivil;

	@Field(value = "estado")
	@ApiModelProperty(value = "estado")
	private String estado;

	@Field(value = "direccion")
	@ApiModelProperty(value = "direccion")
	private String direccion;

	@Field(value = "correo")
	@ApiModelProperty(value = "correo")
	private String correo;

	@Field(value = "nacionalidad")
	@ApiModelProperty(value = "nacionalidad")
	private String nacionalidad;

}
