package zw.co.afrocodemy.afrocodemyarticles.post;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrocodemy.afrocodemyarticles.comment.CommentService;
import zw.co.afrocodemy.afrocodemyarticles.post.dto.PostDto;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @Operation(summary = "Get all posts")
    @GetMapping("/post/all")
    public ResponseEntity<?> getAllPosts(){
        return postService.getAllPosts();
    }
    @GetMapping("/post")
    public ResponseEntity<Page<PostDto>> getPosts(@RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return postService.getPosts(pageable);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }
    @PostMapping("/post/{postId}/approve")
    public ResponseEntity<?> approvePost(@PathVariable Long postId, @RequestParam String approverUsername){
        return postService.approvePost(approverUsername, postId);
    }
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostDto post){
        return postService.createPost(post);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostDto post){
        if(! postId.equals(post.getId())){
            return new ResponseEntity<>("Post IDs do not match", HttpStatus.BAD_REQUEST);
        }
        return postService.updatePost(post);
    }
}
