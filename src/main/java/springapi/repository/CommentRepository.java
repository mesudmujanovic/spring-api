package springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springapi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
