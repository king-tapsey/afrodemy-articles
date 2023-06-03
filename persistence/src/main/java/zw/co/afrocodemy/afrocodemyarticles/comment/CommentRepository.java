package zw.co.afrocodemy.afrocodemyarticles.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findAllByPostId(Long postId);
    Optional<Comment> findByIdAndPostId(Long CommentId, Long postId);
}
