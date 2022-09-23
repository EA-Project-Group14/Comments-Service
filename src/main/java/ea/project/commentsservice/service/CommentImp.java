package ea.project.commentsservice.service;

import ea.project.commentsservice.domain.Comment;
import ea.project.commentsservice.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CommentImp implements CommentSer{

    @Autowired
    CommentsRepository commentsRepository;
    @Override
    public void save(Comment comment) {
        System.out.println("this is add");
        Date now = Date.valueOf(LocalDate.now());
        comment.setCreateDate(now);
        Comment comment1 = commentsRepository.save(comment);

    }

    @Override
    public List<Comment> getAllCommentFromPost(int postId) {
        List<Comment> comments = commentsRepository.findByPostId(postId);
        return comments;
    }

    @Override
    public void delete(int commentId) {
       commentsRepository.deleteById(commentId);
    }
    @Override
    public void deleteAllByPostId(int postId){
        commentsRepository.deleteAllByPostId(postId);
    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }
}
