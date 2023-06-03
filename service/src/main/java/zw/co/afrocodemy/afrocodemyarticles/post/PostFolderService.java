package zw.co.afrocodemy.afrocodemyarticles.post;

import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyarticles.post.dto.PostFolderDto;

public interface PostFolderService {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long postFolderId);
    ResponseEntity<?> create(PostFolderDto postFolder);
    ResponseEntity<?> update(PostFolderDto postFolder);
    ResponseEntity<?> delete(Long postFolderId);
}
