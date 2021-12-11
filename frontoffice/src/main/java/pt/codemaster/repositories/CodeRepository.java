package pt.codemaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.codemaster.adt.Code;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
}
