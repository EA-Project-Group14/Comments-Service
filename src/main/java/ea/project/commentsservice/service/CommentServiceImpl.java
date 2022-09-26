package ea.project.commentsservice.service;

import ea.project.commentsservice.domain.Comment;
import ea.project.commentsservice.dto.CommentDto;
import ea.project.commentsservice.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public void save(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .comment(commentDto.getComment())
                .postId(commentDto.getPostId())
                .createDate(LocalDate.now().toString())
                .build();
        commentsRepository.save(comment);
    }

    @Override
    public List<Comment> getAllCommentFromPost(Long postId) {
        return commentsRepository.findByPostId(postId);
    }

    @Override
    public void delete(Long commentId) {
       commentsRepository.deleteById(commentId);
    }

    @Override
    public Comment update(Long commentId, CommentDto commentDto) {
        Comment comment = commentsRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("No comment found with id: " + commentId));
        comment.setComment(commentDto.getComment());
        comment.setPostId(commentDto.getPostId());
        commentsRepository.save(comment);
        return comment;
    }
}
