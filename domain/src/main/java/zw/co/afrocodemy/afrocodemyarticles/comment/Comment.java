package zw.co.afrocodemy.afrocodemyarticles.comment;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import zw.co.afrocodemy.afrocodemyarticles.BaseEntity;
import zw.co.afrocodemy.afrocodemyarticles.post.Post;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Comment extends BaseEntity {
    private String textContent;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
    @ElementCollection
    private List<String> mentionedUsers;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment(){}

    public Comment(LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername, String textContent) {
        super(createdDate, modifiedDate, creatorUsername);
        this.textContent = textContent;
    }

    public Comment(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername, String textContent) {
        super(id, createdDate, modifiedDate, creatorUsername);
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public List<String> getMentionedUsers() {
        return mentionedUsers;
    }

    public void setMentionedUsers(List<String> mentionedUsers) {
        this.mentionedUsers = mentionedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Comment comment = (Comment) o;

        if (!this.getId().equals(comment.getId())) return false;
        if (!this.getCreatedDate().equals(comment.getCreatedDate())) return false;
        if (this.getModifiedDate() != null ? !this.getModifiedDate().equals(comment.getModifiedDate()) :
                comment.getModifiedDate() != null) return false;
        if(! this.getCreatorUsername().equals(comment.getCreatorUsername())) return false;
        if (!textContent.equals(comment.textContent)) return false;
        return mentionedUsers != null ? mentionedUsers.equals(comment.mentionedUsers) : comment.mentionedUsers == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + textContent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "textContent='" + textContent + '\'' +
                "} " + super.toString();
    }
}
