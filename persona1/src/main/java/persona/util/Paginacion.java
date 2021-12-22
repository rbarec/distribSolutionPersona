package persona.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.ALWAYS)
public class Paginacion {
	
	public enum DATOS{
		page,size,create,after_id,sort_by
	}
	
	public static String[] ATRIBUTOS = {"page","size","create","after_id", "sort_by"};
			
	private int page;
	private int size;
	// TODO PENDIENTE - https://www.moesif.com/blog/technical/api-design/REST-API-Design-Filtering-Sorting-and-Pagination/
	@JsonIgnore
	private String create;
	@JsonIgnore
	private Date dateCreate;
	@JsonIgnore
	private Long after_id;
	@JsonIgnore
	private String estado;
	@JsonIgnore
	private String  sort_by;

}
