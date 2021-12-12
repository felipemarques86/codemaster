package pt.codemaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.codemaster.adt.Deliverable;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {
}
