package zw.co.afrocodemy.comment;

import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.comment.dto.CommentDto;
import zw.co.afrocodemy.comment.dto.CommentReplyDto;

public interface CommentService {
    ResponseEntity<?> createPostComment(Long postId, CommentDto commentDto);
    ResponseEntity<?> createCommentReply(Long postId, Long commentId, CommentReplyDto commentDto);
    ResponseEntity<?> getPostComments(Long postId) throws Exception;
    ResponseEntity<?> delete(Long postId, Long commentId);
    ResponseEntity<?> update(Long postId, Long commentId, CommentDto comment);
}
