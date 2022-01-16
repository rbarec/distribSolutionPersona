package persona.util;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.ALWAYS)
public class CatalogoDTO implements Serializable {

	private static final long serialVersionUID = -151567650367L;
	private Long id;
	private String codigo;

	private List<ItemCat> items;


	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(Include.ALWAYS)
	public  static class ItemCat implements Serializable {

		private static final long serialVersionUID = 883332751L;
		private Long codigo;
		private String key; // Identificador Unico para identificar el Objeto
		private Object value; // Valor a Buscar
		private String tipo;
		
		public ItemCat(String clave, Object valor) {
			this.key = clave;
			this.value = valor;
		}

	}
}
