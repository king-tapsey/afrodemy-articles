package zw.co.afrocodemy.post;

import jakarta.persistence.*;

@Entity
public class PostReaction {
    @Id
    Long id;
    String username;
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
    @Enumerated(EnumType.STRING)
    Reaction reaction;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostReaction(){}

    public PostReaction(String username, Post post, Reaction reaction) {
        this.username = username;
        this.post = post;
        this.reaction = reaction;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostReaction that = (PostReaction) o;

        if (!id.equals(that.id)) return false;
        if (!username.equals(that.username)) return false;
        return reaction == that.reaction;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
