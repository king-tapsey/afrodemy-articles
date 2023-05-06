package zw.co.afrocodemy.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.afrocodemy.post.PostType;
import zw.co.afrocodemy.post.PostVisibility;

import java.time.LocalDateTime;
import java.util.Date;
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
}
