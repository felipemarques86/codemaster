package pt.codemaster.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.activity.Activity;

@Repository
public interface ActivityInstanceRepository extends CrudRepository<ActivityInstance<Activity>, Long> {
}
