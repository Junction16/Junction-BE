package junction.domain.s3.domain.repository;

import junction.domain.s3.domain.entity.S3Choice;
import junction.domain.s3.domain.entity.S3Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface S3ChoiceRepository extends JpaRepository<S3Choice, Long> {
    List<S3Choice> findAllByS3Video(S3Video s3Video);
}
