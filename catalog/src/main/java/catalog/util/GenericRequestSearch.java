package catalog.util;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GenericRequestSearch<T> implements Serializable {

	private static final long serialVersionUID = 8805902241936110751L;

	// LIKE
	@Field(value = "filter")
	private String filter;
	@Field(value = "estado")
	private String estado;
	@Field(value = "id")
	private Long id;

	@Field(value = "fecha1_desde")
	private Date fecha1Desde;
	@Field(value = "fecha1_hasta")
	private Date fecha1Hasta;

	private T search;


	
//	@JsonIgnore
//	public int getPageNumber_OrDefault() {
//		return ppage > 0 ? ppage : 0;
//	}
//
//	@JsonIgnore
//	public int getSizeNumber_OrDefault() {
//		return psize > 0 ? psize : 10;
//	}
	
	public GenericRequestSearch( T instance) {
		this.search = instance;
	}

	public GenericRequestSearch() {
		super();
	}

}