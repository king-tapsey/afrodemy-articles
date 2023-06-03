package zw.co.afrocodemy.afrocodemyarticles.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import zw.co.afrocodemy.afrocodemyarticles.BaseEntity;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Post extends BaseEntity {
    private String modifierUsername;
    private LocalDateTime releaseDate;//schedule release if date is in the future
    @ManyToOne
    @JoinColumn(name = "post_folder_id")
    private PostFolder postFolder;
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Enumerated(EnumType.STRING)
    private PostVisibility postVisibility;
    @Column(nullable = false)
    private String title; //make unique across the site
    private String textContent;
    @ElementCollection
    private Set<String> postTags;
    private String postUrl;
    private Boolean adminApproved;//if not then access must be restricted to admins only
    private String approverUsername;
    private PostCommentPermission postCommentPermission;

    public Post(){}

    public Post(LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername, String modifierUsername,
                LocalDateTime releaseDate, PostFolder postFolder, PostType postType, PostVisibility postVisibility,
                String title, String textContent, Set<String> postTags, String postUrl, Boolean adminApproved,
                String approverUsername, PostCommentPermission postCommentPermission) {
        super(createdDate, modifiedDate, creatorUsername);
        this.modifierUsername = modifierUsername;
        this.releaseDate = releaseDate;
        this.postFolder = postFolder;
        this.postType = postType;
        this.postVisibility = postVisibility;
        this.title = title;
        this.textContent = textContent;
        this.postTags = postTags;
        this.postUrl = postUrl;
        this.adminApproved = adminApproved;
        this.approverUsername = approverUsername;
        this.postCommentPermission = postCommentPermission;
    }

    public String getModifierUsername() {
        return modifierUsername;
    }

    public Post setModifierUsername(String modifierUsername) {
        this.modifierUsername = modifierUsername;
        return this;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public PostFolder getPostFolder() {
        return postFolder;
    }

    public void setPostFolder(PostFolder postFolder) {
        this.postFolder = postFolder;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public PostVisibility getPostVisibility() {
        return postVisibility;
    }

    public void setPostVisibility(PostVisibility postVisibility) {
        this.postVisibility = postVisibility;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Set<String> getPostTags() {
        return postTags;
    }

    public void setPostTags(Set<String> postTags) {
        this.postTags = postTags;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public Boolean getAdminApproved() {
        return adminApproved;
    }

    public void setAdminApproved(Boolean adminApproved) {
        this.adminApproved = adminApproved;
    }

    public String getApproverUsername() {
        return approverUsername;
    }

    public void setApproverUsername(String approverUsername) {
        this.approverUsername = approverUsername;
    }

    public PostCommentPermission getPostCommentPermission() {
        return postCommentPermission;
    }

    public Post setPostCommentPermission(PostCommentPermission postCommentPermission) {
        this.postCommentPermission = postCommentPermission;
        return this;
    }
    @Override
    public String toString() {
        return "Post{" +
                "postType=" + postType +
                ", title='" + title + '\'' +
                "} " + super.toString();
    }
}
