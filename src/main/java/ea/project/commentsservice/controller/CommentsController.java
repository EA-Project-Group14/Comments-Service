package ea.project.commentsservice.controller;


import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import ea.project.commentsservice.domain.Comment;
import ea.project.commentsservice.repository.CommentsRepository;
import ea.project.commentsservice.service.CommentSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentSer commentSer;

    @Autowired
    CommentsRepository commentsRepository;


    @GetMapping("/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") Integer postId) {
       return commentSer.getAllCommentFromPost(postId);
    }


    @PostMapping("/update")
    public Comment updateComment(@RequestBody Comment comment) {
        return commentSer.update(comment);
    }


    @DeleteMapping("/delete/{commentid}")
    public void deleteComment(@PathVariable("commentid") int commentid) {
        commentSer.delete(commentid);
    }

    @PostMapping("/add")
    public void addComment(@RequestBody Comment comment) {
        commentSer.save(comment);
    }


    @FeignClient("PostsSrvice")
    interface AccountFeignClient {
        @RequestMapping("/post/comment/{postid}")
        public List<Comment> getCommentsByPostId(@PathVariable("postid") int postid);
    }
}
