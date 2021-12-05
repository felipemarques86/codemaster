package pt.codemaster.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.codemaster.adt.activity.Activity;

@Repository
public interface ActivityDefinitionRepository extends CrudRepository<Activity, Long> {
}
