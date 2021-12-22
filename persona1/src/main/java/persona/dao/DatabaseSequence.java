package persona.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@TypeAlias("DatabaseSequence")
@EqualsAndHashCode(exclude = { "id" })
@ApiModel(description = "Secuencias del sistema", value = "DatabaseSequence")
@Document(collection = "database_sequences")
public class DatabaseSequence {
	@Id
	private String id;

	private long seq;
}
