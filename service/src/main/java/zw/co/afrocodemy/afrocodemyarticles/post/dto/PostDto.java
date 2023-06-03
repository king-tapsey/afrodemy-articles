package zw.co.afrocodemy.afrocodemyarticles.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.afrocodemy.afrocodemyarticles.post.PostCommentPermission;
import zw.co.afrocodemy.afrocodemyarticles.post.PostType;
import zw.co.afrocodemy.afrocodemyarticles.post.PostVisibility;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String creatorUsername;
    private String modifierUsername;
    private LocalDateTime releaseDate;
    private Long postFolderId;
    private PostType postType;
    private PostVisibility postVisibility;
    private String title;
    private String textContent;
    private Set<String> postTags;
    private String postUrl;
    private PostCommentPermission postCommentPermission;
}
