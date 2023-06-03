package zw.co.afrocodemy.afrocodemyarticles.post.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyarticles.DtoMapper;
import zw.co.afrocodemy.afrocodemyarticles.comment.CommentRepository;
import zw.co.afrocodemy.afrocodemyarticles.post.Post;
import zw.co.afrocodemy.afrocodemyarticles.post.PostRepository;
import zw.co.afrocodemy.afrocodemyarticles.post.PostService;
import zw.co.afrocodemy.afrocodemyarticles.post.dto.PostDto;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final DtoMapper mapper;

    @Override
    public ResponseEntity<?> createPost(PostDto postDto) {
        Post post = mapper.dtoToPost(postDto);
        post.setId(null);
        return ResponseEntity.ok(postRepository.save(post));
    }

    @Override
    public ResponseEntity<?> updatePost(PostDto postDto){
        if(! postRepository.existsById(postDto.getId()) ){
            return new ResponseEntity<>("Post does not exist", HttpStatus.BAD_REQUEST);
        }

        Post post = mapper.dtoToPost(postDto);

        if(post.getModifierUsername().isEmpty()){
            post.setModifierUsername(post.getCreatorUsername());
        }
        if(Objects.isNull(post.getModifiedDate())){
            post.setModifiedDate(LocalDateTime.now());
        }

        return ResponseEntity.ok(postRepository.save(post));
    }

    @Override
    public ResponseEntity<?> approvePost(String approverUsername, Long postId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            return ResponseEntity.ok(mapper.postToDto(post.get()));
        }
        return new ResponseEntity<>("Post with id: " + postId + " not found", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Page<PostDto>> getPosts(Pageable pageable) {
        Page<PostDto> posts = postRepository.findAll(pageable).map(mapper::postToDto);
        return ResponseEntity.ok(posts);
    }

    @Override
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @Override
    public ResponseEntity<?> deletePost(Long postId) {
        if(! postRepository.existsById(postId)){
            return new ResponseEntity<>("Requested post not found", HttpStatus.BAD_REQUEST);
        }
        postRepository.deleteById(postId);
        return ResponseEntity.ok("Post with id: " + postId + " successfully deleted");
    }
}
