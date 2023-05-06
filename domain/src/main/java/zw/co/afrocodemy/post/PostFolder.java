package zw.co.afrocodemy.post;

import jakarta.persistence.Entity;
import zw.co.afrocodemy.BaseEntity;

import java.time.LocalDateTime;

@Entity
public class PostFolder extends BaseEntity {
    private String name;
    private String modifierUsername;

    public PostFolder(){}

    public PostFolder(LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername, String name, String modifierUsername) {
        super(createdDate, modifiedDate, creatorUsername);
        this.name = name;
        this.modifierUsername = modifierUsername;
    }

    public PostFolder(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername, String name, String modifierUsername) {
        super(id, createdDate, modifiedDate, creatorUsername);
        this.name = name;
        this.modifierUsername = modifierUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModifierUsername() {
        return modifierUsername;
    }

    public void setModifierUsername(String modifierUsername) {
        this.modifierUsername = modifierUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PostFolder that = (PostFolder) o;

        if (!this.getId().equals(that.getId())) return false;
        if (!this.getCreatedDate().equals(that.getCreatedDate())) return false;
        if (this.getModifiedDate() != null ? !this.getModifiedDate().equals(that.getModifiedDate()) : that.getModifiedDate() != null) return false;
        if (!this.getCreatorUsername().equals(that.getCreatorUsername())) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PostFolder{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
