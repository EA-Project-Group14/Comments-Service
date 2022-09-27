package ea.project.commentsservice.service;

import ea.project.commentsservice.domain.Comment;
import ea.project.commentsservice.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void save(CommentDto commentDto);
    List<Comment> getAllCommentFromPost(Long postId);
    void delete(Long commentId);
    Comment update(Long commentId, CommentDto comment);

    void deletePostComments(Long postId);
}
