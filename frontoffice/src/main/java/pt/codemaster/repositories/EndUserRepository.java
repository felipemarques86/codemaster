package pt.codemaster.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.codemaster.adt.EndUser;

@Repository
public interface EndUserRepository extends CrudRepository<EndUser, String> {
}
