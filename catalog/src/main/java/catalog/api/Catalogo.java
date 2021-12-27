package catalog.api;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import catalog.util.ItemCat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "adm_catalogos")
@JsonInclude(Include.ALWAYS)
@TypeAlias("Catalogo")
@EqualsAndHashCode(exclude = { "id" })
@ApiModel(description = "Catalogo del sistema", value = "Catalogo")
public class Catalogo implements Serializable {

	private static final long serialVersionUID = -1515550150367L;
	@Id
	private Long id;

	@Field(value = "codigo")
	@ApiModelProperty(value = "codigo unico de catalogo por Instancia de cliente")
	private String codigo;

	@Field(value = "items")
	@ApiModelProperty(value = "Items, lista de valores que dan razon al catalogo")
	private List<ItemCat> items;


}
