package ea.project.commentsservice.controller;


import ea.project.commentsservice.domain.Comment;
import ea.project.commentsservice.dto.CommentDto;
import ea.project.commentsservice.repository.CommentsRepository;
import ea.project.commentsservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @Autowired
    CommentsRepository commentsRepository;

    @GetMapping(params = "postId")
    public List<Comment> getCommentsByPostId(Long postId) {
       return commentService.getAllCommentFromPost(postId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        return commentService.update(commentId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);
    }
    
    @DeleteMapping("/comments/delete/all/{postId}")
    @Transactional
    public void deleteCommentsByPostId(@PathVariable("postId") int postId) {
        commentSer.deleteAllByPostId(postId);
    }

    @PostMapping
    public void addComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
    }

    @FeignClient("PostsSrvice")
    interface AccountFeignClient {
        @RequestMapping("/post/comment/{postid}")
        public List<Comment> getCommentsByPostId(@PathVariable("postid") int postid);
    }
}
