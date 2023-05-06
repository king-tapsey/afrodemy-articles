package zw.co.afrocodemy.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.post.dto.PostDto;


public interface PostService {
    ResponseEntity<?> createPost(PostDto postDto);
    ResponseEntity<?> updatePost(PostDto postDto);
    ResponseEntity<?> approvePost(String approverUsername, Long postId);
    ResponseEntity<?> getPost(Long postId);
    ResponseEntity<Page<PostDto>> getPosts(Pageable pageable);
    ResponseEntity<?> getAllPosts();
    ResponseEntity<?> deletePost(Long postId);
}
