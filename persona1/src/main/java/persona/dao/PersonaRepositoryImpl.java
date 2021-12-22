package persona.dao;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.MongoRegexCreator.MatchMode;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PersonaRepositoryImpl implements PersonaRepositoryCustom {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public PersonaRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	

	@Override
	public List<Persona> queryList(//
			String identificacion, String estado, String filtro) throws Exception {

		Query query = new Query();
		List<Criteria> cList = new ArrayList<>();
		if (estado != null) {
			Criteria c = Criteria.where("estado").is(estado);
			cList.add(c);
		}
		if (identificacion != null) {
			Criteria c2 = Criteria.where("identificacion").is(identificacion);
			cList.add(c2);
		}
		if (filtro != null) {
			Criteria cri = Criteria.where("search")
					.regex(MongoRegexCreator.INSTANCE.toRegularExpression(filtro, MatchMode.CONTAINING), "i");
			cList.add(cri);
		}
		if (!cList.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(//
					cList.toArray(new Criteria[cList.size()])));
		}
		List<Persona> lista = mongoTemplate.find(query, Persona.class);
		return lista;
	}

	@Override
	public void getSequence(Persona obj) {
		Query query = new Query();
		final List<Criteria> criteriaList = new ArrayList<>();
		criteriaList.add(Criteria.where("_id").is("adm_personas" + "_sequence"));
		query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
		DatabaseSequence counter = mongoTemplate.findAndModify(query, new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), DatabaseSequence.class);
		obj.setId(!Objects.isNull(counter) ? counter.getSeq() : 1);
		return;

	}

}
