package zw.co.afrocodemy.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrocodemy.comment.dto.CommentDto;
import zw.co.afrocodemy.comment.dto.CommentReplyDto;
import zw.co.afrocodemy.post.PostService;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<?> createComment(@PathVariable Long postId, CommentDto comment){
        return commentService.createPostComment(postId, comment);
    }
    @PostMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<?> createCommentReply(@PathVariable Long postId, @PathVariable Long commentId,
                                             CommentReplyDto comment){
        return commentService.createCommentReply(postId, commentId, comment);
    }
    @GetMapping("/post/{postId}/comment")
    public ResponseEntity<?> getAllPostComments(@PathVariable Long postId) throws Exception {
        return commentService.getPostComments(postId);
    }
    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        return commentService.delete(postId, commentId);
    }
    @PutMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long postId, @PathVariable Long commentId, CommentDto comment){
        return commentService.update(postId, commentId, comment);
    }
}
