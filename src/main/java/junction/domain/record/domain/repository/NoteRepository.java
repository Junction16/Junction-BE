package junction.domain.record.domain.repository;


import junction.domain.record.domain.entity.Note;
import junction.domain.s3.domain.entity.S3Video;
import junction.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByUser(User user);

   Optional<Note> findByS3Video(S3Video s3Video);
}
