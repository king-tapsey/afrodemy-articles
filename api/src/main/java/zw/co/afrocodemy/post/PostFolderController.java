package zw.co.afrocodemy.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrocodemy.post.dto.PostFolderDto;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class PostFolderController {
    private final PostFolderService postFolderService;

    @GetMapping("/post-folder")
    public ResponseEntity<?> getAll(){
        return postFolderService.getAll();
    }
    @GetMapping("/post-folder/{postFolderId}")
    public ResponseEntity<?> getPostFolder(@PathVariable Long postFolderId){
        return postFolderService.getById(postFolderId);
    }
    @PostMapping("/post-folder")
    public ResponseEntity<?> create(@RequestBody PostFolderDto postFolder){
        if(Objects.isNull(postFolder)){
            return new ResponseEntity<>("Cannot create a null object", HttpStatus.BAD_REQUEST);
        }
        return postFolderService.create(postFolder);
    }
    @PutMapping("/post-folder/{postFolderId}")
    public ResponseEntity<?> update(@PathVariable Long postFolderId, @RequestBody PostFolderDto postFolder){
        if(Objects.isNull(postFolder)){
            return new ResponseEntity<>("Cannot update a null object", HttpStatus.BAD_REQUEST);
        }
        if(! postFolderId.equals(postFolder.getId())){
            return new ResponseEntity<>("PostFolder ID in request path does not match the one in the object",
                    HttpStatus.BAD_REQUEST);
        }
        return postFolderService.update(postFolder);
    }
    @DeleteMapping("/post-folder/{postFolderId}")
    public ResponseEntity<?> delete(@PathVariable Long postFolderId){
        return postFolderService.delete(postFolderId);
    }
}
