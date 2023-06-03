package zw.co.afrocodemy.afrocodemyarticles.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
public class CommentReply extends Comment{
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comment parentComment;

    public CommentReply() {
    }

    public CommentReply(LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername,
                        String textContent, Comment parentComment) {
        super(createdDate, modifiedDate, creatorUsername, textContent);
        this.parentComment = parentComment;
    }

    public CommentReply(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername,
                        String textContent, Comment parentComment) {
        super(id, createdDate, modifiedDate, creatorUsername, textContent);
        this.parentComment = parentComment;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CommentReply that = (CommentReply) o;

        if (!this.getId().equals(that.getId())) return false;
        if (!this.getCreatedDate().equals(that.getCreatedDate())) return false;
        if (this.getModifiedDate() != null ? !this.getModifiedDate().equals(that.getModifiedDate()) :
                that.getModifiedDate() != null) return false;
        if (!this.getCreatorUsername().equals(that.getCreatorUsername())) return false;
        if (!this.getTextContent().equals(that.getTextContent())) return false;
        if (! ((this.getMentionedUsers() != null) && (this.getMentionedUsers().equals(that.getMentionedUsers()))) ) return false;
        return parentComment.equals(that.parentComment);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + parentComment.hashCode();
        return result;
    }
}
