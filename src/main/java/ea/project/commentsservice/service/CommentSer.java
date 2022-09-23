package ea.project.commentsservice.service;

import ea.project.commentsservice.domain.Comment;

import java.util.List;

public interface CommentSer {
    void save(Comment comment);
    List<Comment> getAllCommentFromPost(int postId);
    void delete(int commentId);
    Comment update(Comment comment);
}
