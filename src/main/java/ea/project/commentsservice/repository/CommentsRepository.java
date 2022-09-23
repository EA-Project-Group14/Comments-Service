package ea.project.commentsservice.repository;

import ea.project.commentsservice.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository< Comment, Integer> {
   List<Comment> findByPostId(int commentId);
   Comment deleteById(int id);
}