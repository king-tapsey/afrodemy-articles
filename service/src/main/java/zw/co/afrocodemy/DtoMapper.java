package zw.co.afrocodemy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.comment.Comment;
import zw.co.afrocodemy.comment.CommentReply;
import zw.co.afrocodemy.comment.CommentRepository;
import zw.co.afrocodemy.comment.dto.CommentDto;
import zw.co.afrocodemy.comment.dto.CommentReplyDto;
import zw.co.afrocodemy.post.*;
import zw.co.afrocodemy.post.dto.PostDto;
import zw.co.afrocodemy.post.dto.PostFolderDto;

@Service
@RequiredArgsConstructor
public class DtoMapper {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostFolderRepository postFolderRepository;

    public Comment dtoToComment(final CommentDto commentDto){
        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setCreatedDate(commentDto.getCreatedDate());
        comment.setModifiedDate(commentDto.getModifiedDate());
        comment.setCreatorUsername(commentDto.getCreatorUsername());
        comment.setTextContent(commentDto.getTextContent());
        comment.setPost(postRepository.findById(commentDto.getPostId()).get());
        comment.setMentionedUsers(commentDto.getMentionedUsers());

        return comment;
    }
    public CommentReply dtoToCommentReply(final CommentReplyDto commentReplyDto){
        CommentReply commentReply = new CommentReply();

        commentReply.setId(commentReplyDto.getId());
        commentReply.setCreatedDate(commentReplyDto.getCreatedDate());
        commentReply.setModifiedDate(commentReplyDto.getModifiedDate());
        commentReply.setCreatorUsername(commentReplyDto.getCreatorUsername());
        commentReply.setTextContent(commentReplyDto.getTextContent());
        commentReply.setPost(postRepository.findById(commentReplyDto.getPostId()).get());
        commentReply.setMentionedUsers(commentReplyDto.getMentionedUsers());
        commentReply.setParentComment(commentRepository.findById(commentReplyDto.getId()).get());

        return commentReply;
    }
    public CommentDto commentToDto(final Comment comment){

        final CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .creatorUsername(comment.getCreatorUsername())
                .textContent(comment.getTextContent())
                .postId(comment.getPost().getId())
                .mentionedUsers(comment.getMentionedUsers())
                .build();

        return commentDto;
    }
    public CommentReplyDto commentReplyToDto(final Comment comment){
        final CommentReply commentReply = (CommentReply) comment;

        final CommentReplyDto commentReplyDto = CommentReplyDto.builder()
                .id(commentReply.getId())
                .createdDate(commentReply.getCreatedDate())
                .modifiedDate(commentReply.getModifiedDate())
                .creatorUsername(commentReply.getCreatorUsername())
                .textContent(commentReply.getTextContent())
                .postId(commentReply.getPost().getId())
                .mentionedUsers(commentReply.getMentionedUsers())
                .parentCommentId(commentReply.getParentComment().getId())
                .build();

        return commentReplyDto;
    }
    public Post dtoToPost(final PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setCreatedDate(postDto.getCreatedDate());
        post.setModifiedDate(postDto.getModifiedDate());
        post.setCreatorUsername(postDto.getCreatorUsername());
        post.setModifierUsername(postDto.getModifierUsername());
        post.setReleaseDate(postDto.getReleaseDate());
        post.setPostFolder(postFolderRepository.findById(postDto.getPostFolderId()).get());
        post.setPostType(postDto.getPostType());
        post.setPostVisibility(postDto.getPostVisibility());
        post.setTitle(postDto.getTitle());
        post.setTextContent(postDto.getTextContent());
        post.setPostTags(postDto.getPostTags());
        post.setPostUrl(postDto.getPostUrl());
        return post;
    }
    public PostDto postToDto(final Post post){
        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .creatorUsername(post.getCreatorUsername())
                .modifierUsername(post.getModifierUsername())
                .releaseDate(post.getReleaseDate())
                .postFolderId(post.getPostFolder().getId())
                .postType(post.getPostType())
                .postVisibility(post.getPostVisibility())
                .title(post.getTitle())
                .textContent(post.getTextContent())
                .postTags(post.getPostTags())
                .postUrl(post.getPostUrl())
                .build();
        return postDto;
    }
    public PostSubfolder dtoToPostSubfolder(final PostFolderDto postFolderDto){
        PostSubfolder postSubfolder = new PostSubfolder();

        postSubfolder.setId(postFolderDto.getId());
        postSubfolder.setCreatedDate(postFolderDto.getCreatedDate());
        postSubfolder.setModifiedDate(postFolderDto.getModifiedDate());
        postSubfolder.setCreatorUsername(postFolderDto.getCreatorUsername());
        postSubfolder.setModifierUsername(postFolderDto.getModifierUsername());
        postSubfolder.setName(postFolderDto.getName());
        postSubfolder.setParentFolder(postFolderRepository.findById(postFolderDto.getParentFolderId()).orElse(null));

        return postSubfolder;
    }
    public PostFolder dtoToPostFolder(final PostFolderDto postFolderDto){
        PostFolder postFolder = new PostSubfolder();

        postFolder.setId(postFolderDto.getId());
        postFolder.setCreatedDate(postFolderDto.getCreatedDate());
        postFolder.setModifiedDate(postFolderDto.getModifiedDate());
        postFolder.setCreatorUsername(postFolderDto.getCreatorUsername());
        postFolder.setModifierUsername(postFolderDto.getModifierUsername());
        postFolder.setName(postFolderDto.getName());

        return postFolder;
    }
    public PostFolderDto postSubfolderToDto(final PostFolder postFolder){
        final PostSubfolder postSubfolder = (PostSubfolder) postFolder;

        PostFolderDto postFolderDto = PostFolderDto.builder()
                .id(postSubfolder.getId())
                .createdDate(postSubfolder.getCreatedDate())
                .modifiedDate(postSubfolder.getModifiedDate())
                .creatorUsername(postSubfolder.getCreatorUsername())
                .modifierUsername(postSubfolder.getModifierUsername())
                .name(postSubfolder.getName())
                .parentFolderId(postSubfolder.getParentFolder().getId())
                .build();

        return postFolderDto;
    }
}
