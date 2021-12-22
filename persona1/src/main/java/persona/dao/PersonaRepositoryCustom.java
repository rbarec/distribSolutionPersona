package persona.dao;

import java.util.List;

public interface PersonaRepositoryCustom {
	
	

	public List<Persona> queryList(//
			String  identificacion, String estado, String filtro) throws Exception;

	
	public void getSequence(Persona obj);
}
