package zw.co.afrocodemy.comment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.DtoMapper;
import zw.co.afrocodemy.comment.Comment;
import zw.co.afrocodemy.comment.CommentReply;
import zw.co.afrocodemy.comment.CommentRepository;
import zw.co.afrocodemy.comment.CommentService;
import zw.co.afrocodemy.comment.dto.CommentDto;
import zw.co.afrocodemy.comment.dto.CommentReplyDto;
import zw.co.afrocodemy.post.PostRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final DtoMapper mapper;

    @Override
    public ResponseEntity<?> createPostComment(Long postId, CommentDto commentDto) {
        if(! Objects.equals(postId, commentDto.getPostId())){
            return new ResponseEntity<>("Post IDs do not match", HttpStatus.BAD_REQUEST);
        }
        if(! postRepository.existsById(postId)){
            return new ResponseEntity<>("Referenced post does not exist", HttpStatus.BAD_REQUEST);
        }
        Comment comment = mapper.dtoToComment(commentDto);
        return ResponseEntity.ok(commentRepository.save(comment));
    }

    @Override
    public ResponseEntity<?> createCommentReply(Long postId, Long commentId, CommentReplyDto commentReplyDto) {
        if(! Objects.equals(postId, commentReplyDto.getPostId())){
            return new ResponseEntity<>("Post IDs do not match", HttpStatus.BAD_REQUEST);
        }
        if(! postRepository.existsById(postId)){
            return new ResponseEntity<>("Referenced post does not exist", HttpStatus.BAD_REQUEST);
        }
        if(! commentRepository.existsById(commentId)){
            return new ResponseEntity<>("Referenced parent comment does not exist", HttpStatus.BAD_REQUEST);
        }
        CommentReply commentReply = mapper.dtoToCommentReply(commentReplyDto);
        return ResponseEntity.ok(commentRepository.save(commentReply));
    }

    @Override
    public ResponseEntity<?> getPostComments(Long postId) throws Exception {
        if(! postRepository.existsById(postId)){
            return new ResponseEntity<>("Referenced post does not exist", HttpStatus.BAD_REQUEST);
        }
        List<Comment> comments = commentRepository.findAllByPostId(postId).orElse(null);
        List<Object> convertedComments = comments.stream()
                .map(comment -> comment.getClass().equals(CommentReply.class) ? mapper.commentReplyToDto(comment) :
                        mapper.commentToDto(comment))
                .collect(Collectors.toList());
        return ResponseEntity.ok(convertedComments);
    }

    @Override
    public ResponseEntity<?> delete(Long postId, Long commentId) {
        if(! postRepository.existsById(postId)){
            return new ResponseEntity<>("Referenced post does not exist", HttpStatus.BAD_REQUEST);
        }
        Optional<Comment> comment = commentRepository.findByIdAndPostId(commentId, postId);
        if(comment.isPresent()){
            commentRepository.delete(comment.get());
            return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Comment with id " + commentId + " and postId " + postId + " not found",
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> update(Long postId, Long commentId, CommentDto comment) {
        if(! postRepository.existsById(postId)){
            return new ResponseEntity<>("Referenced post does not exist", HttpStatus.BAD_REQUEST);
        }
        if(! commentRepository.existsById(commentId)){
            return new ResponseEntity<>("Referenced comment does not exist", HttpStatus.BAD_REQUEST);
        }
        if(! Objects.equals(postId, comment.getPostId())){
            return new ResponseEntity<>("Post IDs do not match", HttpStatus.BAD_REQUEST);
        }
        if(! Objects.equals(commentId, comment.getId())){
            return new ResponseEntity<>("Comment IDs do not match", HttpStatus.BAD_REQUEST);
        }
        Comment commentToUpdate = mapper.dtoToComment(comment);
        return ResponseEntity.ok(commentRepository.save(commentToUpdate));
    }
}
