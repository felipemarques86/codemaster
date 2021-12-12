package pt.codemaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.codemaster.adt.ActivityInstance;
import pt.codemaster.adt.activity.Activity;

@Repository
public interface ActivityInstanceRepository extends JpaRepository<ActivityInstance, Long> {
}
