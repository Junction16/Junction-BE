package junction.domain.s3.domain.repository;

import junction.domain.s3.domain.entity.S3Video;
import junction.domain.s3.domain.entity.VideoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface S3VideoRepository extends JpaRepository<S3Video, Long> {
    List<S3Video> findAllByVideoType(VideoType videoType);
}
