package catalog.util;

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
public class GenericResponseSearch<T> implements Serializable {
	

	private static final long serialVersionUID = 8805902241936110751L;
	
	private long code;
	private String message;
	private T result;
	


	
	public GenericResponseSearch(T result) {
		this( -1L, null, result);
	}
	
	public GenericResponseSearch( String message, T result) {
		this( -1L, message, result);
	}
	
	
}