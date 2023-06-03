package zw.co.afrocodemy.afrocodemyarticles.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String creatorUsername;
    private String textContent;
    private Long postId;
    private List<String> mentionedUsers;
}
