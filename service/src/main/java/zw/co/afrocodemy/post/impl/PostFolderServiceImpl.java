package zw.co.afrocodemy.post.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.DtoMapper;
import zw.co.afrocodemy.post.*;
import zw.co.afrocodemy.post.dto.PostFolderDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFolderServiceImpl implements PostFolderService {
    private final PostSubfolderRepository postSubfolderRepository;
    private final PostFolderRepository postFolderRepository;
    private final DtoMapper mapper;

    @Override
    public ResponseEntity<?> getAll() {
        List<PostFolderDto> postFolders = postFolderRepository.findAll().stream()
                .map(mapper::postSubfolderToDto)
                .toList();
        return ResponseEntity.ok(postFolders);
    }

    @Override
    public ResponseEntity<?> getById(Long postFolderId) {
        Optional<PostFolder> postFolder = postFolderRepository.findById(postFolderId);
        if(postFolder.isEmpty()){
            return new ResponseEntity<>("PostFolder not found", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(mapper.postSubfolderToDto(postFolder.get()));
    }

    @Override
    public ResponseEntity<?> create(PostFolderDto postFolder) {
        postFolder.setId(null);
        if(postFolder.getParentFolderId() == null){
            return ResponseEntity.ok(postFolderRepository.save(mapper.dtoToPostFolder(postFolder)));
        }else {
            if(! postFolderRepository.existsById(postFolder.getParentFolderId())){
                return new ResponseEntity<>("ParentFolder not found", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(postSubfolderRepository.save(mapper.dtoToPostSubfolder(postFolder)));
        }
    }

    @Override
    public ResponseEntity<?> update(PostFolderDto postFolderDto) {

        if(! postFolderRepository.existsById(postFolderDto.getId())){
            return new ResponseEntity<>("PostFolder not found", HttpStatus.BAD_REQUEST);
        }

        if(postFolderDto.getParentFolderId() == null){
            PostFolder postFolder = mapper.dtoToPostFolder(postFolderDto);
            return ResponseEntity.ok(postFolderRepository.save(postFolder));
        }
        else{
            if(! postFolderRepository.existsById(postFolderDto.getParentFolderId())){
                return new ResponseEntity<>("ParentFolder not found", HttpStatus.BAD_REQUEST);
            }
            PostSubfolder postSubfolder = mapper.dtoToPostSubfolder(postFolderDto);
            return ResponseEntity.ok(postSubfolderRepository.save(postSubfolder));
        }
    }

    @Override
    public ResponseEntity delete(Long postFolderId) {
        if(! postFolderRepository.existsById(postFolderId)){
            return new ResponseEntity<>("PostFolder not found", HttpStatus.BAD_REQUEST);
        }
        postFolderRepository.deleteById(postFolderId);
        return ResponseEntity.ok("Post with id: " + postFolderId + " successfully deleted");
    }
}
