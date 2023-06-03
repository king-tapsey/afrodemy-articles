package zw.co.afrocodemy.afrocodemyarticles.post;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
public class PostSubfolder extends PostFolder{

    @ManyToOne
    @JoinColumn(name = "parent_folder_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PostFolder parentFolder;

    public PostSubfolder(){}

    public PostSubfolder(LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername,
                         String modifierUsername, String name, PostFolder parentFolder) {
        super(createdDate, modifiedDate, creatorUsername, name, modifierUsername);
        this.parentFolder = parentFolder;
    }

    public PostSubfolder(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String creatorUsername,
                         String modifierUsername, String name, PostFolder parentFolder) {
        super(id, createdDate, modifiedDate, creatorUsername, name, modifierUsername);
        this.parentFolder = parentFolder;
    }

    public PostFolder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(PostFolder parentFolder) {
        this.parentFolder = parentFolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PostSubfolder that = (PostSubfolder) o;

        if (!this.getId().equals(that.getId())) return false;
        if (!this.getCreatedDate().equals(that.getCreatedDate())) return false;
        if (this.getModifiedDate() != null ? !this.getModifiedDate().equals(that.getModifiedDate()) : that.getModifiedDate() != null) return false;
        if (!this.getCreatorUsername().equals(that.getCreatorUsername())) return false;
        if (!this.getName().equals(that.getName())) return false;
        return parentFolder.equals(that.parentFolder);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + parentFolder.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PostSubfolder{" +
                "parentFolder=" + parentFolder +
                "} " + super.toString();
    }
}
