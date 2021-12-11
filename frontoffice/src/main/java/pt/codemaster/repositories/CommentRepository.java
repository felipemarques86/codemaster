package pt.codemaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.codemaster.adt.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
