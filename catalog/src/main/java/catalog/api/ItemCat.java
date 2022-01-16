package catalog.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.ALWAYS)
public class ItemCat implements Serializable {

	private static final long serialVersionUID = 880222751L;

	// para identificar el ITEM y asi gestionarlos desde grupos
	private Long codigo;

	private String key; // Identificador Unico para identificar el Objeto
	private Object value; // Valor a Buscar
	private String tipo;

	public ItemCat(String clave, Object valor) {
		this.key = clave;
		this.value = valor;
	}

}