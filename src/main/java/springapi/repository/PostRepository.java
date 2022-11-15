package springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springapi.entity.Post;

public interface PostRepository extends JpaRepository <Post,Long> {

}
